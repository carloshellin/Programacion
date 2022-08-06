package objetos.cartas;

import interfaz.Posicion;
import interfaz.Sprite;
import juego.Juego;
import objetos.plantas.Nuez;

/**
 * Clase que recoge los elementos de la carta de nuez.
 */
public class NuezCarta extends Sprite {
    /**
     * Método constructor de la carta de nuez.
     */
        public NuezCarta() {
        super("nuez-carta.png", new Posicion(248, 38));
    }
    
    /**
     * Define que pasa cuando clickamos.
     * 
     * @param x valor del eje x
     * @param y valor del eje y
     * @return  true
     */
    @Override
    public boolean click(int x, int y) {
        if (Juego.getTablero().getSoles() >= Nuez.getCoste() && !Juego.getTablero().isColocando()) {
            Nuez nuez = new Nuez(new Posicion(x, y));
            Juego.getTablero().disminuirSoles(Nuez.getCoste());
            Juego.getTablero().setColocando(true);
            return true;
        }
        
        return true;
    }
}
