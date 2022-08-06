package interfaz;

import java.io.Serializable;

/**
 * Clase que recoge la posición en formato fila y columna.
 */
public class Posicion implements Serializable {
    /** Atributo para la posición x */
    private int x;    
    /** Atributo para la posición y */
    private int y;

    /**
     * Método constructor de Posicion.
     * 
     * @param x número de fila
     * @param y número de columna
     */
    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Modifica el valor de x e y
     *
     * @param x nuevo valor de x
     * @param y nuevo valor de y
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Obtiene el valor de y
     *
     * @return el valor de y
     */
    public int getY() {
        return y;
    }

    /**
     * Modifica el valor de y
     *
     * @param y nuevo valor de y
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Obtiene el valor de x
     *
     * @return el valor de x
     */
    public int getX() {
        return x;
    }

    /**
     * Modifica el valor de x
     *
     * @param x nuevo valor de x
     */
    public void setX(int x) {
        this.x = x;
    }
    
}
