import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FloodFillApp {

    private static FloodFill floodFill;
    private static ImagePanel imagePanel;
    private static Timer timer;
    private static final int DELAY = 1; // Tempo em milissegundos entre cada iteração
    private static final int PIXELS_PER_STEP = 200; // Número de pixels preenchidos por iteração

    public static void main(String[] args) {
        BufferedImage image = loadImageFromFile(); // Carrega a imagem a partir de um arquivo PNG

        if (image != null) {
            JFrame frame = createFrame(image.getWidth(), image.getHeight(), image);
            startFloodFill(image, 10, 10, Color.RED);
        }
    }

    private static BufferedImage loadImageFromFile() {
        JFileChooser fileChooser = new JFileChooser();  // Abre o seletor de arquivos para o usuário escolher a imagem
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                return ImageIO.read(selectedFile);  // Carrega a imagem selecionada
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem.");
            }
        }
        return null;
    }

    private static JFrame createFrame(int width, int height, BufferedImage image) {
        JFrame frame = new JFrame("Flood Fill Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width + 50, height + 50);

        imagePanel = new ImagePanel(image);
        frame.add(imagePanel);
        frame.setVisible(true);

        return frame;
    }

    private static void startFloodFill(BufferedImage image, int startX, int startY, Color fillColor) {
        floodFill = new FloodFill(image, startX, startY, fillColor);

        timer = new Timer(DELAY, e -> updateFloodFill());
        timer.start();
    }

    private static void updateFloodFill() {
        floodFill.fillNextPixels(PIXELS_PER_STEP);
        imagePanel.repaint();

        if (floodFill.isComplete()) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "Preenchimento concluído!");
        }
    }
}
