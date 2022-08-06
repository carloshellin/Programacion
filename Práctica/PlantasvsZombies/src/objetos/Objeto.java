package objetos;

import interfaz.Posicion;
import interfaz.Sprite;

/**
 * Clase que define los elementos generales que tiene cualquier objeto.
 */
public abstract class Objeto extends Sprite {
    /** Atributo para la resistencia del objeto */
    private int resistencia;
    /** Atributo para el daño que causa el objeto */
    private int dano;    
    /** Atributo para los ciclos que se aumentan en cada turno */
    private int ciclo;    
    /** Atributo que referencia con la Posicion de la matriz */
    private Posicion posicionMatriz;

    /**
     * Método constructor de la clase Objeto.
     * 
     * @param resistencia resistencia del objeto
     * @param dano daño que puede causar el objeto
     * @param fichero nombre de la imagen a cargar
     * @param posicion posicion donde se encuentra el objeto
     */
    public Objeto(int resistencia, int dano, String fichero, Posicion posicion) {
        super(fichero, posicion);
        this.resistencia = resistencia;
        this.dano = dano;
        this.ciclo = 0;
    }
    
    /**
     * Método abstracto que implementan las plantas y los zombies que heredan está clase.
     * 
     * @param objeto el objeto que utiliza
     */
    abstract public void comportamiento(Objeto objeto);

    /**
     * Cambia el valor de la resistencia cuando ésta recibe daño.
     * 
     * @param dano daño que puede causar el objeto
     */
    public void golpear(int dano) {
        resistencia -= dano;
    }
    
    /**
     * Aumenta el ciclo.
     */
    public void aumentarCiclo() {
        ciclo++;
    }
    
    /**
     * Método toString de la clase Objeto.
     * 
     * @return el valor de la resistencia
     */
    @Override
    public String toString() {
        return "(" + resistencia + ")";
    }
    
    /**
     * Obtiene el valor de resistencia
     *
     * @return el valor de resistencia
     */
    public int getResistencia() {
        return resistencia;
    }

    /**
     * Modifica el valor de resistencia
     *
     * @param resistencia nueva valor de resistencia
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
    
    /**
     * Obtiene el valor de dano
     *
     * @return el valor de dano
     */
    public int getDano() {
        return dano;
    }

    /**
     * Modifica el valor de dano
     *
     * @param dano nuevo valor de dano
     */
    public void setDano(int dano) {
        this.dano = dano;
    }
    
    /**
     * Obtiene el valor de ciclo
     *
     * @return el valor de ciclo
     */
    public int getCiclo() {
        return ciclo;
    }

    /**
     * Modifica el valor de ciclo
     *
     * @param ciclo nuevo valor de ciclo
     */
    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }
    
    /**
     * Obtiene el valor de la posicion en formato matriz.
     * 
     * @return el valor de la posicion en formato matriz
     */
    public Posicion getPosicionMatriz() {
        return posicionMatriz;
    }

    /**
     * Modifica el valor de la posicion en formato matriz.
     * 
     * @param posicionMatriz  nuevo valor de la posicion en formato matriz
     */
    public void setPosicionMatriz(Posicion posicionMatriz) {
        this.posicionMatriz = posicionMatriz;
    }
}
