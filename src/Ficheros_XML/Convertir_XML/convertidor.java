package Ficheros_XML.Convertir_XML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class convertidor {
    public static void main(String[] args) throws IOException {
        String hojaEstilo = "src\\Ficheros_XML\\Convertir_XML\\alumnosPlantilla.xsl";
        String datosAlumnos = "src\\Ficheros_XML\\Convertir_XML\\alumnos.xml";
        File pagHTML = new File("src\\Ficheros_XML\\Convertir_XML\\mipagina.html"); // crear fichero HTML
        FileOutputStream os = new FileOutputStream(pagHTML);
        Source estilos = new StreamSource(hojaEstilo); // fuente XSL
        Source datos = new StreamSource(datosAlumnos); // fuente XML

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

