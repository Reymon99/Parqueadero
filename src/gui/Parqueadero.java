package gui;
import tools.Carro;
import tools.Files;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Objects;
public class Parqueadero extends JPanel {
    public static int despachados;
    public static boolean disponibleIn; // para saber si alguien está entrando al parqueadero a estacionarse
    public static boolean disponibleOut; // para saber si alguien está saliendo del parqueadero
    private final int widthParking;
    private final int heightParking;
    private final Carro[] carros;
    private static Parqueadero parqueadero;
    {
        widthParking = 170;
        heightParking = 120;
        carros = new Carro[6];
    }
    static {
        despachados = 0;
        disponibleIn = true;
        disponibleOut = true;
    }
    private Parqueadero(){
        setMinimumSize(new Dimension(1000, 500));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public static Parqueadero getInstance() {
        if (parqueadero == null) parqueadero = new Parqueadero();
        return parqueadero;
    }
    public boolean isLleno() {
        for(Carro carro : carros) if (carro == null) return false;
        return true;
    }
    public Carro[] getCarros() {
        return carros;
    }
    public void setCarro(int pos, Carro carro) {
        carros[pos] = carro;
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
        g2.setColor(new Color(130, 137, 143));
        g2.setStroke(new BasicStroke(17.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(x, y, x, y + heightParking);
        g2.drawLine(x + widthParking, y, x + widthParking, y + heightParking);
    }
    private void number(Graphics2D g2, int number, int x, int y){
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 34));
        g2.setColor(new Color(146, 76, 43));
        g2.setStroke(new BasicStroke(4.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        FontMetrics metrics = getFontMetrics(getFont());
        int radio = 28;
        String numberString = String.valueOf(number);
        g2.drawString(numberString, x +(widthParking >> 1) - (metrics.stringWidth(numberString) >> 1), y + (heightParking >> 1) + (number >= 3 ? metrics.getLeading() : metrics.getHeight()));
        int y1 = y + heightParking - (radio << 1) - (metrics.getHeight() << 1) + (number >= 3 ?  (metrics.getAscent() << 1) * -1 : metrics.getLeading()) + (number >= 3 ? 13 : 5);
        g2.draw(new Ellipse2D.Double(x + (widthParking >> 1) - radio + 3 + (metrics.stringWidth(numberString) >> 1), y1, radio << 1, radio << 1));
    }
    /**
     * dibuja los carros que están dentro del parqueadero
     * @param g2 pincel
     */
    private void parquear(Graphics2D g2) {
        for(Carro carro : carros) if (carro != null) g2.drawImage(Objects.requireNonNull(carro.getImage(), "Imagen No Encontrada"), carro.getPoint().x, carro.getPoint().y, this);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        parking(g2);
        doors(g2);
        lines(g2);
        parkingSpaces(g2);
        parquear(g2);
    }
}