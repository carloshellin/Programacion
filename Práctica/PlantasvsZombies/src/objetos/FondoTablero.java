package objetos;

import interfaz.Posicion;
import interfaz.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import juego.Juego;

/**
 * Clase que recoge los elementos del fondo del tablero.
 */
public class FondoTablero extends Sprite {
    /** 
     * Método constructor del fondo del tablero.
     */
    public FondoTablero() {
           super("fondo.png", new Posicion(0, 30));
    } 
       
    /**
     * Dibuja el fondo del tablero de juego.
     * 
     * @param g graficos
    */
    @Override
    public void dibujar(Graphics g) {
        super.dibujar(g);
        
        g.setFont(new Font("Purisa", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(Juego.getTablero().getSoles()), 50, 126);
    }
    
}
