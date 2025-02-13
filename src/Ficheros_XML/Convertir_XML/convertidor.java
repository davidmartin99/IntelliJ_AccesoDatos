package Ficheros_XML.Convertir_XML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class convertidor {
    public static void main(String[] args) throws IOException {
        // Rutas a los archivos de la hoja de estilo XSL y datos XML
        String hojaEstilo = "src\\Ficheros_XML\\Convertir_XML\\alumnosPlantilla.xsl"; // Archivo XSL
        String datosAlumnos = "src\\Ficheros_XML\\Convertir_XML\\alumnos.xml"; // Archivo XML con los datos

        // Creación de un archivo HTML donde se escribirá el resultado de la transformación
        File pagHTML = new File("src\\Ficheros_XML\\Convertir_XML\\mipagina.html"); // Crear fichero HTML de salida
        FileOutputStream os = new FileOutputStream(pagHTML); // Flujo de salida para el archivo HTML

        // Cargar los archivos XSL (hoja de estilo) y XML (datos)
        Source estilos = new StreamSource(hojaEstilo); // Fuente de la hoja de estilo XSL
        Source datos = new StreamSource(datosAlumnos); // Fuente de los datos XML

        // El resultado de la transformación se escribirá en el archivo HTML a través del flujo de salida
        Result result = new StreamResult(os); // Donde se escribirá el resultado (el archivo HTML)

        try {
            // Crea un objeto Transformer utilizando la hoja de estilo XSL.
            // Este objeto es responsable de aplicar la transformación.
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);

            // Aplicar la transformación de los datos XML usando la hoja de estilo XSL
            transformer.transform(datos, result); // Transformar el XML en HTML
        } catch (Exception e) {
            // En caso de error, imprimir un mensaje con la descripción del error
            System.err.println("Error: " + e);
        }

        // Cerrar el flujo de salida para asegurarse de que todos los datos se escriben correctamente
        os.close(); // Cerrar el archivo de salida
    } // Fin del método main
} // Fin de la clase convertidor

