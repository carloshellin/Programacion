package objetos.botones;

import interfaz.Posicion;
import interfaz.Sprite;
import interfaz.pantallas.Ranking;

/**
 * Clase que recoge los elementos del ranking.
 */
public class VerRanking extends Sprite { 
    /** Atributo que referencia el ranking */
    private Ranking ranking;
    
    /**
     * Método constructor de ranking.
     * 
     * @param ranking ranking
     */
    public VerRanking(Ranking ranking) { 
        super("ver-ranking.png", new Posicion(854, 32));
        this.ranking = ranking;
    }

    /**
     * Muestra el ranking cuando clickamos.
     * 
     * @param x valor del eje x
     * @param y valor del eje y
     * @return  true
     */
    @Override
    public boolean click(int x, int y) {
        ranking.setVisible(true);
        
        return true;
    }  
    
    /**
     * Obtiene el valor de ranking.
     * 
     * @return el valor de ranking
     */
    public Ranking getRanking() {
        return ranking;
    }

    /**
     * Modifica el valor de ranking.
     * 
     * @param ranking nuevo valor de ranking
     */
    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }
}
