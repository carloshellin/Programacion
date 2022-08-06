package objetos.plantas;

import objetos.Objeto;
import interfaz.Posicion;
import java.awt.Rectangle;
import juego.Juego;
import objetos.Tile;

/**
 * Define los elementos generales de la planta, heredando de objeto.
 */
public abstract class Planta extends Objeto {
    /** Atributo para la frecuencia de las plantas */
    private int frecuencia;    
    /** Atributo que define si esta colocado o no */
    private boolean colocado;

    /**
     * Método constructor de planta.
     * @param frecuencia frecuencia de las plantas
     * @param resistencia resistencia al daño
     * @param dano capacidad de ejercer daño
     * @param fichero nombre de la imagen a cargar
     * @param posicion posicion en formato fila-columna donde se situa
     */
    public Planta(int frecuencia, int resistencia, int dano, String fichero, Posicion posicion) {
        super(resistencia, dano, fichero, posicion);
        this.frecuencia = frecuencia;
        this.colocado = false;
    }

    /**
     * Actualiza la planta.
     * 
     * @param raton valor del raton
     */
    @Override
    public void actualizar(Posicion raton) {
        super.actualizar(raton);
   
        if (!colocado) {
            Posicion posicion = new Posicion(raton.getX() - (getAncho() / 2), raton.getY() - (getAlto() / 2));
            setPosicion(posicion);
        }
    }

    /**
     * Define que pasa cuando clickamos.
     * @param x valor del eje x
     * @param y valor del eje y
     * @return true o false 
     */
    @Override
    public boolean click(int x, int y) {
        Rectangle raton = new Rectangle(x, y, 1, 1);
        Tile[][] tileset = Juego.getTablero().getMatriz();
        
        for (int fila = 0; fila < tileset.length; fila++) {
            for (int columna = 0; columna < tileset[fila].length - 1; columna++) {
                Rectangle rectangulo = tileset[fila][columna].getRectangulo();
                boolean colision = raton.intersects(rectangulo);
                if (colision && tileset[fila][columna].getObjeto() == null) {
                    setPosicionMatriz(new Posicion(columna, fila));
                    setPosicion(new Posicion(rectangulo.x, rectangulo.y));
                    tileset[fila][columna].setObjeto(this);
                    colocado = true;
                    Juego.getTablero().setColocando(false);
                    return true;
                }
            }
         }        
        
        return false;
    }

    /**
     * Obtener el valor de frecuencia
     *
     * @return el valor de frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * Modificar el valor de frecuencia
     *
     * @param frecuencia nuevo valor de frecuencia
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * Obtiene el valor de colocado.
     * 
     * @return el valor de colocado
     */
    public boolean isColocado() {
        return colocado;
    }

    /**
     * Modifica el valos de colocado.
     * 
     * @param colocado nuevo valor de colocado
     */
    public void setColocado(boolean colocado) {
        this.colocado = colocado;
    }

}
