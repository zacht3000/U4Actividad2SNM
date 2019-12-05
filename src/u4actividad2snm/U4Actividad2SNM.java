package u4actividad2snm;

import java.util.Scanner;

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
        int paloDominante = (int) (Math.random() * palos.length);
        //Generar baraja
        int[][] baraja = generarBaraja(palos, cartas, JUGADORES, REPARTICION);

        //----------------------------------------------------------------------------  
        for (int j = 1; j <= JUGADORES; j++) {
            String listaCartasJugador = obtenerCartasJugador(baraja, palos, cartas, j);
            System.out.println("Cartas jugador " + j + ": " + listaCartasJugador);
            System.out.print("¿Qué carta lanzas?: ");
            String cartaLanzada = leerCartaLanzada();
        }

    }

    public int[][] generarBaraja(String[] palos, String[] cartas, int jugadores, int repartirCartas) {
        int[][] baraja = new int[palos.length][cartas.length];
        for (int i = 0; i < repartirCartas; i++) {
            for (int j = 1; j <= jugadores; j++) {
                int positionY = (int) (Math.random() * palos.length);
                int positionX = (int) (Math.random() * cartas.length);

                if (baraja[positionY][positionX] == 0) {
                    baraja[positionY][positionX] = j;
                } else {
                    --j;
                }
            }
        }
        return baraja;
    }

    public String obtenerCartasJugador(int[][] baraja, String[] palos, String[] cartas, int numeroJugador) {
        String cartasJugador = "";
        for (int p = 0; p < baraja.length; p++) {
            for (int c = 0; c < baraja[p].length; c++) {
                if (baraja[p][c] == numeroJugador) {
                    if (!cartasJugador.equals("")) {
                        cartasJugador += " | " + cartas[c] + palos[p].charAt(0);
                    } else {
                        cartasJugador = cartas[c] + palos[p].charAt(0);
                    }
                }
            }
        }
        return cartasJugador;
    }

    public String leerCartaLanzada() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;

    }
}
