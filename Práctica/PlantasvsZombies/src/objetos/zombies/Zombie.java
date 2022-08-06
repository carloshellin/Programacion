package objetos.zombies;

import objetos.Objeto;
import interfaz.Posicion;

/**
 * Clase que recoge todos los elementos del zombie.
 */
public abstract class Zombie extends Objeto {
    /** Atributo para la velocidad del zombie */
    private int velocidad;    
    /** Atributo para saber en que turno sale */
    private int turnoEnSalir;

    /**
     * Método constructor que recoge el número de turno donde comienza a salir. 
     * @param resistencia resistencia al daño
     * @param dano capacidad de ejercer daño
     * @param fichero nombre de la imagen a cargar
     * @param turnoEnSalir número de turno donde comienza a aparecer
     * @param velocidad número de turno que se empieza a mover
     */
    public Zombie(int resistencia, int dano, String fichero, int turnoEnSalir, int velocidad) {
        super(resistencia, dano, fichero, new Posicion(-100, -100));
        this.turnoEnSalir = turnoEnSalir;
        this.velocidad = velocidad;
    }
    
    /**
     * Método que ejecuta el comportamiento del ZombieComun.
     * 
     * @param objeto el objeto que utiliza
     */
    @Override
    public void comportamiento(Objeto objeto) {
        objeto.golpear(getDano());
    }
    
    /**
     * Método toString de zombie.
     * 
     * @return datos de zombie
     */
    @Override
    public String toString() {
        return "Z" + super.toString();
    }
        
    /**
     * Obtiene el valor de velocidad
     *
     * @return el valor de velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Modifica el valor de velocidad
     *
     * @param velocidad nuevo valor de velocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
    
    /**
     * Obtiene el valor de turnoEnSalir
     *
     * @return el valor de turnoEnSalir
     */
    public int getTurnoEnSalir() {
        return turnoEnSalir;
    }

    /**
     * Modifica el valor de turnoEnSalir
     *
     * @param turnoEnSalir nuevo valor de turnoEnSalir
     */
    public void setTurnoEnSalir(int turnoEnSalir) {
        this.turnoEnSalir = turnoEnSalir;
    }
}
