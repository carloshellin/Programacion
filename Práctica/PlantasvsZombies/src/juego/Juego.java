package juego;

import objetos.Tablero;
import interfaz.Ventana;
import interfaz.pantallas.Inicio;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import objetos.FondoTablero;
import objetos.botones.VerRanking;
import objetos.Tablero.Dificultad;
import objetos.botones.PasarTurno;
import objetos.cartas.GirasolCarta;
import objetos.cartas.LanzaGuisantesCarta;
import objetos.cartas.NuezCarta;
import objetos.cartas.PetaCerezaCarta;

/**
 * Clase encargada de realizar el juego.
 */
public class Juego {
    /** Atributo que relaciona con el Tablero */
    private static Tablero tablero;
    /** Atributo que referencia la ventana */
    private static Ventana ventana;
    /** Atributo que referencia el inicio */
    private Inicio inicio;    
    /** Atributo que referencia el hilo */
    private static Thread hilo;
    /** Atributo que referencia pasarTurno */
    private static PasarTurno pasarTurno;
  
    /**
     * Método que crea el juego.
     */
    public Juego() {
        UtilJugador.cargarDatos();
        
        ventana = new Ventana("Plants vs Zombies", 1000, 752);
        inicio = new Inicio(this);
        ventana.add(inicio);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (tablero != null) {
                    if (!tablero.isTerminado()) {
                        UtilJugador.guardarPartida(tablero);
                    } else {
                        UtilJugador.eliminarPartida(tablero.getJugador());
                    }
                }
                UtilJugador.guardarDatos();
                System.exit(0);
            }
        });
    }
    
    /**
     * Método que inica el juego.
     * 
     * @param tablero tablero
     * @param jugador jugador
     * @param dificultad nivel de dificultad
     */
    public void iniciar(Tablero tablero, Jugador jugador, Dificultad dificultad) {
        ventana.remove(inicio);
        Ventana.setSprites(new ArrayList<>());
        FondoTablero fondoTablero = new FondoTablero();
        if (tablero != null) {
            Juego.tablero = tablero;
            tablero.recargarObjetos();
        } else {
            Juego.tablero = new Tablero(5, 9, dificultad);
            Juego.tablero.agregarTiles();
        }
        
        Juego.tablero.setJugador(jugador);
        pasarTurno = new PasarTurno();
        GirasolCarta girasolCarta = new GirasolCarta();
        LanzaGuisantesCarta lanzaGuisantesCarta = new LanzaGuisantesCarta();
        NuezCarta nuezCarta = new NuezCarta();
        PetaCerezaCarta petaCerezaCarta = new PetaCerezaCarta();
        
        VerRanking verRanking = new VerRanking(inicio.getRanking());
        
        hilo = new Thread() {
            public void run() {
              ventana.ejecutar();
            }
        };
        hilo.start();
    }
    
    /**
     * Obtiene el valor de tablero
     *
     * @return el valor de tablero
     */
    public static Tablero getTablero() {
        return tablero;
    }

    /**
     * Modifica el valor de tablero
     *
     * @param tablero nuevo valor de tablero
     */
    public static void setTablero(Tablero tablero) {
        Juego.tablero = tablero;
    }
    
    /**
     * Obtiene el valor de la ventana.
     * 
     * @return el valor de ventana
     */
    public static Ventana getVentana() {
        return ventana;
    }

    /**
     * Modifica el valor de ventana.
     * 
     * @param ventana nuevo valor de ventana
     */
    public static void setVentana(Ventana ventana) {
        Juego.ventana = ventana;
    }
    
    /**
     * Obtiene el valor de inicio.
     * 
     * @return el valor de inicio
     */
    public Inicio getInicio() {
        return inicio;
    }

    /**
     * Modifica el valor de inicio.
     * 
     * @param inicio nuevo valor de inicio
     */
    public void setInicio(Inicio inicio) {
        this.inicio = inicio;
    }
    
    /**
     * Obtiene el valor del hilo.
     * 
     * @return el valor del hilo
     */
    public static Thread getHilo() {
        return hilo;
    }

    /**
     * Modifica el valor del hilo.
     * 
     * @param hilo nuevo valor del hilo
     */
    public static void setHilo(Thread hilo) {
        Juego.hilo = hilo;
    }
    
    /**
     * Obtiene el valor de pasarTurno.
     * 
     * @return valor de pasarTurno
     */
    public static PasarTurno getPasarTurno() {
        return pasarTurno;
    }

    /**
     * Modifica el valor de pasarTurno.
     * 
     * @param pasarTurno nuevo valor de PasarTurno
     */
    public static void setPasarTurno(PasarTurno pasarTurno) {
        Juego.pasarTurno = pasarTurno;
    }
}
