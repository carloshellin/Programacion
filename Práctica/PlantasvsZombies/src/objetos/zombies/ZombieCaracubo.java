package objetos.zombies;

/**
 * Clase que define los elementos de ZombieCaracubo heredado de Zombie
 */
public class ZombieCaracubo extends Zombie { 
    
    /**
     * Método constructor de ZombieCaracubo.
     * 
     * @param turnoEnSalir heredado de zombie
     */
    public ZombieCaracubo (int turnoEnSalir) {
        super(8, 1, "zombie-caracubo.png", turnoEnSalir, 4);
    }
}

