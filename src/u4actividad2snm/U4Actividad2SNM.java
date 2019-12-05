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
        String[] cartas = cartas();
        int[] cartasValores = cartasValores();
        String[] palos = palos();
        String[] palosPrefix = palosPrefix();
        //Inprimir palo dominate
        int paloDominante = (int) (Math.random() * palos.length);
        //Generar baraja
        int[][] baraja = generarBaraja(palos, cartas, JUGADORES, REPARTICION);
        //Array de los datos introducidos
        String[] totalCartasLanzadas = new String[JUGADORES];
        //----------------------
        System.out.println("Palo dominante => " + palos[paloDominante]);

        //----------------------------------------------------------------------------  
        for (int j = 1; j <= JUGADORES; j++) {
            String listaCartasJugador = obtenerCartasJugador(baraja, palos, cartas, j);
            System.out.println("\nCartas jugador " + j + ": " + listaCartasJugador);
            do {
                System.out.print("¿Qué carta lanzas?: ");
                String cartaLanzada = leerCartaLanzada();
                boolean formatoCorrecto = comprobarFormato(cartaLanzada, listaCartasJugador);
                if (formatoCorrecto) {
                    totalCartasLanzadas[j - 1] = cartaLanzada;
                    break;
                }

            } while (true);

        }
        //----------------------------------------------

        int[] puntuaciones = comprobarPuntuacion(totalCartasLanzadas, palosPrefix, paloDominante, JUGADORES);
        int mayorPuntuacion = mayorNumero(puntuaciones);

        imprimirGanadores(puntuaciones, mayorPuntuacion);

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

    public boolean comprobarFormato(String cartaLanzada, String listaCartasJugador) {
        if (cartaLanzada.length() == 2 && listaCartasJugador.contains(cartaLanzada)) {
            return true;
        }
        return false;
    }

    public void imprimirGanadores(int[] puntuacion, int mayorPuntuacion) {
        System.out.print("El jugador ");

        int contadorGanadores = 0;
        for (int i = 0; i < puntuacion.length; i++) {

            if (puntuacion[i] >= mayorPuntuacion) {
                if (contadorGanadores == 0) {
                    System.out.print(i + 1);
                } else {
                    System.out.print(", " + (i + 1));
                }
                contadorGanadores++;
            }
        }
        System.out.print(" gana la partida\n");
    }

    public int mayorNumero(int[] numeros) {
        int value = 0;
        for (int i : numeros) {

            if (i > value) {
                value = i;
            }
        }
        return value;
    }

    public boolean hayCartasEnPaloDom(String[] cartasLanzadas, String simboloPaloDom) {
        for (String carta : cartasLanzadas) {
            String prefijoPalo = carta.substring(1);
            if (prefijoPalo.equals(simboloPaloDom)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayCartasConValorPaloDom(String[] cartasLanzadas, String simboloPaloDom) {
        for (String carta : cartasLanzadas) {
            String simboloCarta = carta.substring(0, 1);
            String prefijoPalo = carta.substring(1);

            if (prefijoPalo.equals(simboloPaloDom) && obtenerValorCarta(simboloCarta) > 0) {
                return true;
            }
        }

        return false;
    }

    public int[] obtenerCartasIndices(String[] cartasLanzadas, String simboloPaloDom, int numJugadores) {
        int[] puntos = new int[numJugadores];

        for (int i = 0; i < cartasLanzadas.length; i++) {
            String simboloCarta = cartasLanzadas[i].substring(0, 1);
            String prefijoPalo = cartasLanzadas[i].substring(1);

            if (!"".equals(simboloPaloDom)) {
                if (prefijoPalo.equals(simboloPaloDom)) {
                    puntos[i] = obtenerIndiceCarta(simboloCarta);
                }
            } else {
                puntos[i] = obtenerIndiceCarta(simboloCarta);
            }
        }
        return puntos;
    }

    public int[] obtenerCartasPuntos(String[] cartasLanzadas, String simboloPaloDom, int numJugadores) {
        int[] puntos = new int[numJugadores];

        for (int i = 0; i < cartasLanzadas.length; i++) {
            String simboloCarta = cartasLanzadas[i].substring(0, 1);
            String prefijoPalo = cartasLanzadas[i].substring(1);

            if (!"".equals(simboloPaloDom)) {
                if (prefijoPalo.equals(simboloPaloDom) && obtenerValorCarta(simboloCarta) > 0) {
                    puntos[i] = obtenerValorCarta(simboloCarta);
                }
            } else {
                if (obtenerValorCarta(simboloCarta) > 0) {
                    puntos[i] = obtenerValorCarta(simboloCarta);
                }
            }
        }
        return puntos;
    }

    public int[] comprobarPuntuacion(String[] cartasLanzadas, String[] palosPrefix, int indicePaloDom, int numJugadores) {
        int[] puntuaciones = new int[numJugadores];
        String simboloPaloDom = palosPrefix[indicePaloDom];

        if (hayCartasEnPaloDom(cartasLanzadas, simboloPaloDom)) {
            if (hayCartasConValorPaloDom(cartasLanzadas, simboloPaloDom)) {
                int[] cartasPuntos = obtenerCartasPuntos(cartasLanzadas, simboloPaloDom, JUGADORES);
                puntuaciones = cartasPuntos;
            } else {
                int[] cartasIndices = obtenerCartasIndices(cartasLanzadas, simboloPaloDom, JUGADORES);
                puntuaciones = cartasIndices;
            }
        } else {
            int[] cartasPuntos = obtenerCartasPuntos(cartasLanzadas, "", JUGADORES);

            int suma = 0;
            for (int i : cartasPuntos) {
                suma += i;
            }

            // Si los puntos de las cartas no son cero
            if (suma != 0) {
                puntuaciones = cartasPuntos;
            } else {
                int[] cartasIndices = obtenerCartasIndices(cartasLanzadas, "", JUGADORES);
                puntuaciones = cartasIndices;
            }

        }
        return puntuaciones;
    }
    public int[] cartasValores() {
        int[] valores = {10, 0, 0, 0, 5, 0, 0, 0, 0, 2, 3, 4};
        return valores;
    }
    
    public String[] cartas() {
        String[] cartas = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "S", "C", "R"};
        return cartas;
    }

    public String[] palos() {
        String[] palos = {"OROS", "ESPADAS", "BASTOS", "COPAS"};
        return palos;

    }

    public String[] palosPrefix() {
        String[] palosPrefix = {"O", "E", "B", "C"};
        return palosPrefix;
    }
    
    public int obtenerValorCarta(String simbolo)
    {
        String[] cartas = cartas();
        int[] valoresCartas = cartasValores();
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i].equals(simbolo))
                return valoresCartas[i];
        }
        return 0;
    }
    
    public int obtenerIndiceCarta(String simbolo)
    {
        String[] cartas = cartas();
        int[] valoresCartas = cartasValores();
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i].equals(simbolo))
                return i + 1;
        }
        return 0;
    }
}
