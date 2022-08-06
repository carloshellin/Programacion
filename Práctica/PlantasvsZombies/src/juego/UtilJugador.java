package juego;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import objetos.Tablero;

/**
 * Clase que recoge los elementos de la clase utilJugador
 */
public class UtilJugador {
    /** Argumento para crear el hashmap */
    private static HashMap<String, Jugador> jugadores = new HashMap<>();
    
    /**
     * Método para dar de alta a un jugador.
     * 
     * @param jugador 
     * @return true o false
     */
    public static boolean altaJugador(Jugador jugador) {
        if (!jugadores.containsKey(jugador.getDni())) {
            jugadores.put(jugador.getDni(), jugador);
            UtilJugador.guardarDatos();
            return true;
        }
        
        return false;
    }
    
    /**
     * Método para cargar los datos de los jugadores.
     */
    public static void cargarDatos() {
        try {
            try (FileInputStream fileInputStream = new FileInputStream("jugadores.dat")) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                jugadores = (HashMap) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } 
    }
    
    /**
     * Método para cargar una partida ya iniciada anteriormente.
     * @param jugador
     * @return la partida
     */
    public static Tablero cargarPartida(Jugador jugador) {
        try {
            try (FileInputStream fileInputStream = new FileInputStream("guardado-" + jugador.getDni() + ".dat")) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Tablero tablero = (Tablero) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
                return tablero;
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } 
        
        return null;
    }
    
    /**
     * Método para guardar los datos de los jugadores.
     */
    public static void guardarDatos() {
        try {
            if (!jugadores.isEmpty()) {
                try (FileOutputStream fileOutputStream = new FileOutputStream("jugadores.dat")) {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(jugadores);
                    fileOutputStream.close();
                    objectOutputStream.close();
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        }
    }
    
    /**
     * Método para guardar la partida.
     * 
     * @param tablero tablero
     */
    static void guardarPartida(Tablero tablero) {
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream("guardado-" + Juego.getTablero().getJugador().getDni() + ".dat")) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(tablero);
                fileOutputStream.close();
                objectOutputStream.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        }
    }
    
    /**
     * Método para eliminar la partida.
     * 
     * @param jugador jugador
     */
    public static void eliminarPartida(Jugador jugador) {
        try {
            File fichero = new File("guardado-" + Juego.getTablero().getJugador().getDni() + ".dat");
            fichero.delete();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Método para crear una ficha.
     * 
     * @param jugador jugador
     * @throws IOException  excepciones
     */
    public static void generarFicha(Jugador jugador) throws IOException {
        try {
            FileWriter fw = new FileWriter("ficha-" + jugador.getDni() + ".txt");
            try (PrintWriter salida = new PrintWriter(new BufferedWriter(fw))) {
                salida.print("-------------------------------- Ficha jugador --------------------------------");
                salida.print("\n");
                salida.print("DNI: " + jugador.getDni());
                salida.print("\n");
                salida.print("Nombre: " + jugador.getNombre());
                salida.print("\n");
                salida.print("-------------------------------- Partidas y puntos ----------------------------");
                salida.print("\n");
                for (Partida partida : jugador.getPartidas()) {
                    salida.print(partida.toString());
                    salida.print("\n");
                }
                salida.print("-------------------------------------------------------------------------------");
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        }
    }
    
    /**
     * Obtiene el valor de jugadores.
     * 
     * @return el valor de jugadores
     */
    public static HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Modifica el valor de jugadores.
     * 
     * @param jugadores nuevo valor de jugadores
     */
    public static void setJugadores(HashMap<String, Jugador> jugadores) {
        UtilJugador.jugadores = jugadores;
    }

}
