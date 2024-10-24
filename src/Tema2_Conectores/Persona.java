package Tema2_Conectores;

public class Persona {
    private String nombre;
    private String ciudad;

    // Constructor con parámetros
    public Persona(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    // Constructor por defecto
    public Persona() {
        this.nombre = null;
        this.ciudad = null;
    }

    // Getter para el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para la ciudad
    public String getCiudad() {
        return ciudad;
    }

    // Setter para la ciudad
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

