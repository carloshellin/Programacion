package objetos.zombies;

/**
 * Clase que define los elementos de ZombieDeportista heredado de Zombie
 */
public class ZombieDeportista extends Zombie { 
    
    /**
     * Método constructor de ZombieDeportista.
     * 
     * @param turnoEnSalir heredado de zombie
     */
    public ZombieDeportista (int turnoEnSalir) {
        super(2, 1, "zombie-deportista.png", turnoEnSalir, 0);
        setCiclo(-1);
    }    
}
