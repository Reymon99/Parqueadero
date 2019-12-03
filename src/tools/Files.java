package tools;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class Files {
    public static ImageIcon image(String path, int width, int height) {
        try{
            return new ImageIcon(ImageIO.read(Files.class.getResource(path)).getScaledInstance(width,height, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            return null;
        }
    }
}