import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FloodFillApp {

    private static FloodFill floodFill;
    private static Imagem imagem;
    private static Timer timer;

    private static final int DELAY = 1; 
    private static final int PIXELS_POR_INTERACAO = 100; 
    private static final Color COR =Color.RED;

    public static void main(String[] args) {
        BufferedImage image = carregarImagem(); 

        if (image != null) {
            criaFrame(image.getWidth(), image.getHeight(), image); 
            startFloodFill(image, 10, 10, COR);
        }
    }

    private static BufferedImage carregarImagem() {
        JFileChooser fileChooser = new JFileChooser();  
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                return ImageIO.read(selectedFile);  
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem.");
            }
        }
        return null;
    }

    private static JFrame criaFrame(int width, int height, BufferedImage image) {
        JFrame frame = new JFrame("Flood Fill Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width + 50, height + 50);

        imagem = new Imagem(image);
        frame.add(imagem);
        frame.setVisible(true);

        return frame;
    }

    private static void startFloodFill(BufferedImage image, int startX, int startY, Color fillColor) {
        floodFill = new FloodFill(image, startX, startY, fillColor);

        timer = new Timer(DELAY, e -> updateFloodFill()); 
        timer.start();
    }

    private static void updateFloodFill() {
        floodFill.preencherPixels(PIXELS_POR_INTERACAO);
        imagem.repaint();
        
        if (floodFill.finalizar()) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "Preenchimento concluído!");
        }
    }
}
