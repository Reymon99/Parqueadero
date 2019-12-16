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
        Constrains.addComp(new Parqueadero(), this, new Rectangle(0, 0, 1, 1), 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addCompX(panelDeControl(), this, new Rectangle(0, 1, 1, 1), 0.3, new Insets(10, 5, 5, 5), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
    }
    private JPanel panelDeControl(){
        final JPanel panel = new JPanel(new GridBagLayout());
        JButton start = new JButton("Iniciar");
        JButton end = new JButton("Terminar");
        Constrains.addCompX(start, panel, new Rectangle(0, 0, 1, 1), 1, new Insets(15, 15, 15, 10), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(end, panel, new Rectangle(1, 0, 1, 1), 1, new Insets(15, 15, 15, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        return panel;
    }
}