package objetos.botones;

import interfaz.Posicion;
import interfaz.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import juego.Juego;

/**
 * Clase que recoge los elementos de pasarTurno.
 */
public class PasarTurno extends Sprite {
    /** Atributo booleano que marca si algo es visible o no */
    private boolean visible;

    /**
     * Método constructor de pasarTurno.
     */
    public PasarTurno() {
        super("pasar-turno.png", new Posicion(575, 32));
        this.visible = true;
    }
    
    /**
     * Pasa de turno cuando le clickan.
     * 
     * @param x valor del eje x
     * @param y valor del eje y 
     * @return el siguiente turno o false
     */
    @Override
    public boolean click(int x, int y) {
        if (visible) {
            return Juego.getTablero().siguienteTurno();
        }
        
        return false;
    }

    /**
     * Método que dibuja lo que es visible.
     * 
     * @param g graficos
     */
    @Override
    public void dibujar(Graphics g) {
        if (visible) {
            super.dibujar(g);

            g.setFont(new Font("Purisa", Font.PLAIN, 20));
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(Juego.getTablero().getTurno()), 638, 90);
        }
    }
    
    /**
     * Obtiene si es visible.
     * 
     * @return true si es visible, false en caso contrario
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Modifica el valor de visible.
     * 
     * @param visible nuevo valor de visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
