package Ficheros_XML.SerializarObjetos_XML;

import Ficheros_Binarios.Persona;
import com.thoughtworks.xstream.XStream;
import java.io.*;
/*
    Pasamos un fichero.dat  a un XML.
 */
public class EscribirPersonasXML {

    public static void main(String[] args) {
        File fichero = new File("src\\Ficheros_Binarios\\persona.dat");

        try {
            // Abrimos el fichero para leer los objetos
            FileInputStream fileIn = new FileInputStream(fichero);
            ObjectInputStream dataIn = new ObjectInputStream(fileIn);

            // Creamos la lista de personas
            ListaPersonas listaPersonas = new ListaPersonas();

            try {
                while (true) {
                    // Leemos cada persona del fichero binario y la añadimos a la lista
                    Persona persona = (Persona) dataIn.readObject();
                    listaPersonas.add(persona);
                }
            } catch (EOFException eofException) {
                // EOFException se lanza cuando se llega al final del fichero
                dataIn.close();  // Cerramos el ObjectInputStream
            }

            // Creamos el objeto XStream para generar el XML
            XStream xstream = new XStream();

            // Alias para que los nombres de las etiquetas sean más legibles
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPersona", Persona.class);

            // Eliminamos la etiqueta lista como atributo de la clase ListaPersonas
            xstream.addImplicitCollection(ListaPersonas.class, "lista");

            // Escribimos la lista de personas en el fichero XML
            xstream.toXML(listaPersonas, new FileOutputStream("src\\Ficheros_XML\\SerializarObjetos_XML\\Personas.xml"));
            System.out.println("Fichero XML creado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
