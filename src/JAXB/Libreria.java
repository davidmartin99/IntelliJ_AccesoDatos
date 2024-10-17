package JAXB;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "libreria") // Set the root element name for XML
public class Libreria {

    private ArrayList<Libro> listaLibros; // Changed to plural for consistency
    private String nombre;
    private String lugar;

    // Constructor with parameters
    public Libreria(ArrayList<Libro> listaLibros, String nombre, String lugar) {
        this.listaLibros = listaLibros;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    // Default constructor
    public Libreria() {
        // Empty constructor for JAXB
    }

    // Getters and setters
    @XmlElementWrapper(name = "listaLibros") // Wrapper for the list in XML
    @XmlElement(name = "libro") // Element name for each book
    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @XmlElement(name = "nombre") // Specify XML element name
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "lugar") // Specify XML element name
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
