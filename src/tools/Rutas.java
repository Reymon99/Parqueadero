package tools;
import gui.Parqueadero;
public class Rutas {
    public synchronized void parquear() {
        if (!Parqueadero.getInstance().isLleno() && Parqueadero.disponibleIn) {

        }
    }
    public synchronized void desparquear() {
        if (Parqueadero.disponibleOut) {

        }
    }
    private void parquearPosicion0(Carro carro) {

    }
    private void parquearPosicion1(Carro carro) {

    }
    private void parquearPosicion2(Carro carro) {

    }
    private void parquearPosicion3(Carro carro) {

    }
    private void parquearPosicion4(Carro carro) {

    }
    private void parquearPosicion5(Carro carro) {

    }
}