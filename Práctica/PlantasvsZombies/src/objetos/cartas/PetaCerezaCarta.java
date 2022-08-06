package objetos.cartas;

import interfaz.Posicion;
import interfaz.Sprite;
import juego.Juego;
import objetos.plantas.PetaCereza;

/**
 * Clase que recoge los elementos de la carta de petacereza
 */
public class PetaCerezaCarta extends Sprite {
    /**
     * Método constructor de la carta de petacereza.
     */
    public PetaCerezaCarta() {
        super("petacereza-carta.png", new Posicion(316, 38));
    }
    
    /**
     * Define que pasa si clickamos.
     * @param x valor del eje x
     * @param y valor del eje y
     * @return true
     */
    @Override
    public boolean click(int x, int y) {
        if (Juego.getTablero().getSoles() >= PetaCereza.getCoste() && !Juego.getTablero().isColocando()) {
            PetaCereza petaCereza = new PetaCereza(new Posicion(x, y));
            Juego.getTablero().disminuirSoles(PetaCereza.getCoste());
            Juego.getTablero().setColocando(true);
            return true;
        }
        
        return true;
    }
}
