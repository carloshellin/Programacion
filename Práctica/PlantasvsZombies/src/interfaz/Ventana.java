package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 * Clase que recoge todos los elementos para crear una ventana.
 */
public class Ventana extends JFrame {  
    /** Atributo para crear DEBUG */
    private static boolean DEBUG;  
    /** ArrayList que almacena los sprites */
    private static ArrayList<Sprite> sprites;
    /** Atributo para la anchura */
    private int ancho;
    /** Atributo para la altura */
    private int alto;
    /** Atributo para recoger un bufferedImage */
    private BufferedImage buffer;
    /** Atributo para el border interior */
    private Insets borderInterior;
    /** Atributo para referenciar la entrada */
    private Entrada entrada;

    /**
     * Crea la ventana que se muestra.
     * 
     * @param titulo valor del titulo
     * @param ancho valor de la anchura
     * @param alto valor de la altura
     */
    public Ventana(String titulo, int ancho, int alto) { 
        // Si se ejecuta Main.java en modo Debug entonces DEBUG es true
        DEBUG = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp");
        
        this.ancho = ancho;
        this.alto = alto;

        setTitle(titulo);
        setSize(ancho, alto);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        
        borderInterior = getInsets();
        setSize(borderInterior.left + ancho, borderInterior.top + alto);
        
        buffer = new BufferedImage(borderInterior.left + ancho, borderInterior.top + alto, TYPE_INT_RGB);
                
        entrada = new Entrada();
        addMouseMotionListener(entrada);
        addMouseListener(entrada);
    }
    
    /**
     * Añade un sprite al ArrayList
     * 
     * @param sprite valor del sprite
     */
    public static void agregarSprite(Sprite sprite) {
        sprites.add(sprite);
    }
    
    /**
     * Elimina un sprite del ArrayList.
     * 
     * @param sprite valor del sprite
     */
    public static void eliminarSprite(Sprite sprite) {
        sprites.remove(sprite);
    }
    
    /**
     * Bucle que actualiza y dibuja los sprites cada 16 ms
     */
    public void ejecutar() {
        int framePorSegundos = 60;
        
        while (true) {            
            long tiempo = System.nanoTime();
            
            actualizar();
            dibujar();
            
            long tiempoPasado = System.nanoTime() - tiempo;
            long tiempoDormir = (1000000000 / framePorSegundos) - tiempoPasado;
            
            if (tiempoDormir > 0) {
                try {
                    Thread.sleep(tiempoDormir / 1000000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    
    /**
     * Actualiza los sprites.
     */
    public void actualizar() {       
        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).actualizar(entrada.getRaton());
        }
    }

    /**
     * Dibuja los sprites.
     */
    public void dibujar() {
        Graphics g = getGraphics();

        Graphics bg = buffer.getGraphics();

        bg.setColor(Color.WHITE);
        bg.fillRect(0, 0, ancho, alto);

        
        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).dibujar(bg);
        }
        
        g.drawImage(buffer, 0, 0, this);
        
        bg.dispose();
        g.dispose();
    }
    
    /**
     * Obtiene el valor de debug
     * 
     * @return el valor de debug
     */
    public static boolean isDEBUG() {
        return DEBUG;
    }

    /**
     * Modifica el valor de debug.
     * 
     * @param DEBUG nuevo valor de debug
     */
    public static void setDEBUG(boolean DEBUG) {
        Ventana.DEBUG = DEBUG;
    }
    
    /**
     * Devuelve el arrayList de sprites
     * 
     * @return el arrayList de sprites
     */
    public static ArrayList<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Modifica el arrayList de sprites.
     * 
     * @param sprites nuevo arrayList de sprites
     */
    public static void setSprites(ArrayList<Sprite> sprites) {
        Ventana.sprites = sprites;
    }
    
    /**
     * Obtiene el valor de la anchura.
     * 
     * @return el valor de la anchura
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Modifica el valor de la anchura.
     * 
     * @param ancho nuevo valor de la anchura
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    
    /**
     * Obtiene el valor del buffer.
     * 
     * @return el valor del buffer
     */
    public BufferedImage getBuffer() {
        return buffer;
    }

    /**
     * Modifica el valor del buffer.
     * 
     * @param buffer nuevo valor del buffer
     */
    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }
    
    /**
     * Obtiene el valor del borderInterior.
     * 
     * @return el valor de borderInterior
     */
    public Insets getBorderInterior() {
        return borderInterior;
    }

    /**
     * Modifica el valor de borderInterior.
     * 
     * @param borderInterior nuevo valor de borderInterior
     */
    public void setBorderInterior(Insets borderInterior) {
        this.borderInterior = borderInterior;
    }

    /**
     * Obtiene el valor de la altura.
     * 
     * @return el valor de la altura
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Modifica el valor de la altura.
     * 
     * @param alto nuevo valor de la altura
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }
    
    /**
     * Obtiene el valor de la entrada.
     * 
     * @return el valor de la entrada
     */
    public Entrada getEntrada() {
        return entrada;
    }

    /**
     * Modifica el valor de la entrada.
     * 
     * @param entrada nuevo valor de la entrada
     */
    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }
}
