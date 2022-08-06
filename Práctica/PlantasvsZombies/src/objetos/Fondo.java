package objetos;

import interfaz.Sprite;
import objetos.botones.Ok;

/**
 * Clase que recoge los elementos del fondo.
 */
public class Fondo extends Sprite {

    /**
     * Método constructor de fondo.
     * 
     * @param fichero fichero
     * @param ancho valor de la anchura
     * @param alto valor de la altura
     */
    public Fondo(String fichero, int ancho, int alto) {
        super(fichero, ancho, alto);
        Ok ok = new Ok();
    }
}
