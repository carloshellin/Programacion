
package objetos.plantas;

import interfaz.Posicion;
import objetos.Objeto;

/**
 *Clase que define todos los elementos de la nuez.
 */
public class Nuez extends Planta {
/** Atributo estático para el coste de la Nuez */
    private static final int COSTE = 50;
    
    /**
     * Método constructor de Nuez.
     * 
     * @param posicion formato fila-columna donde se situa 
     */
    public Nuez(Posicion posicion) {
        super(1, 10, 0, "nuez.gif", posicion);
    }
    
    /**
     * Método toString de Nuez.
     * 
     * @return  datos de nuez
     */
    @Override
    public String toString() {
        return "U" + super.toString();
    }
    
    /**
     * Obtener el valor de COSTE
     *
     * @return el valor de COSTE
     */
    public static int getCoste() {
        return COSTE;
    }
    
    /**
     * No define nada para Nuez
     * @param objeto null
     */
    @Override
    public void comportamiento(Objeto objeto) {
        
    }
}
