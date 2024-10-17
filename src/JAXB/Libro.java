package JAXB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serial;
import java.io.Serializable;

@XmlType(propOrder = {"nombre", "autor", "editorial", "isbn"})
public class Libro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;

    // Constructor with parameters
    public Libro(String nombre, String autor, String editorial, String isbn) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }

    // Default constructor
    public Libro() {
        // Empty constructor for JAXB
    }

    @XmlElement(name = "nombre") // Specify XML element name
    public String getNombre() {
        return nombre;
    }

    @XmlElement(name = "autor") // Specify XML element name
    public String getAutor() {
        return autor;
    }

    @XmlElement(name = "editorial") // Specify XML element name
    public String getEditorial() {
        return editorial;
    }

    @XmlElement(name = "isbn") // Specify XML element name
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
