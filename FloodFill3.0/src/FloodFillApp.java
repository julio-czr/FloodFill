import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FloodFillApp {

    private static FloodFillFila floodFillFila;
    private static FloodFillPilha floodFillPilha;

    private static Imagem imagem;
    private static Timer timer;

    private static final int DELAY = 1;
    private static final int PIXELS_POR_INTERACAO = 1000;
    private static final Color COR = Color.RED;
    private static int contadorAlteracoes = 0; // contador de alterações
    private static int numImg =0;

    public static void main(String[] args) {
        BufferedImage image = carregarImagem();
        if (image != null) {
            criaFrame(image.getWidth(), image.getHeight(), image);
            startFloodFillFila(image, 10, 10, COR);

           
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

    private static void startFloodFillFila(BufferedImage image, int startX, int startY, Color fillColor) {
        floodFillFila = new FloodFillFila(image, startX, startY, fillColor);

        timer = new Timer(DELAY, e -> updateFloodFillFila(image));
        timer.start();
    }
    private static void startFloodFillPilha(BufferedImage image, int startX, int startY, Color fillColor) {
        floodFillPilha = new FloodFillPilha(image, startX, startY, fillColor);

        timer = new Timer(DELAY, e -> updateFloodFillPilha(image));
        timer.start();
    }

    private static void updateFloodFillFila(BufferedImage image) {
        floodFillFila.preencherPixels(PIXELS_POR_INTERACAO);
        imagem.repaint();
        contadorAlteracoes++;

        if (contadorAlteracoes >= 10) {
            numImg++;
            salvarImagem(image, "ResultadoImagens/imagem_salva"+numImg+".png");
            contadorAlteracoes = 0; // Reinicia o contador após salvar
        }

        if (floodFillFila.finalizar()) {
            salvarImagem(image, "ResultadoImagens/imagem_salva"+numImg+".png");
            timer.stop();
            JOptionPane.showMessageDialog(null, "Preenchimento concluído!");
            image= carregarImagem();
            criaFrame(image.getWidth(), image.getHeight(), image);
            startFloodFillPilha(image, 10, 10, COR);
        }
    }
    private static void updateFloodFillPilha(BufferedImage image) {
        floodFillPilha.preencherPixels(PIXELS_POR_INTERACAO);
        imagem.repaint();
        contadorAlteracoes++;

        if (contadorAlteracoes >= 10) {
            numImg++;
            salvarImagem(image, "ResultadoImagens/imagem_salva"+numImg+".png");
            contadorAlteracoes = 0; // Reinicia o contador após salvar
        }

        if (floodFillPilha.finalizar()) {
            salvarImagem(image, "ResultadoImagens/imagem_salva"+numImg+".png");
            timer.stop();
            JOptionPane.showMessageDialog(null, "Preenchimento concluído!");
        }
    }

    private static void salvarImagem(BufferedImage imagem, String caminho) {
        try {
            ImageIO.write(imagem, "png", new File(caminho));
            System.out.println("Imagem salva em: " + caminho);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar a imagem.");
        }
    }
}
