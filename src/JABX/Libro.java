package JABX;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = {"autor", "nombre", "editorial", "isbn"})
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;

    public Libro(String nombre, String autor, String editorial, String isbn) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }

    public Libro() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
