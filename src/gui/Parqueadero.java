package gui;
import tools.Files;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
public class Parqueadero extends JPanel {
    private boolean lleno;
    public Parqueadero(){
        setMinimumSize(new Dimension(1000, 500));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    /**
     * @param percentage 0% - 100%
     * @param width true: width | false: height
     * @return punto en el componente según la orientación
     */
    private int pointOfComponent(double percentage, boolean width){
        return (int) ((width ? getMinimumSize().width : getMinimumSize().height) * (percentage / 100));
    }
    private int pointWidth(double percentage){
        return pointOfComponent(percentage, true);
    }
    private int pointHeight(double percentage){
        return pointOfComponent(percentage, false);
    }
    private int minusSizeComponent(int minus, boolean width){
        return (width ? getMinimumSize().width : getMinimumSize().height) - minus;
    }
    private int minusWidth(int minus){
        return minusSizeComponent(minus, true);
    }
    private int minusHeight(int minus){
        return minusSizeComponent(minus, false);
    }
    private void doors(Graphics2D g2){
        g2.fill3DRect(0, pointHeight(25), 30, pointHeight(50), true);
        g2.fill3DRect(minusWidth(30), pointHeight(25), 30, pointHeight(50), true);
    }
    private void parking(Graphics2D g2){
        g2.drawImage(Objects.requireNonNull(Files.image("/recursos/imagenes/ENTRADA.png", -1, 30), "Imagen No Encontrada").getImage(), 6, (int)(getMinimumSize().height * 0.18), this);
        g2.drawImage(Objects.requireNonNull(Files.image("/recursos/imagenes/Salida.png", -1, 30), "Imagen No Encontrada").getImage(), getMinimumSize().width - 68, (int)(getMinimumSize().height * 0.18), this);
        g2.drawImage(Objects.requireNonNull(Files.image("/recursos/imagenes/parking.jpg", 50, -1), "Imagen No Encontrada").getImage(), 20, 20, this);
        g2.drawImage(Objects.requireNonNull(Files.image("/recursos/imagenes/parking.jpg", 50, -1), "Imagen No Encontrada").getImage(), minusWidth(70), 20, this);
    }
    private void lines(Graphics2D g2){
        g2.setColor(new Color(219, 210, 11));
        g2.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[]{25.0f, 25.0f}, 0.0f));
        g2.drawLine(pointWidth(11), pointHeight(50) - 2, minusWidth(pointWidth(11)), pointHeight(50) - 2);
    }
    private void parkingSpaces(Graphics2D g2){

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        parking(g2);
        doors(g2);
        lines(g2);
        parkingSpaces(g2);
    }
}