package gui;
import javax.swing.*;
import java.awt.*;
public class Main extends JFrame {
    public Main(){
        super("Parqueadero");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        init();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
    private void init(){

    }
}