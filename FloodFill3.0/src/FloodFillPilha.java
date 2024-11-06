import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFillPilha {

    private final BufferedImage imagem;
    private final pilha<Point> pixelPilha; 
    private final Color corAntiga;
    private final Color corNova;

    public FloodFillPilha(BufferedImage img, int x, int y, Color corNova) {
        this.imagem = img;
        this.corAntiga = new Color(img.getRGB(x, y));
        this.corNova = corNova;
        this.pixelPilha = new pilha<>(); 
        if (!corAntiga.equals(corNova)) {
            pixelPilha.empilhar(new Point(x, y)); 
        }
    }

    public void preencherPixels(int PIXELS_POR_INTERACAO) {
        for (int i = 0; i < PIXELS_POR_INTERACAO && !pixelPilha.vazia(); i++) {
            Point point = pixelPilha.desempilhar();
            pintarPixel(point.x, point.y);
        }
    }

    private void pintarPixel(int x, int y) {
        if (x < 0 || x >= imagem.getWidth() || y < 0 || y >= imagem.getHeight()) {
            return;
        }
        if (!new Color(imagem.getRGB(x, y)).equals(corAntiga)) {
            return;
        }

        imagem.setRGB(x, y, corNova.getRGB());

        pixelPilha.empilhar(new Point(x + 1, y)); 
        pixelPilha.empilhar(new Point(x - 1, y)); 
        pixelPilha.empilhar(new Point(x, y + 1)); 
        pixelPilha.empilhar(new Point(x, y - 1)); 
    }

    public boolean finalizar() {
        return pixelPilha.vazia();
    }
}
