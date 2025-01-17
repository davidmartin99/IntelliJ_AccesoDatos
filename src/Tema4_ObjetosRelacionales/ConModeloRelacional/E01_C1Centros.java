package Tema4_ObjetosRelacionales.ConModeloRelacional;

import java.util.Set;

public class E01_C1Centros {

    private Integer codCentro;
    private String nomCentro;
    private E01_C1Profesores director;
    private String direccion;
    private String localidad;
    private String provincia;
    private Set<E01_C1Profesores> setprofesores;

    public E01_C1Centros(Integer codCentro, String nomCentro, E01_C1Profesores director, String direccion, String localidad,
                         String provincia, Set<E01_C1Profesores> setprofesores) {
        super();
        this.codCentro = codCentro;
        this.nomCentro = nomCentro;
        this.director = director;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.setprofesores = setprofesores;
    }

    public E01_C1Centros(){}

    //GETTERS y SETTERS

    public Integer getCodCentro() {
        return codCentro;
    }

    public void setCodCentro(Integer codCentro) {
        this.codCentro = codCentro;
    }

    public String getNomCentro() {
        return nomCentro;
    }

    public void setNomCentro(String nomCentro) {
        this.nomCentro = nomCentro;
    }

    public E01_C1Profesores getDirector() {
        return director;
    }

    public void setDirector(E01_C1Profesores director) {
        this.director = director;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Set<E01_C1Profesores> getSetprofesores() {
        return setprofesores;
    }

    public void setSetprofesores(Set<E01_C1Profesores> setprofesores) {
        this.setprofesores = setprofesores;
    }


}