package objetos.botones;

import interfaz.Posicion;
import interfaz.Sprite;
import java.awt.Rectangle;
import juego.Juego;
import juego.UtilJugador;

/**
 * Clase que recoge todos los elementos del boton ok.
 */
public class Ok extends Sprite {

    /**
     * Método constructor de la clase ok.
     */
    public Ok() {
        super(null, new Posicion(440, 654));
        setRectangulo(new Rectangle(440, 654, 120, 51));
    }

    /**
     * Define que pasa cuando clickamos ok.
     * @param x valor del eje x
     * @param y valor del eje y
     * @return  true
     */
    @Override
    public boolean click(int x, int y) {
        UtilJugador.eliminarPartida(Juego.getTablero().getJugador());
        Juego.getHilo().stop();
        Juego.getVentana().dispose();
        Juego juego = new Juego();
        
        return true;
    }
    
    
    
}
