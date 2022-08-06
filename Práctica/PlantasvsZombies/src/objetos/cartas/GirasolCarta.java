package objetos.cartas;

import interfaz.Sprite;
import interfaz.Posicion;
import juego.Juego;
import objetos.plantas.Girasol;

/**
 * Clase que recoge los elementos de la carta de girasoles.
 */
public class GirasolCarta extends Sprite {
    /** 
     * Método conbtructor de la carta de girasoles.
     */
    public GirasolCarta() {
        super("girasol-carta.jpg", new Posicion(112, 38));
    }
    
    /**
     * Define que pasa cuando clickamos.
     * 
     * @param x valor del eje x
     * @param y valor del eje y
     * @return true o false
     */
    @Override
    public boolean click(int x, int y) {
        if (Juego.getTablero().getSoles() >= Girasol.getCoste() && !Juego.getTablero().isColocando()) {
            Girasol girasol = new Girasol(new Posicion(x, y));
            Juego.getTablero().disminuirSoles(Girasol.getCoste());
            Juego.getTablero().setColocando(true);
            return true;
        }
        
        return false;
    }
}
