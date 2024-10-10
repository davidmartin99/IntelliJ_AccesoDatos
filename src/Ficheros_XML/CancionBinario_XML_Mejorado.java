package Ficheros_XML;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Ficheros_Binarios.Cancion;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class CancionBinario_XML_Mejorado {

    public static void main(String[] args) throws IOException {

        //creamos variable fichero y flujo de lectura de fichero de objetos

        File f = new File("C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\out\\production\\Acceso_Datos\\Ficheros_Binarios\\canciones4.dat");

        FileInputStream fileIn = new FileInputStream(f);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);


        //creamos variables

        Cancion cancion = new Cancion();


        //instancia para construir el parser

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();


        try {

            //creamos varias objetos para crear finalmente el documento xml

            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null,"Canciones",null);
            document.setXmlVersion("1.0");


            // Leemos los objetos Cancion en un bucle hasta que ocurra EOFException
            while (true) {
                try {
                    cancion = (Cancion) objectIn.readObject();

                    // Creamos nodo raíz 'Cancion'
                    Element raiz = document.createElement("Cancion");

                    // Añadimos nodo principal
                    document.getDocumentElement().appendChild(raiz);

                    // Creamos elementos para cada atributo de Cancion
                    crearElemento("Id", Integer.toString(cancion.getId()), raiz, document);
                    crearElemento("Ano", Integer.toString(cancion.getAño()), raiz, document);
                    crearElemento("Titulo", cancion.getTitulo(), raiz, document);
                    crearElemento("Artista", cancion.getArtista(), raiz, document);
                    crearElemento("Duracion", cancion.getDuracion(), raiz, document);
                    crearElemento("Espanola", Boolean.toString(cancion.getEspañola()), raiz, document);

                } catch (EOFException e) {
                    // Cuando llegamos al final del archivo, salimos del bucle
                    System.out.println("Fin de lectura del archivo binario.");
                    break;
                }
            }//Fin while

            Source source = new DOMSource(document);

            Result result = new StreamResult(
                    new File("src\\Ficheros_XML\\CancionesBinario_2.xml"));

            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();

            //se transforma el documento al fichero
            transformer.transform(source, result);


            // Habilitar la indentación para mostrar el XML de forma ordenada
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(source, result);

            // Mostramos el documento por consola con formato
            Result console = new StreamResult(System.out);
            transformer.transform(source, console);


        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e);
        }

        //Cerrar el objeto
        objectIn.close();

    }//Fin main

    /**
     * Metodo que crea un elemento para escribirlo en documento xml
     * @param datoCancion Elemento de la canci?n
     * @param valor Valor que se va a dar al elemento
     * @param raiz Elemento raiz
     * @param document Documento donde se van a a?adir estos elementos
     */
    static void crearElemento(String datoCancion, String valor, Element raiz,
                              Document document)
    {

        Element elemento = document.createElement(datoCancion);
        Text texto = document.createTextNode(valor); //damos valor

        raiz.appendChild(elemento); //pegamos el elemento hijo a la raiz
        elemento.appendChild(texto); //pegamos el valor al elemento hijo

    }//Fin crearElemento

}//Fin class