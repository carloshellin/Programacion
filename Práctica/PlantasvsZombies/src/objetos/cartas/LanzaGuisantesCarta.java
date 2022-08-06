package objetos.cartas;

import interfaz.Posicion;
import interfaz.Sprite;
import juego.Juego;
import objetos.plantas.LanzaGuisantes;

/**
 * Clase que recoge todos los elementos de la carta de lanzaguisantes.
 */
public class LanzaGuisantesCarta extends Sprite {
    /**
     * Método constructor de carta lanzaguisantes.
     */
    public LanzaGuisantesCarta() {
        super("lanzaguisantes-carta.png", new Posicion(180, 38));
    }
    
    /**
     * Define que pasa cuando clickamos.
     * 
     * @param x  valor del eje x
     * @param y valor del eje y
     * @return  true
     */
    @Override
    public boolean click(int x, int y) {
        if (Juego.getTablero().getSoles() >= LanzaGuisantes.getCoste() && !Juego.getTablero().isColocando()) {
            LanzaGuisantes lanzaGuisantes = new LanzaGuisantes(new Posicion(x, y));
            Juego.getTablero().disminuirSoles(LanzaGuisantes.getCoste());
            Juego.getTablero().setColocando(true);
            return true;
        }
        
        return true;
    }
    
}
