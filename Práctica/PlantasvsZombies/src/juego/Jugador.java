package juego;

import java.io.Serializable;
import java.util.ArrayList;
import objetos.Tablero.Dificultad;

/**
 * Clase que recoge los elementos del jugador.
 */
public class Jugador implements Serializable {  
    /** Atributo que recoge el dni */
    private String dni;
    /** Atributo que recoge el nombre */
    private String nombre;
    /** Atributo que crea un arrayList con las partidas */
    private ArrayList<Partida> partidas;    
    /** Atributo que crea una lista con la puntuacion total */
    private int[] puntuacionTotal;

    
    /**
     * Método contructor del jugador.
     * 
     * @param dni dni del jugador
     * @param nombre nombre del jugador
     */
    public Jugador(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        
        puntuacionTotal = new int[Dificultad.values().length];
        partidas = new ArrayList<>();
    }
    
    /**
     * Método que crea una nueva partida.
     * 
     * @param partida partida
     */
    public void agregarPartida(Partida partida) {
        partidas.add(partida);
        puntuacionTotal[partida.getDificultad().ordinal()] += partida.getPuntos();
        UtilJugador.guardarDatos();
    }

    /**
     * Obtiene el valor del dni.
     * 
     * @return el valor del dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Modifica el valor del dni.
     * 
     * @param dni nuevo valor del dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    /**
     * Obtiene el valor del nombre.
     *
     * @return el valor del nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el valor del nombre.
     * 
     * @param nombre nuevo valor del nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el arrayList de partidas.
     * 
     * @return el arrayList de partidas
     */
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    /**
     * Modifica el arrayList de partidas.
     * 
     * @param partidas nuevo arrayList de partidas
     */
    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    /**
     * Obtiene la lista de puntuacion total.
     * 
     * @return la lista de puntuacion total
     */
    public int[] getPuntuacionTotal() {
        return puntuacionTotal;
    }

    /**
     * Modifica la lista de puntuacion total.
     * 
     * @param puntuacionTotal nueva lista de puntuacion total
     */
    public void setPuntuacionTotal(int[] puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }
}
