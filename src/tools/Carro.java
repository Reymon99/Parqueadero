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
    public Carro getCarro() {
        //nextInt
        return this;
    }
    @Override
    public String toString() {
        return "Carro{" + nombre + '}';
    }
    private static class Colors {
        protected static int BLANCO_ROJO;
        protected static int CYAN;
        protected static int VERDE_AMARILLO;
        protected static int VERDE_ROJO;
        protected static int BLANCO_NEGRO;
        static {
            BLANCO_ROJO = 0;
            CYAN = 0;
            VERDE_AMARILLO = 0;
            VERDE_ROJO = 0;
            BLANCO_NEGRO = 0;
        }
    }
}