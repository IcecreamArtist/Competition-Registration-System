import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel{

    private Image image;

    public ImagePanel(String name) {
        try {
            URL imageUrl = ImagePanel.class.getResource(name);
            image = ImageIO.read(imageUrl);
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        g.clearRect(0,0,width,height);

        g.drawImage(image,0,0,width,height,null);
    }
}