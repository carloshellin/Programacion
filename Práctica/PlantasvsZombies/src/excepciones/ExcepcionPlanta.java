package excepciones;
/**
 * Clase que engloba las excepciones con respecto al funcionamiento de las plantas.
 */

public class ExcepcionPlanta extends Exception {
    /**
     * Recibe una frase que define la excepción que se esta recogiendo.
     * @param msg frase que define el error
     */
    public ExcepcionPlanta(String msg){
        super(msg);
    }

}
