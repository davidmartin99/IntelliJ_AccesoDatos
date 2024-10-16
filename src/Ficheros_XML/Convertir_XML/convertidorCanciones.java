package Ficheros_XML.Convertir_XML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class convertidorCanciones {
    public static void main(String[] args) throws IOException {
        String hojaEstilo = "src\\Ficheros_XML\\Convertir_XML\\cancionesPlantilla.xsl";
        String datosCanciones = "src\\Ficheros_XML\\Canciones.xml";
        File pagHTML = new File("src\\Ficheros_XML\\Convertir_XML\\mipaginaCanciones.html"); // crear fichero HTML
        FileOutputStream os = new FileOutputStream(pagHTML);
        Source estilos = new StreamSource(hojaEstilo); // fuente XSL
        Source datos = new StreamSource(datosCanciones); // fuente XML

        // resultado de la transformación
        Result result = new StreamResult(os);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result); // obtiene el HTML
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        os.close(); // cerrar fichero
    } // de main
} // de la clase

