package objetos.plantas;

import interfaz.Posicion;
import juego.Juego;
import objetos.Objeto;

/**
 * Clase que define todos los elementos del girasol.
 */
public class Girasol extends Planta {
    /** Atributo estático para el coste del Girasol */
    private static final int COSTE = 20;
    
    /**
     * Método constructor de Girasol.
     * 
     * @param posicion formato fila-columna donde se situa 
     */        
    public Girasol(Posicion posicion) {
        super(2, 1, 0, "girasol.gif", posicion);
    }

    /**
     * Define en que medida se aumentan los soles con un girasol.
     * 
     * @param objeto es null
     */
    @Override
    public void comportamiento(Objeto objeto) {
        Juego.getTablero().aumentarSoles(10);
    }

    /**
     * Define que pasa cuando clickamos.
     * @param x valor del eje x
     * @param y valor del eje y
     * @return resultado
     */
    @Override
    public boolean click(int x, int y) {
        boolean resultado = super.click(x, y);
        if (resultado) {
            Juego.getTablero().agregarGirasol(this);
        }
        
        return resultado;
    }
    
    /**
     * Método toString de Girasol.
     * 
     * @return  datos de Girasol
     */
    @Override
    public String toString() {
        return "G" + super.toString();
    }
    
    /**
     * Obtener el valor de COSTE
     *
     * @return el valor de COSTE
     */
    public static int getCoste() {
        return COSTE;
    }
}
