package Ficheros_XML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

/*
    Vamos a crear un fichero XML
    partiendo de uno ALEATORIO
 */

public class CancionAleatorio_XML {
    public static void main(String args[]) throws IOException {
        // Abro el fichero cancionesAleatorio que es donde tengo las canciones
        File fichero = new File("src\\Ficheros_Aleatorios\\cancionesAleatorio.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        int posicion = 0; // para situarnos al principio del fichero

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Canciones", null);
            document.setXmlVersion("1.0");

            while (posicion < file.length()) {
                file.seek(posicion); // nos posicionamos al inicio del registro

                // Leer cada campo según el formato de escritura
                int id = file.readInt(); // obtener ID de la canción
                int año = file.readInt(); // obtener el año de la canción

                // Leer el título
                StringBuilder tituloBuilder = new StringBuilder();
                for (int i = 0; i < 50; i++) {
                    tituloBuilder.append(file.readChar());
                }
                String titulo = tituloBuilder.toString().trim(); // Convertir a String y eliminar espacios

                // Leer el artista
                StringBuilder artistaBuilder = new StringBuilder();
                for (int i = 0; i < 50; i++) {
                    artistaBuilder.append(file.readChar());
                }
                String artista = artistaBuilder.toString().trim(); // Convertir a String y eliminar espacios

                // Leer la duración
                StringBuilder duracionBuilder = new StringBuilder();
                for (int i = 0; i < 10; i++) {
                    duracionBuilder.append(file.readChar());
                }
                String duracion = duracionBuilder.toString().trim(); // Convertir a String y eliminar espacios

                boolean española = file.readBoolean(); // obtener si es española

                // Crear el elemento raíz 'Cancion' para cada canción
                Element raiz = document.createElement("Cancion");
                document.getDocumentElement().appendChild(raiz);

                // Añadir los datos de la canción al XML
                CrearElemento("ID", Integer.toString(id), raiz, document);
                CrearElemento("Año", Integer.toString(año), raiz, document);
                CrearElemento("Titulo", titulo, raiz, document);
                CrearElemento("Artista", artista, raiz, document);
                CrearElemento("Duracion", duracion, raiz, document); // Aquí usamos el String directamente
                CrearElemento("EsEspañola", española ? "Sí" : "No", raiz, document);

                posicion += 229; // avanzar al siguiente registro
            }

            // Pasar el árbol al fichero XML con Transformer
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("src\\Ficheros_XML\\Canciones.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            file.close(); // cerrar fichero
        }
    }

    // Inserción de los datos del empleado
    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor); // damos valor
        elem.appendChild(text); // pegamos el valor
        raiz.appendChild(elem); // pegamos el elemento hijo a la raiz
    }
}
