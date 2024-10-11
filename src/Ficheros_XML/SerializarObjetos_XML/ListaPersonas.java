package Ficheros_XML.SerializarObjetos_XML;
import Ficheros_Binarios.Persona;

import java.util.ArrayList;
import java.util.List;

public class ListaPersonas {
    private List<Persona> lista;

    public ListaPersonas() {
        lista = new ArrayList<>();
    }

    public void add(Persona persona) {
        lista.add(persona);
    }

    public List<Persona> getListaPersonas() {
        return lista;
    }

}//Fin clas
