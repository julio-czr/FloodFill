import java.awt.*;
import java.awt.image.BufferedImage;


public class FloodFillFila {

    private final BufferedImage imagem;
    private final fila<Point> filaPixel;
    private final Color corAntiga;
    private final Color corNova;

    public FloodFillFila(BufferedImage img, int x, int y, Color corNova) {
        this.imagem = img;
        this.corAntiga = new Color(img.getRGB(x, y));
        this.corNova = corNova;
        this.filaPixel = new fila<>(img.getWidth() * img.getHeight()); 

        if (!corAntiga.equals(corNova)) {
            filaPixel.adicionar(new Point(x, y));
        }
    }

    public void preencherPixels(int PIXELS_POR_INTERACAO) {
        for (int i = 0; i < PIXELS_POR_INTERACAO && !filaPixel.vazia(); i++) {
            Point point = filaPixel.remover();
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

        filaPixel.adicionar(new Point(x + 1, y));
        filaPixel.adicionar(new Point(x - 1, y)); 
        filaPixel.adicionar(new Point(x, y + 1)); 
        filaPixel.adicionar(new Point(x, y - 1)); 
    }

    public boolean finalizar() {
        return filaPixel.vazia();
    
    }
}
