package excepciones;
/**
 * Clase que engloba las excepciones con respecto al funcionamiento del juego.
 */

public class ExcepcionJuego extends Exception {
    /**
     * Recibe una frase que define la excepción que se esta recogiendo.
     * @param msg frase que define el error
     */
    public ExcepcionJuego(String msg){
        super(msg);
    }
}
