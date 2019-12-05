package gui;
import tools.Files;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Parqueadero extends JPanel {
    public Parqueadero(){
        setMinimumSize(new Dimension(1000, 500));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.fill3DRect(0, (int)(getMinimumSize().height * 0.25), 30, (int)(getMinimumSize().height * 0.5), true);
        g2.fill3DRect(getMinimumSize().width - 30, (int)(getMinimumSize().height * 0.25), 30, (int)(getMinimumSize().height * 0.5), true);
        g2.drawImage(Objects.requireNonNull(Files.image("/recursos/imagenes/ENTRADA.png", -1, 30), "Imagen No Encontrada").getImage(), 6, (int)(getMinimumSize().height * 0.18), this);
        g2.drawImage(Objects.requireNonNull(Files.image("/recursos/imagenes/Salida.png", -1, 30), "Imagen No Encontrada").getImage(), getMinimumSize().width - 68, (int)(getMinimumSize().height * 0.18), this);
    }
}