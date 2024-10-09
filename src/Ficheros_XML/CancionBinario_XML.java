package Ficheros_XML;


import Ficheros_Binarios.Cancion;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CancionBinario_XML {
    public static void main(String[] args) {
        File fichero = new File("C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\out\\production\\Acceso_Datos\\Ficheros_Binarios\\canciones4.dat");

        // Declarar la variable para el documento XML
        Document document = null;

        // Crea el flujo de entrada
        try (FileInputStream fileIn = new FileInputStream(fichero);
             ObjectInputStream dataIS = new ObjectInputStream(fileIn)) {

            // Configuración para crear el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "Canciones", null);
            document.setXmlVersion("1.0");

            while (true) {
                // Leer objeto Cancion
                Cancion cancion = (Cancion) dataIS.readObject();

                // Crear el elemento raíz 'Cancion' para cada objeto
                Element raiz = document.createElement("Cancion");
                document.getDocumentElement().appendChild(raiz);

                // Añadir los datos de la canción al XML
                crearElemento("ID", Integer.toString(cancion.getId()), raiz, document);
                crearElemento("Año", Integer.toString(cancion.getAño()), raiz, document);
                crearElemento("Titulo", cancion.getTitulo(), raiz, document);
                crearElemento("Artista", cancion.getArtista(), raiz, document);
                crearElemento("Duracion", cancion.getDuracion(), raiz, document);
                crearElemento("Española", Boolean.toString(cancion.getEspañola()), raiz, document);
            }
        } catch (EOFException e) {
            System.out.println("Fin de lectura del fichero binario.");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        // Guardar el documento XML en un archivo
        guardarXML(document);
    }

    // Metodo para guardar el documento XML en un archivo
    private static void guardarXML(Document document) {
        try {
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("src\\Ficheros_XML\\CancionesBinario.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Indentación para formato legible
            transformer.transform(source, result);
            System.out.println("Archivo XML creado con éxito.");
        } catch (Exception e) {
            System.err.println("Error al guardar el archivo XML: " + e);
        }
    }

    // Inserción de los datos de la canción
    static void crearElemento(String nombreElemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreElemento);
        Text text = document.createTextNode(valor); // Damos valor
        elem.appendChild(text); // Pegamos el valor
        raiz.appendChild(elem); // Pegamos el elemento hijo a la raíz
    }
}

