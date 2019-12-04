package u4actividad2snm;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class U4Actividad2SNM {

    static final int JUGADORES = 4;
    static final int REPARTICION = 4;
    
    public static void main(String[] args) {
        U4Actividad2SNM programa = new U4Actividad2SNM();
        programa.inicio();
    }

    public void inicio() {
        String[] cartas = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "S", "C", "R"};
        String[] palos = {"OROS", "ESPADAS", "BASTOS", "COPAS"};
        String[] palosPrefix = {"O", "E", "B", "C"};
        //Inprimir palo dominate
        int paloDominante =(int) (Math.random() * palos.length);
    }
}
