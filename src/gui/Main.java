package gui;
import javax.swing.*;
import java.awt.*;
public class Main extends JFrame {
    PanelControl panelControl;
    public Main(){
        super("Parqueadero");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        init();
        pack();
        setLocation(20 + panelControl.getSize().width + 10, 70);
        setResizable(false);
    }
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
    private void init(){
        Constrains.addComp(new Parqueadero(), this, new Rectangle(0, 0, 1, 1), 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        panelControl = new PanelControl(this);
        panelControl.setVisible(true);
    }
}