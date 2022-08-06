package interfaz;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Clase que recoge los eventos que realiza el ratón.
 */
public class Entrada implements MouseMotionListener, MouseListener {
    /** Atributo que recoge los datos del raton */
    private Posicion raton;
    
    /**
     * Método constructor de la clase entrada.
     */
    public Entrada() {
        this.raton = new Posicion(0, 0);
    }
    
    /**
     * Recoge lo que arrastra el ratón.
     * @param evento movimiento del raton
     */
    @Override
    public void mouseDragged(MouseEvent evento) {
        
    }

    /**
     * Recoge el desplazamiento del ratón.
     * @param evento movimiento del ratón
     */
    @Override
    public void mouseMoved(MouseEvent evento) {
        raton.set(evento.getX(), evento.getY());
        
        if (Ventana.isDEBUG()) {
            System.out.println(evento.getX() + " " + evento.getY());   
        }
    }

    /**
     * Recoge cuando se coge y suelta algo con el ratón
     * @param evento movimiento del ratón
     */
    @Override
    public void mouseClicked(MouseEvent evento) {
        ArrayList<Sprite> sprites = Ventana.getSprites();
        if (sprites != null) {
            for (int i = 0; i < sprites.size(); i++) {
                Rectangle rectangulo = new Rectangle(evento.getX(), evento.getY(), 1, 1);
                boolean colision = sprites.get(i).getRectangulo().intersects(rectangulo);
                if (colision) {
                    if (sprites.get(i).click(evento.getX(), evento.getY())) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Recoge cuando se pulsa un botón.
     * @param evento movimiento del ratón
     */
    @Override
    public void mousePressed(MouseEvent evento) {
        
    }

    /**
     * Recoge cuando se suelta un botón.
     * @param evento movimiento del ratón
     */
    @Override
    public void mouseReleased(MouseEvent evento) {
        
    }

    /**
     * Recoge cuando se entra a la ventana.
     * @param evento movimiento del ratón
     */
    @Override
    public void mouseEntered(MouseEvent evento) {
        
    }

    /**
     * Recoge cuando el ratón sale de la ventana.
     * @param evento movimiento del raton
     */
    @Override
    public void mouseExited(MouseEvent evento) {
        
    }
    
    /**
     * Obtiene el valor del raton
     * @return el valor del raton
     */
    public Posicion getRaton() {
        return raton;
    }

    /**
     * Modifica el valor del raton
     * @param raton nuevo valor del raton
     */
    public void setRaton(Posicion raton) {
        this.raton = raton;
    }
}
