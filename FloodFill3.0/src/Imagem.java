import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Imagem extends JPanel {
    private final BufferedImage img;

    public Imagem(BufferedImage img) {
        this.img = img;
    }

    @Override
    protected void paintComponent(Graphics i) {
        super.paintComponent(i);
        i.drawImage(img, 0, 0, null);
    }
}
