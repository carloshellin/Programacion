package objetos.plantas;

import interfaz.Posicion;
import juego.Juego;
import objetos.Guisante;
import objetos.Objeto;

/**
 * Clase que recoge todos los elementos de LanzaGuisantes.
 */
public class LanzaGuisantes extends Planta {  
    /** Atributo estático para el coste del LanzaGuisantes */
    private static final int COSTE = 50;
    
    /** Atributo para el alcance de disparo */
    private int alcance;

    /**
     * Método constructor de LanzaGuisantes.
     * @param posicion posicion en formato fila-columna donde se situa
     */
    public LanzaGuisantes(Posicion posicion) {
        super(1, 3, 1, "lanzaguisantes.gif", posicion);
        alcance = 1;
    }
    
    /**
     * Define el daño que causa a los zombies.
     * @param objeto datos del zombie
     */
    @Override
    public void comportamiento(Objeto objeto) {
        Posicion posicion = new Posicion(getPosicion().getX() + getAncho(), getPosicion().getY() + 8);
        Guisante guisante = new Guisante(posicion);
        objeto.golpear(getDano());
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
            Juego.getTablero().agregarLanzaGuisantes(this);
        }
        
        return resultado;
    }

    /**
     * Método toString de lanzaGuisantes.
     * 
     * @return datos de lanzaGuisantes
     */
    @Override
    public String toString() {
        return "L" + super.toString();
    }
    
    /**
     * Obtener el valor de alcance
     *
     * @return el valor de alcance
     */
    public int getAlcance() {
        return alcance;
    }

    /**
     * Modifica el valor de alcance
     *
     * @param alcance nuevo valor de alcance
     */
    public void setAlcance(int alcance) {
        this.alcance = alcance;
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
