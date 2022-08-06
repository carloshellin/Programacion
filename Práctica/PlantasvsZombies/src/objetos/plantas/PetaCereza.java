package objetos.plantas;

import interfaz.Posicion;
import juego.Juego;
import objetos.Objeto;
import objetos.zombies.Zombie;

/**
 * Clase que recoge todos los elementos de PetaCereza.
 */
public class PetaCereza extends Planta {
/** Atributo estático para el coste de PetaCereza */
    private static final int COSTE = 50;
    
    /** Atributo para el alcance de disparo */
    private int alcance;

    /**
     * Método constructor de Petacereza.
     * 
     * @param posicion posicion en formato fila-columna donde se situa
     */
    public PetaCereza(Posicion posicion) {
        super(2, 2, 10, "petacereza.png", posicion);
        alcance = 1;
    }
    
    /**
     * Define el daño que causa a los zombies.
     * 
     * @param objeto datos del zombie
     */
    @Override
    public void comportamiento(Objeto objeto) {
        Zombie zombie = (Zombie) objeto;
        zombie.golpear(getDano());
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
            Juego.getTablero().agregarPetaCereza(this);
        }
        
        return resultado;
    }

    /**
     * Método toString de PetaCereza.
     * 
     * @return datos de petacereza
     */
    @Override
    public String toString() {
        return "P" + super.toString();
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

