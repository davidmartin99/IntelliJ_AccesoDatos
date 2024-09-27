package Ficheros_Binarios;
/*
    Clase Cancion Serializable
    identificador, año, titulo, artista, duración y canción española (verdadero/falso)
    Constructor y constructo a 0 o null, o falso.
    y metodo mostrar cancion
 */

import java.io.Serializable;

public class Cancion implements Serializable {
    //Atributos
    private int id;
    private int año;
    private String titulo;
    private String artista;
    private String duracion;
    private boolean española;


    //Constructores
    public Cancion(int id, int año, String titulo, String artista, String duracion, boolean española) {
        this.id = id;
        this.año = año;
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.española = española;
    }

    public Cancion(){
        this.id = 0;
        this.año = 0;
        this.titulo = null;
        this.artista = null;
        this.duracion = null;
        this.española = false;
    }

    //Setters y Getters
    public void setId(int id) {this.id = id;}
    public void setAño(int año) {this.año = año;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setArtista(String artista) {this.artista = artista;}
    public void setDuracion(String duracion) {this.duracion = duracion;}
    public void setEspañola(boolean española) {this.española = española;}

    public int getId() {return id;}
    public int getAño() {return año;}
    public String getTitulo() {return titulo;}
    public String getArtista() {return artista;}
    public String getDuracion() {return duracion;}
    public boolean getEspañola() {return española;}


    //Metodo que muestre la cancion
    @Override
    public String toString() {
        return "- Canción ID: " + id + "\n" +
                "     Año: " + año + "\n" +
                "     Título: " + titulo + "\n" +
                "     Artista: " + artista + "\n" +
                "     Duración: " + duracion + "\n" +
                "     Es española: " + (española ? "Sí" : "No");
    }//Fin toString


}//Fin class
