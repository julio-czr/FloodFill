import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFiller {

    private final BufferedImage image;
    private final Queue<Point> pointsQueue;
    private final Color targetColor;
    private final Color replacementColor;

    public FloodFiller(BufferedImage image, int startX, int startY, Color replacementColor) {
        this.image = image;
        this.targetColor = new Color(image.getRGB(startX, startY));
        this.replacementColor = replacementColor;
        this.pointsQueue = new Queue<>(image.getWidth() * image.getHeight());

        if (!targetColor.equals(replacementColor)) {
            pointsQueue.enqueue(new Point(startX, startY));
        }
    }

    public void fillNextPixels(int numberOfPixels) {
        for (int i = 0; i < numberOfPixels && !pointsQueue.isEmpty(); i++) {
            Point currentPoint = pointsQueue.dequeue();
            fillPixel(currentPoint.x, currentPoint.y);
        }
    }

    private void fillPixel(int x, int y) {
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            return;
        }
        if (!new Color(image.getRGB(x, y)).equals(targetColor)) {
            return;
        }

        image.setRGB(x, y, replacementColor.getRGB());

        pointsQueue.enqueue(new Point(x + 1, y));
        pointsQueue.enqueue(new Point(x - 1, y));
        pointsQueue.enqueue(new Point(x, y + 1));
        pointsQueue.enqueue(new Point(x, y - 1));
    }

    public boolean isFillComplete() {
        return pointsQueue.isEmpty();
    }
}
