package objetos;

import interfaz.Posicion;
import java.util.ArrayList;
import java.util.Iterator;
import objetos.plantas.Girasol;
import objetos.plantas.LanzaGuisantes;
import objetos.plantas.Planta;
import objetos.zombies.Zombie;
import objetos.zombies.ZombieComun;
import interfaz.Ventana;
import java.io.Serializable;
import juego.Jugador;
import juego.Partida;
import objetos.plantas.PetaCereza;
import objetos.zombies.ZombieCaracubo;
import objetos.zombies.ZombieDeportista;

/**
 * Clase que recoge todos los elementos necesarios para crear la interfaz.
 */
public class Tablero implements Serializable {
    /** Establece los valores que puede tomar dificultad */
    public enum Dificultad {
      BAJA, MEDIA, ALTA, IMPOSIBLE  
    };
    
    /** Atributo para los turnos */
    private int turno;
    /** Atributo para los soles totales */
    private int soles;
    /** Atributo con los girasoles dentro de una ArrayList */
    private ArrayList<Girasol> girasoles;
    /** Atributo con los lanzaguisantes dentro de una ArrayList */
    private ArrayList<LanzaGuisantes> lanzasGuisantes;    
    /** Atributo con las petacerezas dentro de una ArrayList */
    private ArrayList<PetaCereza> petaCerezas; 
    /** Atributo con los zombies dentro de una ArrayList */
    private ArrayList<Zombie> zombies;
    /** Atributo con los zombies sin salir dentro de una ArrayList */
    private ArrayList<Zombie> zombiesSinSalir;
    /** Atributo con los números de turnos en los que salen zombies */
    private int turnoReparto;
    /** Atributo con los números de zombies en la partida */    
    private int totalZombis;
    /** Atributo con los números iniciales sin zombies */
    private int turnosSinZombis;
    /** Atributo con la matriz de Tiles */
    private Tile[][] matriz;
    /** Atributo que referencia el nivel de dificultad */
    private Dificultad dificultad;   
    /** Atributo que referencia el jugador */
    private Jugador jugador;    
    /** Atributo que recoge si se esta colocando algo */
    private boolean colocando;    
    /** Atributo que recoge si se ha terminado algo */
    private boolean terminado;

    /**
     * Recibe los elementos para crear el tablero y establece las condiciones de cada uno de ellos.
     * 
     * @param filas número de filas del tablero
     * @param columnas número de columnas del tablero
     * @param dificultad nivel de dificultad de la partida
     */
    public Tablero(int filas, int columnas, Dificultad dificultad) {
        this.dificultad = dificultad;
        this.colocando = false;
        this.terminado = false;
        turno = 0;
        soles = 50;
        matriz = new Tile[filas][columnas];
        girasoles = new ArrayList<>();
        lanzasGuisantes = new ArrayList<>();
        petaCerezas = new ArrayList<>();
        zombies = new ArrayList<>();
        zombiesSinSalir = new ArrayList<>();
        switch (this.dificultad) {
            case BAJA:
                turnoReparto = 30;
                totalZombis = 5;
                turnosSinZombis = 10;
                break;
            case MEDIA:
                turnoReparto = 30;
                totalZombis = 15;
                turnosSinZombis = 7;
                break;
            case ALTA:
                turnoReparto = 30;
                totalZombis = 25;
                turnosSinZombis = 5;
                break;
            case IMPOSIBLE:
                turnoReparto = 30;
                totalZombis = 50;
                turnosSinZombis = 5;
                break;
        }
        
        for (int i = 0; i < totalZombis; i++) {
            int turnoEnSalir = (int) (Math.random() * (turnosSinZombis + turnoReparto) + 1);
            Zombie zombie = null;
            int tipoZombie = (int) (Math.random() * 3);
            switch (tipoZombie){
                case 0:
                    zombie = new ZombieComun(turnoEnSalir);
                    break;
                case 1:
                    zombie = new ZombieCaracubo(turnoEnSalir); 
                    break;
                case 2:
                    zombie = new ZombieDeportista(turnoEnSalir);
                    break;
                default:
                    zombie = new ZombieComun(turnoEnSalir);
                    break;
            }
            zombiesSinSalir.add(zombie);
        }
    }
    
