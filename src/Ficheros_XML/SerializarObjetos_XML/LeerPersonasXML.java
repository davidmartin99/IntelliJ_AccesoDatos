package Ficheros_XML.SerializarObjetos_XML;

import Ficheros_Binarios.Persona;
import Ficheros_XML.SerializarObjetos_XML.ListaPersonas;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileInputStream;
import java.io.IOException;

public class LeerPersonasXML {
    public static void main(String[] args) throws IOException {
        XStream xstream = new XStream();

        // Uso esto para dar permisos
        xstream.addPermission(AnyTypePermission.ANY); // También puedes usar allowTypesByWildcard para clases específicas.

        // Set up XStream aliases
        xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
        xstream.alias("DatosPersona", Persona.class);
        xstream.aliasField("NombreAlumno", Persona.class, "nombre");
        xstream.aliasField("EdadAlumno", Persona.class, "edad");
        xstream.addImplicitCollection(ListaPersonas.class, "lista");

        // Deserialize from XML
        ListaPersonas listadoTodas = (ListaPersonas) xstream.fromXML(new FileInputStream("src\\Ficheros_XML\\SerializarObjetos_XML\\Personas.xml"));

        // Display the number of persons
        System.out.println("Número de Personas: " + listadoTodas.getListaPersonas().size());


        // Iterate and print details
        for (Persona p : listadoTodas.getListaPersonas()) {
            System.out.printf("Nombre: %s, Edad: %d%n", p.getNombre(), p.getEdad());
        }

        System.out.println("Fin de listado");


    }
}
