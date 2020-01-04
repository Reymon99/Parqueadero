package hilos;
import gui.PanelControl;
import tools.Carro;
import javax.swing.*;
import java.util.Random;
public class CarGenerator extends Thread {
    @Override
    public void run() {
        Random random = new Random();
        while (PanelControl.activo){
            PanelControl.getInstance().addCar(Carro.getCarro());
            PanelControl.getInstance().updateCarrosFaltantes();
            try {
                Thread.sleep(random.nextInt(9754) + 2003); // entre 2 y 11.8 segundos de espera
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}