    /**
     * Método para recargar todos los objetos.
     */
    public void recargarObjetos() {
        for (Zombie zombie : zombiesSinSalir) {
            zombie.recargar();
        }
        
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                matriz[fila][columna].recargar();
                if (matriz[fila][columna].getObjeto() != null) {
                    matriz[fila][columna].getObjeto().recargar();
                }
            }
        }
    }
    
    /**
     * Método para agregar cuadrados al tablero.
     */
    public void agregarTiles() {
        // Posición y tamaño de los cuadrados (tiles) del tablero
        int x = 50;
        int y = 144;
        int ancho = 80;
        int alto = 94;
        
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                matriz[fila][columna] = new Tile(x + ((ancho + 22) * columna), y + ((alto + 26) * fila), ancho, alto);
            }
         }
    }

    /**
     * Actualiza todos los elementos de la interfaz cada vez que se cambia de turno, 
     * realizando los cambios corresponientes a cada elemento.
     * 
     * @return false si Plantas o Zombies ganan, por el contrario true.
    */
    public boolean siguienteTurno() {
        if (turno >= turnosSinZombis) {
            if (totalZombis > (zombiesSinSalir.size() - totalZombis)) {
                 for (Iterator<Zombie> i = zombiesSinSalir.iterator(); i.hasNext();) {
                    Zombie zombie = i.next();
                    if (turno >= zombie.getTurnoEnSalir()) {
                        int y = (int) (Math.random() * (matriz.length - 1));
                        int x =  matriz[y].length - 1;
                        if (matriz[y][x].getObjeto() == null) {
                            zombie.setPosicion(matriz[y][x].getPosicion());
                            zombie.setPosicionMatriz(new Posicion(x, y));
                            zombies.add(zombie);
                            matriz[y][x].setObjeto(zombie);
                            i.remove();
                        }
                    }
                }
            }
            
            if (zombiesSinSalir.isEmpty() && zombies.isEmpty()) {
                int puntos = girasoles.size() + lanzasGuisantes.size() + soles;
                jugador.agregarPartida(new Partida(Partida.Resultado.GANADA, dificultad, puntos));
                
                Fondo fondo = new Fondo("fondo-gana.png", 1000, 752);
                terminado = true;
                return false;
            }
        }
        
        for (Girasol girasol : girasoles) {
            if (girasol.getCiclo() == girasol.getFrecuencia()) {
                girasol.comportamiento(null);
                girasol.setCiclo(0);
            } else {
                girasol.aumentarCiclo();
            }
        }
        
        for (Iterator<PetaCereza> i = petaCerezas.iterator(); i.hasNext();) {
            PetaCereza petaCereza = i.next();
            if (petaCereza.getCiclo() == petaCereza.getFrecuencia()){

                Posicion posicion = petaCereza.getPosicionMatriz();

                int y = posicion.getY() - 1;
                int yRango = 2;
                if (y < 0) {
                    y += 1;
                } else if (y == matriz.length - 2) {
                    yRango = 1;
                }

                for (; y < posicion.getY() + yRango; y++){
                    int x = posicion.getX() - 1;
                    if (x < 0) {
                        x += 1;
                    }
                    for (; x < posicion.getX() + 2; x++) {
                        if (matriz[y][x].getObjeto() instanceof Zombie) {
                            Zombie zombie = (Zombie) matriz[y][x].getObjeto();
                            petaCereza.comportamiento(zombie);

                            if (zombie.getResistencia() <= 0){
                                matriz[y][x].setObjeto(null);
                                zombies.remove(zombie);
                                Ventana.eliminarSprite(zombie);
                            }
                        }
                    }
                }

                matriz[posicion.getY()][posicion.getX()].setObjeto(null);
                Ventana.eliminarSprite(petaCereza);
                i.remove();
            } else {
                petaCereza.aumentarCiclo();
            }    
        }
        
        for (LanzaGuisantes lanzaGuisantes : lanzasGuisantes) {
            if (lanzaGuisantes.getCiclo() == lanzaGuisantes.getFrecuencia()) {
                lanzaGuisantes.setCiclo(0);
                
                Posicion posicion = lanzaGuisantes.getPosicionMatriz();
                int y = posicion.getY();

                for (int x = posicion.getX() + 1; x < matriz[y].length; x++) {
                    if (matriz[y][x].getObjeto() instanceof Zombie) {
                        Zombie zombie = (Zombie) matriz[y][x].getObjeto();
                        lanzaGuisantes.comportamiento(zombie);

                        if (zombie.getResistencia() <= 0) {
                            matriz[y][x].setObjeto(null);
                            zombies.remove(zombie);
                        }

                        break;
                    }
                }
            } else {
                lanzaGuisantes.aumentarCiclo();
            }
        }
                
        for (Zombie zombie : zombies) {
            Posicion posicion = zombie.getPosicionMatriz();
            int x = posicion.getX();
            int y = posicion.getY();
            
            if (x == 0) {
                int puntos = girasoles.size() + lanzasGuisantes.size() + soles;
                jugador.agregarPartida(new Partida(Partida.Resultado.PERDIDA, dificultad, puntos));
                
                Fondo fondo = new Fondo("fondo-pierde.png", 1000, 752);
                terminado = true;
                return false;
            }
            
            if (matriz[y][x - 1].getObjeto() == null) {
                if (zombie.getCiclo() == zombie.getVelocidad()) {
                    posicion.set(x - 1, y);
                    zombie.setPosicion(matriz[y][x - 1].getPosicion());
                    matriz[y][x - 1].setObjeto(matriz[y][x].getObjeto());
                    matriz[y][x].setObjeto(null);
                    zombie.setCiclo(0);
                } else {
                    zombie.aumentarCiclo();
                }
            } else if (matriz[y][x - 1].getObjeto() instanceof Planta) {
                Planta planta = (Planta) matriz[y][x - 1].getObjeto();
                zombie.comportamiento(planta);
                if (planta.getResistencia() <= 0) {
                    matriz[y][x - 1].setObjeto(null);
                    Ventana.eliminarSprite(planta);
                    if (planta instanceof LanzaGuisantes) {
                        lanzasGuisantes.remove((LanzaGuisantes) planta);
                    } else if (planta instanceof Girasol) {
                        girasoles.remove((Girasol) planta);
                    }
                }
            }
        }
        
        turno++;
            
        return true;
    }

    /**
     * Método para añadir un girasol.
     * 
     * @param girasol girasol
     */
    public void agregarGirasol(Girasol girasol) {
        girasoles.add(girasol);
        siguienteTurno();
    }
    
    /**
     * Método para agregar un lanzaguisantes.
     * 
     * @param lanzaGuisantes lanzaguisantes
     */
    public void agregarLanzaGuisantes(LanzaGuisantes lanzaGuisantes) {
        lanzasGuisantes.add(lanzaGuisantes);
        siguienteTurno();
    }
    
    /**
     * Método para agregar una petacereza.
     * 
     * @param petaCereza petacereza
     */
    public void agregarPetaCereza(PetaCereza petaCereza) {
        petaCerezas.add(petaCereza);
        siguienteTurno();
    }
    
    /** 
     * Aumenta los soles mediante la frecuencia.
     * 
     * @param frecuencia frecuencia con la que se aumentan los soles
     */
    public void aumentarSoles(int frecuencia) {
        soles += frecuencia;
    }
    
    /**
     * Disminuye los soles mediante la frecuencia.
     * 
     * @param frecuencia frecuencia con la que se disminuyen los soles
     */
    public void disminuirSoles(int frecuencia) {
        soles -= frecuencia;
    }
       
    /**
     * Obtiene el valor turno
     *
     * @return el valor de turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Modifica el valor de turno
     *
     * @param turno nuevo valor de turno
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    /**
     * Obtiene el valor de soles
     *
     * @return el valor de soles
     */
    public int getSoles() {
        return soles;
    }

    /**
     * Modifica el valor de soles
     *
     * @param soles nuevo valor de soles
     */
    public void setSoles(int soles) {
        this.soles = soles;
    }
    

    /**
     * Obtiene el valor de matriz
     *
     * @return el valor de matriz
     */
    public Tile[][] getMatriz() {
        return matriz;
    }

    /**
     * Modifica el valor de matriz
     *
     * @param matriz nuevo valor de matriz
     */
    public void setMatriz(Tile[][] matriz) {
        this.matriz = matriz;
    }
    
    /**
     * Obtiene el valor de girasoles
     *
     * @return el valor de girasoles
     */
    public ArrayList<Girasol> getGirasoles() {
        return girasoles;
    }

    /**
     * Modifica el valor de girasoles
     *
     * @param girasoles nuevo valor de girasoles
     */
    public void setGirasoles(ArrayList<Girasol> girasoles) {
        this.girasoles = girasoles;
    }
    
    /**
     * Obtiene el valor de lanzasGuisantes
     *
     * @return el valor de lanzasGuisantes
     */
    public ArrayList<LanzaGuisantes> getLanzasGuisantes() {
        return lanzasGuisantes;
    }

    /**
     * Modifica el valor de lanzasGuisantes
     *
     * @param lanzasGuisantes nuevo valor de lanzasGuisantes
     */
    public void setLanzasGuisantes(ArrayList<LanzaGuisantes> lanzasGuisantes) {
        this.lanzasGuisantes = lanzasGuisantes;
    }
    
    /**
     * Obtiene el valor de zombies
     *
     * @return el valor de zombies
     */
    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    /**
     * Modifica el valor de zombies
     *
     * @param zombies nuevo valor de zombies
     */
    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }
    
    /**
     * Obtiene el valor de turnoReparto
     *
     * @return el valor de turnoReparto
     */
    public int getTurnoReparto() {
        return turnoReparto;
    }

    /**
     * Modifica el valor de turnoReparto
     *
     * @param turnoReparto nuevo valor de turnoReparto
     */
    public void setTurnoReparto(int turnoReparto) {
        this.turnoReparto = turnoReparto;
    }
    
    
    /**
     * Obtiene el valor de totalZombis
     *
     * @return el valor de totalZombis
     */
    public int getTotalZombis() {
        return totalZombis;
    }

    /**
     * Modifica el valor de totalZombis
     *
     * @param totalZombis nuevo valor de totalZombis
     */
    public void setTotalZombis(int totalZombis) {
        this.totalZombis = totalZombis;
    }
    
    /**
     * Obtiene el valor de dificultad
     *
     * @return el valor de dificultad
     */
    public Dificultad getDificultad() {
        return dificultad;
    }

    /**
     * Modifica el valor de dificultad
     *
     * @param dificultad nuevo valor de dificultad
     */
    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }
    
    /**
     * Obtiene el valor de turnosSinZombis
     *
     * @return el valor de turnosSinZombis
     */
    public int getTurnosSinZombis() {
        return turnosSinZombis;
    }

    /**
     * Modifica el valor de turnosSinZombis
     *
     * @param turnosSinZombis nuevo valor de turnosSinZombis
     */
    public void setTurnosSinZombis(int turnosSinZombis) {
        this.turnosSinZombis = turnosSinZombis;
    }
    
    /**
     * Obtiene el valor de jugador.
     * 
     * @return el valor de jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /** 
     * Modifica el valor de jugador.
     * 
     * @param jugador nueo valor de jugador
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    /**
     * Obtiene si se esta colocando algo mediante un booleano
     * @return true o false
     */
    public boolean isColocando() {
        return colocando;
    }

    /**
     * Modifica el valor de colocando.
     * 
     * @param colocando nuevo valor de colocando
     */
    public void setColocando(boolean colocando) {
        this.colocando = colocando;
    }
    
    /**
     * Obtiene si se ha terminado algo mediante un booleano.
     * 
     * @return true o false
     */
    public boolean isTerminado() {
        return terminado;
    }

    /**
     * Modifica el valor de terminado.
     * 
     * @param terminado nuevo valor de terminado
     */
    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
}
