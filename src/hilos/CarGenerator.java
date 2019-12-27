package hilos;
import gui.PanelControl;
import tools.Carro;
import javax.swing.*;
import java.util.Random;
public class CarGenerator extends Thread {
    private PanelControl panelControl;
    public CarGenerator(PanelControl panelControl) {
        this.panelControl = panelControl;
    }
    @Override
    public void run() {
        Random random = new Random();
        while (true){
            panelControl.addCar(Carro.getCarro());
            panelControl.updateCarrosFaltantes();
            try {
                Thread.sleep(random.nextInt(10050) + 13250); // entre 13 y 23 segundos de espera
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}