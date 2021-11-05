
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.net.URL;

public class MyFrame extends JFrame {

    /** constuctor */
    MyFrame(String title,String pic){
        super(title);

        /** add picture display component */
        BgPanel root = new BgPanel(pic);
        this.setContentPane(root);

        /** no layout(DIY) */
        root.setLayout(null);

    }

    /** background panel */
    private class BgPanel extends JPanel{
        Image image = null;

        public BgPanel(String pic){
            URL imageUrl = MyFrame.class.getResource(pic);
            try{
                image= ImageIO.read(imageUrl);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        protected void paintComponent(Graphics g){
            int width = this.getWidth();
            int height = this.getHeight();
            g.clearRect(0,0,width,height);

            g.drawImage(image,0,0,width,height,null);

        }
    }
}
