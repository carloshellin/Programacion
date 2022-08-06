package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Clase que recoge todos los elementos del sprite
 */
public abstract class Sprite implements Serializable {
    /** Atributo que referencia la imagen */
    private transient Image imagen;    
    /** Atributo que referencia con la Posicion */
    private Posicion posicion;
    /** Atributo que referencia el rectangulo */
    private Rectangle rectangulo;
    /** Atributo para el ancho */
    private int ancho;
    /** Atributo para el alto */
    private int alto;
    /** Atributo para el fichero */
    private String fichero;
    
    /**
     * Recibe los elementos para crear un sprite
     * 
     * @param fichero fichero
     * @param posicion posicion 
     */
    public Sprite(String fichero, Posicion posicion) {
        imagen = new ImageIcon("recursos/" + fichero).getImage();
        this.ancho = imagen.getWidth(null);
        this.alto = imagen.getHeight(null);
        this.posicion = posicion;
        this.fichero = fichero;
        
        rectangulo = new Rectangle(posicion.getX(), posicion.getY(), ancho, alto);
        Ventana.agregarSprite(this);
    }
    
    /**
     * Recibe los elementos para crear un sprite
     * 
     * @param fichero fichero
     * @param ancho ancho
     * @param alto alto 
     */
    public Sprite(String fichero, int ancho, int alto) {
        this(fichero, new Posicion(0, 0));
        this.ancho = ancho;
        this.alto = alto;
    }
    
    /**
     * Vuelve a cargar los sprites de una partida que se ha dejado sin finalizar.
     */
    public void recargar() {
        imagen = new ImageIcon("recursos/" + fichero).getImage();
        Ventana.agregarSprite(this);
    }
    
    /**
     * Detecta si hay una colision entre dos sprites y llama a colisionCon
     */
    public void colision() {
        for (int i = 0; i < Ventana.getSprites().size(); i++) {
            Sprite sprite = Ventana.getSprites().get(i);
            Rectangle rectangulo = Ventana.getSprites().get(i).getRectangulo();
            boolean colision = rectangulo.intersects(getRectangulo());
            if (colision) {
                colisionCon(sprite);
            }
        }
    }
    
    /**
     * Reutilizado por los que heredan de sprite
     * 
     * @param sprite la ventana de la interfaz
     */
    public void colisionCon(Sprite sprite) {
        
    }
    
    /**
     * Actualiza todos los sprites.
     * 
     * @param raton valor del raton
     */
    public void actualizar(Posicion raton) {
        rectangulo.x = posicion.getX();
        rectangulo.y = posicion.getY();
        
        colision();
    }
    
    /**
     * Dibuja el sprite en pantalla.
     * 
     * @param g graficos
     */
    public void dibujar(Graphics g) {
        if (Ventana.isDEBUG()) {
            Graphics2D g2 = (Graphics2D) g;
        
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(2));
            g.setColor(Color.WHITE);
            g.drawRect(rectangulo.x, rectangulo.y, rectangulo.width, rectangulo.height);
            g2.setStroke(oldStroke);
        }
        
        g.drawImage(imagen, posicion.getX(), posicion.getY(), ancho, alto, null);
    }
    
    /**
     * Método que recibe la posicion en x e y al hacer click
     * 
     * @param x posicion en el eje x
     * @param y posicion en el eje y
     * @return false
     */
    public boolean click(int x, int y) {
        return false;
    }

    /**
     * Obtiene el valor de la imagen
     * 
     * @return el valor de la imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Modifica el valor de la imagen
     * 
     * @param imagen nuevo valor de la imagen
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    /**
     * Obtiene el valor de posicion
     *
     * @return el valor de posicion
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Modifica el valor de posicion
     *
     * @param posicion nueva valor de posicion
     */
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    
    /**
     * Obtiene el valor del rectangulo
     * 
     * @return el valor del rectangulo
     */
    public Rectangle getRectangulo() {
        return rectangulo;
    }

    /**
     * Modifica el valor del rectangulo.
     * 
     * @param rectangulo nuevo valor del rectangulo
     */
    public void setRectangulo(Rectangle rectangulo) {
        this.rectangulo = rectangulo;
    }    
    
    /**
     * Obtiene el valor de la altura
     * 
     * @return el valor de la altura
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Modifica el valor de la altura
     * 
     * @param alto nuevo valor de la altura
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Obtiene el valor de la anchura
     * 
     * @return el valor de la anchura
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Modifica el valor de la anchura
     * 
     * @param ancho nuevo valor de la anchura
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}
