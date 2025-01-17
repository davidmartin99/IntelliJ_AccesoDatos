package Tema4_ObjetosRelacionales.ConModeloRelacional;

import java.util.Set;

public class E01_C1Asignaturas {

    private String codAsig;
    private String nombreAsi;
    private Set<E01_C1Profesores> setprofesores;

    public E01_C1Asignaturas(String codAsig, String nombreAsi, Set<E01_C1Profesores> setprofesores) {
        super();
        this.codAsig = codAsig;
        this.nombreAsi = nombreAsi;
        this.setprofesores = setprofesores;
    }

    public E01_C1Asignaturas(){}

    //GETTERS y SETTERS

    public String getCodAsig() {
        return codAsig;
    }
    public void setCodAsig(String codAsig) {
        this.codAsig = codAsig;
    }
    public String getNombreAsi() {
        return nombreAsi;
    }
    public void setNombreAsi(String nombreAsi) {
        this.nombreAsi = nombreAsi;
    }
    public Set<E01_C1Profesores> getSetprofesores() {
        return setprofesores;
    }
    public void setSetprofesores(Set<E01_C1Profesores> setprofesores) {
        this.setprofesores = setprofesores;
    }

}