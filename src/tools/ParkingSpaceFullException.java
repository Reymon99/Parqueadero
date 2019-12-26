package tools;
public class ParkingSpaceFullException extends RuntimeException {
    private final Carro carroIn;
    private final Carro carroOut;
    private final Carro[] parqueados;
    public ParkingSpaceFullException(Carro carroIn, Carro carroOut, Carro... parqueados) {
        super("");
        this.carroIn = carroIn;
        this.carroOut = carroOut;
        this.parqueados = parqueados;
    }
    @Override
    public String getMessage() {
        StringBuilder n = new StringBuilder(carroOut.toString() + " no puede ingresar\n" + "La posición " + carroIn.getPosicion() + " está ocupada por " + carroIn.toString());
        for (int i = 0; i < parqueados.length; i++) if (parqueados[i] == null) n.append("\nPosición disponible en: ").append(i);
        return n.toString();
    }
}