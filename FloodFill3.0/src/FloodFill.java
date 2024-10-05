import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFill {

    private final BufferedImage image;
    private final fila<Point> pointsQueue; // Substituindo por sua fila
    private final Color targetColor;
    private final Color newColor;

    public FloodFill(BufferedImage img, int x, int y, Color fillColor) {
        this.image = img;
        this.targetColor = new Color(img.getRGB(x, y));
        this.newColor = fillColor;
        this.pointsQueue = new fila<>(img.getWidth() * img.getHeight()); // Capacidade da fila

        if (!targetColor.equals(newColor)) {
            pointsQueue.adicionar(new Point(x, y));
        }
    }

    public void fillNextPixels(int pixelsPerStep) {
        for (int i = 0; i < pixelsPerStep && !pointsQueue.vazia(); i++) {
            Point point = pointsQueue.remover();
            fillPixel(point.x, point.y);
        }
    }

    private void fillPixel(int x, int y) {
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            return;
        }
        if (!new Color(image.getRGB(x, y)).equals(targetColor)) {
            return;
        }

        image.setRGB(x, y, newColor.getRGB());

        pointsQueue.adicionar(new Point(x + 1, y)); // Direita
        pointsQueue.adicionar(new Point(x - 1, y)); // Esquerda
        pointsQueue.adicionar(new Point(x, y + 1)); // Abaixo
        pointsQueue.adicionar(new Point(x, y - 1)); // Acima
    }

    public boolean isComplete() {
        return pointsQueue.vazia();
    }
}
