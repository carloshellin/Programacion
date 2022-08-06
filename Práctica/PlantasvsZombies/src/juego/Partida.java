package juego;

import java.io.Serializable;
import objetos.Tablero.Dificultad;

/**
 * Clase que recoge los elementos de una partida.
 */
public class Partida implements Serializable {
    /** Atributo que recoge los posibles resultados */
    public enum Resultado {
        GANADA, PERDIDA
    }
    /** Atributo que referencia el resultado */
    private Resultado resultado;
    /** Atributo que referencia la dificultad*/
    private Dificultad dificultad;
    /** Atributo que recoge los puntos */
    private int puntos;

    /**
     * Método constructor de la clase partida.
     * 
     * @param resultado resultado de la partida
     * @param dificultad nivel de dificultad 
     * @param puntos numero de puntos
     */
    public Partida(Resultado resultado, Dificultad dificultad, int puntos) {
        this.resultado = resultado;
        this.dificultad = dificultad;
        this.puntos = puntos;
    }

    /**
     * Método toString de partida.
     * 
     * @return el resultado, dificultad y los puntos obtenidos
     */
    @Override
    public String toString() {
        return "Resultado: " + resultado.toString() + " Dificultad: " + dificultad.toString() + " Puntos: " + puntos;
    }

    /**
     * Obtiene el valor del resultado.
     * 
     * @return el valor del resultado
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Modifica el valor de resultado.
     * 
     * @param resultado nuevo valor de resultado
     */
    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }    

    /**
     * Obtiene el valor de dificultad.
     * 
     * @return el valor de dificultad
     */
    public Dificultad getDificultad() {
        return dificultad;
    }

    /**
     * Modifica el valor de dificultad.
     * 
     * @param dificultad nuevo valor de dificultad
     */
    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }        

    /**
     * Obtiene el valor de los puntos.
     * 
     * @return el valor de los puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Modifica el valor de puntos.
     * 
     * @param puntos nuevo valor de puntos
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
