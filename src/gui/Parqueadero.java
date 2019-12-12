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
        g2.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[]{25.0f, 25.0f}, 0.0f));
        g2.drawLine(pointWidth(10), pointHeight(50) - 2, minusWidth(pointWidth(10)), pointHeight(50) - 2);
    }
    private void parkingSpaces(Graphics2D g2){
        g2.setColor(new Color(130, 137, 143));
        g2.setStroke(new BasicStroke(19.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Delimita perfectamente los pixeles curvos
        int[] ejesX = new int[]{pointWidth(19), pointWidth(19) + 210, pointWidth(19) + 210 * 2};
        for (int x = 0; x < ejesX.length; x++){
            parkingSpace(g2, ejesX[x], 0);
            parkingSpace(g2, ejesX[x], minusHeight(120));
            number(g2, x, ejesX[x], 0);
            number(g2, x + 3, ejesX[x], minusHeight(120));
        }
    }
    private void parkingSpace(Graphics2D g2, int x, int y){
        g2.drawLine(x, y, x, y + 120);
        g2.drawLine(x + 170, y, x + 170, y + 120);
    }
    private void number(Graphics2D g2, int number, int x, int y){

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