package objetos;

import interfaz.Posicion;
import interfaz.Sprite;
import java.awt.Rectangle;

/**
 * Clase que recoge todos los elementos de tile (cuadrados del tablero).
 */
public class Tile extends Sprite {
    /** Atributo que referencia el objeto */
    private Objeto objeto;

    /**
     * Método constructor de tiles.
     * 
     * @param x valor del eje x
     * @param y valor del eje y
     * @param ancho valor de la anchura
     * @param alto valor de la altura
     */
    public Tile(int x, int y, int ancho, int alto) {
        super(null, new Posicion(x, y));
        setRectangulo(new Rectangle(x, y, ancho, alto));
    }
    
    /**
     * Obtiene el valor del objeto.
     * 
     * @return el valor del objeto
     */
    public Objeto getObjeto() {
        return objeto;
    }

    /**
     * Modifica el valor del objeto.
     * 
     * @param objeto nuevo valor del objeto
     */
    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }
}
