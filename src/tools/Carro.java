package tools;

import java.awt.*;

public class Carro {
    private Image image;
    private int posicion;
    private String nombre;
    public int getPosicion() {
        return posicion;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    @Override
    public String toString() {
        return "Carro{" + nombre + '}';
    }
}
