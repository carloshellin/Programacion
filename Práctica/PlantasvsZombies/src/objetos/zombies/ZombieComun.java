package objetos.zombies;

/**
 * Clase que define los elementos de ZombieComun heredado de Zombie
 */
public class ZombieComun extends Zombie { 
    
    /**
     * Método constructor de ZombieComun.
     * 
     * @param turnoEnSalir heredado de zombie
     */
    public ZombieComun(int turnoEnSalir) {
        super(5, 1, "zombie-comun.png", turnoEnSalir, 2);
    }
}
