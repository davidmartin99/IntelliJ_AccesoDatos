package Tema6_DesarrolloComponentes.PrimerBean;

import java.io.Serializable;

/**
 * Clase MiPrimerBean: Un JavaBean simple con una propiedad.
 */
public class MiPrimerBean implements Serializable {
    private String propiedad;

    /**
     * Constructor sin parámetros.
     */
    public MiPrimerBean() {
        this.propiedad = "";
    }

    /**
     * Obtiene el valor de la propiedad.
     */
    public String getPropiedad() {
        return this.propiedad;
    }

    /**
     * Establece el valor de la propiedad.
     */
    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }
}