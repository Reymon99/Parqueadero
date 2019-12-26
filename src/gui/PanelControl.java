package gui;
import tools.Carro;
import tools.ParkingSpaceFullException;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
public class PanelControl extends JPanel {
    public static boolean activo;
    private JTextArea enLista;
    private JTextArea notificaciones;
    private JLabel carrosFaltantes;
    private JLabel carrosParqueados;
    private JLabel carrosDespachados;//el se del contador solo anidar el número al mensaje preescrito
    private final LinkedList<Carro> carrosOut;
    private final Carro[] carrosIn;
    {
        carrosOut = new LinkedList<>();
        carrosIn = new Carro[6];
    }
    static {
        activo = false;
    }
    public PanelControl(){
        super(new GridBagLayout());
        init();
    }
    private void init(){
        final JButton start = new JButton("Iniciar");
        final JButton end = new JButton("Terminar");
        start.addActionListener((e) -> {
            juego();
            //lanzar hilo principal acá, que a la vez ese hilo lanzará los multiples hilos que controlarán los automoviles en el estacionamiento
        });
        end.addActionListener((e) -> juego());
        Constrains.addCompX(start, this, new Rectangle(0, 0, 1, 1), 1, new Insets(15, 15, 15, 10), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(end, this, new Rectangle(0, 1, 1, 1), 1, new Insets(15, 15, 15, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
    }
    private void juego(){
        PanelControl.activo = !PanelControl.activo;
    }
    private void addCar(Carro carro) throws NullPointerException {
        if (carro == null) throw new NullPointerException("No se encuentra carro a añadir");
        else if (enLista == null || carrosOut == null) throw new NullPointerException("No existe lista para el parqueadero");
        else {
            enLista.insert(carro.toString() + " esperando\n", 0);
            carrosOut.add(carro);
        }
    }
    private void addCarParking(Carro carro) throws NullPointerException, ParkingSpaceFullException {
        if (carro == null) throw new NullPointerException("No se encuentra carro a añadir");
        else if (carrosIn == null) throw new NullPointerException("No existe Parqueadero");
        else if (carrosIn[carro.getPosicion()] != null) throw new ParkingSpaceFullException(carrosIn[carro.getPosicion()], carro, carrosIn);
        else {
            // lanzar hilo de parkeo
        }
    }
}