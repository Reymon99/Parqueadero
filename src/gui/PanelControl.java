package gui;
import hilos.CarGenerator;
import tools.Carro;
import tools.ParkingSpaceFullException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class PanelControl extends JDialog {
    public static boolean activo;
    private final DefaultListModel<Carro> enEspera;
    private final DefaultListModel<String> notificaciones;
    private JLabel carrosFaltantes;
    private JLabel carrosParqueados;
    private JLabel carrosDespachados;
    private static PanelControl panelControl;
    {
        enEspera = new DefaultListModel<>();
        notificaciones = new DefaultListModel<>();
    }
    static {
        activo = false;
        panelControl = null;
    }
    private PanelControl(JFrame frame){
        super(frame);
        setTitle("Panel de Control");
        setLocation(20, 50);
        getContentPane().setLayout(new GridBagLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        init();
        pack();
    }
    public static PanelControl getInstance(JFrame frame) throws NullPointerException {
        if (panelControl == null && frame != null) panelControl = new PanelControl(frame);
        else if (panelControl == null) throw new NullPointerException("Frame not found");
        return panelControl;
    }
    public static PanelControl getInstance() {
        return getInstance(null);
    }
    private void init(){
        final JButton start = new JButton("Iniciar");
        final JButton end = new JButton("Terminar");
        start.setBackground(Color.GREEN);
        end.setBackground(Color.RED);
        start.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        end.setFont(start.getFont());
        start.addActionListener((e) -> {
            start.setEnabled(false);
            end.setEnabled(true);
            juego();
            new CarGenerator(PanelControl.this).start();
            //lanzar hilo principal acá, que a la vez ese hilo lanzará los multiples hilos que controlarán los automoviles en el estacionamiento
        });
        end.addActionListener((e) -> {
            juego();
            start.setEnabled(true);
            end.setEnabled(false);
        });
        end.setEnabled(false);
        final JList<Carro> espera = new JList<>(enEspera);
        final JList<String> notify = new JList<>(notificaciones);
        espera.setBorder(BorderFactory.createTitledBorder("En Espera"));
        notify.setBorder(BorderFactory.createTitledBorder("Notificaciones"));
        espera.getMinimumSize().height = 250;
        notify.getPreferredSize().height = 250;
        carrosFaltantes = new JLabel("", SwingConstants.CENTER);
        carrosParqueados = new JLabel("", SwingConstants.CENTER);
        carrosDespachados = new JLabel("", SwingConstants.CENTER);
        carrosFaltantes.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        carrosParqueados.setFont(carrosFaltantes.getFont());
        carrosDespachados.setFont(carrosFaltantes.getFont());
        updateCarros();
        Constrains.addCompX(start, getContentPane(), new Rectangle(0, 0, 1, 1), 1, new Insets(15, 15, 5, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(end, getContentPane(), new Rectangle(0, 1, 1, 1), 1, new Insets(5, 15, 10, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(new JScrollPane(espera), getContentPane(), new Rectangle(0, 2, 1, 1), 1, 4, new Insets(10, 15, 10, 15), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(new JScrollPane(notify), getContentPane(), new Rectangle(0, 3, 1, 1), 1, 4, new Insets(10, 15, 15, 15), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addCompX(carrosFaltantes, getContentPane(), new Rectangle(0, 4, 1, 1), 1, new Insets(10, 15, 10, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(carrosParqueados, getContentPane(), new Rectangle(0, 5, 1, 1), 1, new Insets(10, 15, 10, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(carrosDespachados, getContentPane(), new Rectangle(0, 6, 1, 1), 1, new Insets(10, 15, 15, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
    }
    private void juego(){
        PanelControl.activo = !PanelControl.activo;
    }
    public void addCar(Carro carro) throws NullPointerException {
        if (carro == null) throw new NullPointerException("No se encuentra carro a añadir");
        else if (enEspera == null) throw new NullPointerException("No existe lista para el parqueadero");
        else {
            enEspera.addElement(carro);
            notificaciones.add(0, carro.toString() + " en espera");
        }
    }
    private void addCarParking(Carro carro) throws NullPointerException, ParkingSpaceFullException {
        if (carro == null) throw new NullPointerException("No se encuentra carro a añadir");
        else if (Parqueadero.getInstance().getCarros() == null) throw new NullPointerException("No existe Parqueadero");
        else if (Parqueadero.getInstance().getCarros()[carro.getPosicion()] != null) throw new ParkingSpaceFullException(Parqueadero.getInstance().getCarros()[carro.getPosicion()], carro, Parqueadero.getInstance().getCarros());
        else {
            // lanzar hilo de parkeo
        }
    }
    public void updateCarrosFaltantes(){
        carrosFaltantes.setText("Carros Faltantes: " + enEspera.size());
    }
    public void updateCarrosParqueados(){
        int i = 0;
        for (Carro carro : Parqueadero.getInstance().getCarros()) if (carro != null) i++;
        carrosParqueados.setText("Carros Parqueados: " + i);
    }
    public void updateCarrosDespachados(){
        carrosDespachados.setText("Carros Despachados: " + Parqueadero.despachados);
    }
    public void updateCarros() {
        updateCarrosFaltantes();
        updateCarrosParqueados();
        updateCarrosDespachados();
    }
}