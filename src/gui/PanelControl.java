package gui;
import tools.Carro;
import tools.ParkingSpaceFullException;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
public class PanelControl extends JPanel {
    public static boolean activo;
    private final DefaultListModel<String> enEspera;
    private final DefaultListModel<String> notificaciones;
    private JLabel carrosFaltantes;
    private JLabel carrosParqueados;
    private JLabel carrosDespachados;//el se del contador solo anidar el número al mensaje preescrito
    private final LinkedList<Carro> carrosOut;
    private final Carro[] carrosIn;
    {
        enEspera = new DefaultListModel<>();
        notificaciones = new DefaultListModel<>();
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
        final JList<String> espera = new JList<>(enEspera);
        final JList<String> notify = new JList<>(notificaciones);
        espera.setBorder(BorderFactory.createTitledBorder("En Espera"));
        notify.setBorder(BorderFactory.createTitledBorder("Notificaciones"));
        Constrains.addCompX(start, this, new Rectangle(0, 0, 1, 1), 1, new Insets(15, 15, 15, 10), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(end, this, new Rectangle(1, 0, 1, 1), 1, new Insets(15, 15, 15, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
    }
    private void juego(){
        PanelControl.activo = !PanelControl.activo;
    }
    public void addCar(Carro carro) throws NullPointerException {
        if (carro == null) throw new NullPointerException("No se encuentra carro a añadir");
        else if (enEspera == null || carrosOut == null) throw new NullPointerException("No existe lista para el parqueadero");
        else {
            enEspera.addElement(carro.toString() + " esperando");
            notificaciones.add(0, carro.toString() + " en espera");
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
    public void updateCarrosFaltantes(){
        carrosFaltantes.setText("Faltan " + enEspera.size() + " Carro(s)");
    }
}