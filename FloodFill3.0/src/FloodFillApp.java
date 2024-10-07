import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FloodFillApp {

    private static FloodFiller floodFiller;
    private static ImagePanel imagePanel;
    private static Timer fillTimer;
    private static final int FILL_DELAY = 1;
    private static final int PIXELS_PER_STEP = 200;

    public static void main(String[] args) {
        BufferedImage image = loadImageFromFile();

        if (image != null) {
            JFrame frame = createAppFrame(image.getWidth(), image.getHeight(), image);
            startFloodFill(image, 10, 10, Color.RED);
        }
    }

    private static BufferedImage loadImageFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int fileChooserResult = fileChooser.showOpenDialog(null);

        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                return ImageIO.read(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
                showMessage("Erro ao carregar imagem.");
            }
        }
        return null;
    }

    private static JFrame createAppFrame(int width, int height, BufferedImage image) {
        JFrame frame = new JFrame("Exemplo Flood Fill");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width + 50, height + 50);

        imagePanel = new ImagePanel(image);
        frame.add(imagePanel);
        frame.setVisible(true);

        return frame;
    }

    private static void startFloodFill(BufferedImage image, int startX, int startY, Color fillColor) {
        floodFiller = new FloodFiller(image, startX, startY, fillColor);

        fillTimer = new Timer(FILL_DELAY, e -> updateFloodFill());
        fillTimer.start();
    }

    private static void updateFloodFill() {
        floodFiller.fillNextPixels(PIXELS_PER_STEP);
        imagePanel.repaint();

        if (floodFiller.isFillComplete()) {
            fillTimer.stop();
            showMessage("Flood fill completo!");
        }
    }

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
