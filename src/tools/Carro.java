package tools;
import java.awt.*;
import java.util.Objects;
import java.util.Random;
public class Carro {
    private Image image;
    private int posicion;
    private String nombre;
    private int id;
    private long espera;
    private final Point point;
    private static final Random random;
    {
        point = new Point();
    }
    static {
        random = new Random();
    }
    private Carro(String nombre, int id, Image image) {
        this.nombre = nombre;
        this.id = id;
        this.image = image;
        this.espera = random.nextInt(13257) + 20573;  // entre 20 y 33
    }
    public Image getImage() {
        return image;
    }
    public int getPosicion() {
        return posicion;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    public Point getPoint() {
        return point;
    }
    public void setPoint (int x, int y) {
        point.move(x, y);
    }
    public static Carro getCarro() {
        switch (random.nextInt(5)) {
            case 0:
                return getBlancoRojo();
            case 1:
                return getCyan();
            case 2:
                return getVerdeAmarillo();
            case 3:
                return getVerdeRojo();
            case 4:
                return getBlancoNegro();
            default:
                return null;
        }
    }
    private static Carro getBlancoRojo(){
        return new Carro(Names.BLANCO_ROJO, ++Colors.BLANCO_ROJO, Objects.requireNonNull(Files.image("/recursos/imagenes/cars/car.png", -1, 30)).getImage());
    }
    private static Carro getCyan(){
        return new Carro(Names.CYAN, ++Colors.CYAN, Objects.requireNonNull(Files.image("/recursos/imagenes/cars/car1.png", -1, 30)).getImage());
    }
    private static Carro getVerdeAmarillo(){
        return new Carro(Names.VERDE_AMARILLO, ++Colors.VERDE_AMARILLO, Objects.requireNonNull(Files.image("/recursos/imagenes/cars/car2.png", -1, 30)).getImage());
    }
    private static Carro getVerdeRojo(){
        return new Carro(Names.VERDE_ROJO, ++Colors.VERDE_ROJO, Objects.requireNonNull(Files.image("/recursos/imagenes/cars/car3.png", -1, 30)).getImage());
    }
    private static Carro getBlancoNegro(){
        return new Carro(Names.BLANCO_NEGRO, ++Colors.BLANCO_NEGRO, Objects.requireNonNull(Files.image("/recursos/imagenes/cars/car4.png", -1, 30)).getImage());
    }
    @Override
    public String toString() {
        return "Carro{" + nombre + " - " + id + '}';
    }
    private static class Colors {
        private static int BLANCO_ROJO;
        private static int CYAN;
        private static int VERDE_AMARILLO;
        private static int VERDE_ROJO;
        private static int BLANCO_NEGRO;
        static {
            BLANCO_ROJO = 0;
            CYAN = 0;
            VERDE_AMARILLO = 0;
            VERDE_ROJO = 0;
            BLANCO_NEGRO = 0;
        }
    }
    private static class Names {
        private static String BLANCO_ROJO;
        private static String CYAN;
        private static String VERDE_AMARILLO;
        private static String VERDE_ROJO;
        private static String BLANCO_NEGRO;
        static {
            BLANCO_ROJO = "Blanco Rojo";
            CYAN = "Cyan";
            VERDE_AMARILLO = "Verde Amarillo";
            VERDE_ROJO = "Verde Rojo";
            BLANCO_NEGRO = "Blanco Negro";
        }
    }
}