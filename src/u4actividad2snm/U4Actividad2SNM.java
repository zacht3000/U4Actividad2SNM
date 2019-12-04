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
        //Generar baraja
        int[][] baraja = generarBaraja(palos, cartas, JUGADORES, REPARTICION);
    }
    
    public int[][] generarBaraja(String[] palos, String[] cartas, int jugadores, int repartirCartas) 
    {
        int[][] baraja = new int[palos.length][cartas.length];
        for (int i = 0; i < repartirCartas; i++) 
        {
            for (int j = 1; j <= jugadores; j++) 
            {
                int positionY = (int) (Math.random() * palos.length);
                int positionX = (int) (Math.random() * cartas.length);
                
                if (baraja[positionY][positionX] == 0)
                    baraja[positionX][positionX] = j;
                else
                    --j;
            }
        }
        return baraja;
    }
}