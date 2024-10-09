package Ficheros_XML;

import Ficheros_Binarios.Persona;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
/*
    Transformamos el fichoero BINARIO persona.dat
    a XML
 */
public class BinarioAXML {
    public static void main(String[] args) {
        File fichero = new File("src\\Ficheros_Binarios\\persona.dat");

        // Declarar la variable aquí
        Document document = null;

        // Crea el flujo de entrada
        try (FileInputStream fileIn = new FileInputStream(fichero);
             ObjectInputStream dataIS = new ObjectInputStream(fileIn)) {

            // Configuración para crear el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "Personas", null);
            document.setXmlVersion("1.0");

            while (true) {
                // Leer objeto Persona
                Persona persona = (Persona) dataIS.readObject();

                // Crear el elemento raíz 'Persona' para cada objeto
                Element raiz = document.createElement("Persona");
                document.getDocumentElement().appendChild(raiz);

                // Añadir los datos de la persona al XML
                crearElemento("Nombre", persona.getNombre(), raiz, document);
                crearElemento("Edad", Integer.toString(persona.getEdad()), raiz, document);
                // Añade más elementos según los atributos de la clase Persona

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
            Result result = new StreamResult(new File("src\\Ficheros_XML\\Personas.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            System.out.println("Archivo XML creado con éxito.");
        } catch (Exception e) {
            System.err.println("Error al guardar el archivo XML: " + e);
        }
    }

    // Inserción de los datos de la persona
    static void crearElemento(String nombreElemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreElemento);
        Text text = document.createTextNode(valor); // damos valor
        elem.appendChild(text); // pegamos el valor
        raiz.appendChild(elem); // pegamos el elemento hijo a la raiz
    }

}//Fin class
