package objetos;

import interfaz.Posicion;
import interfaz.Sprite;
import interfaz.Ventana;
import juego.Juego;
import objetos.zombies.Zombie;

/**
 * Clase que recoge todos los elementos del guisante.
 */
public class Guisante extends Sprite {
    /**
     * Método constructor de guisante.
     * 
     * @param posicion posicion del guisante
     */
    public Guisante(Posicion posicion) {
        super("guisante.png", posicion);
        Juego.getPasarTurno().setVisible(false);
    }

    /**
     * Actualiza la posicion del guisante.
     * 
     * @param raton valor del raton
     */
    @Override
    public void actualizar(Posicion raton) {
        Posicion posicion = getPosicion();
        posicion.setX(posicion.getX() + 12);
        
        super.actualizar(raton);
    }    

    /**
     * Comprueba si el guisante colisiona con un zombie.
     * 
     * @param sprite sprite
     */
    @Override
    public void colisionCon(Sprite sprite) {
        super.colisionCon(sprite);
        
        if (sprite instanceof Zombie)
        {
            Zombie zombie = (Zombie) sprite;
            if (zombie.getResistencia() == 0) {
                Ventana.eliminarSprite(zombie);
            }
            
            Ventana.eliminarSprite(this);
            for (int i = 0; i < Ventana.getSprites().size(); i++) {
                if (Ventana.getSprites().get(i) instanceof Guisante) {
                    return;
                }
            }
            Juego.getPasarTurno().setVisible(true);
        }
    } 
}
