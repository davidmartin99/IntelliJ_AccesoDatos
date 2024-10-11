package Ficheros_XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
/*
    Igual que LecturaEmpleadoXML pero con el archivo Peliculas.xml
 */
public class LecturaPeliculasXML {
    public static void main(String[] args) {
        // Creamos una instancia de DocumentBuilderFactory para obtener un parser de XML.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // Creamos un DocumentBuilder para parsear el archivo XML.
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parseamos el archivo Empleados.xml, obteniendo el DOM (Document Object Model) del archivo.
            Document document = builder.parse(new File("src\\Ficheros_XML\\Peliculas.xml"));

            // Normalizamos el documento para eliminar nodos redundantes y unificar la estructura.
            document.getDocumentElement().normalize();

            // Imprimimos el nombre del elemento raíz del documento XML.
            System.out.printf("Elemento raiz: %s\n", document.getDocumentElement().getNodeName());

            // Creamos una lista de nodos que contienen todos los elementos "Empleado" en el archivo XML.
            NodeList peliculas = document.getElementsByTagName("pelicula");
            System.out.printf("Nodos empleado a recorrer: %d\n", peliculas.getLength());

            // Recorremos la lista de nodos "Empleado".
            // Utilizamos .getLenght() para saber cuantos empleados tenemos
            for (int i = 0; i < peliculas.getLength(); i++) {

                /*
                    !!!!!! Obtenemos el nodo individual de cada empleado.
                    Aquí se obtiene un nodo de empleado en la posición i de la lista empleados.
                    Cada nodo contiene toda la información de un empleado
                    (por ejemplo, ID, apellido, departamento, salario).
                 */
                Node pelicula = peliculas.item(i);

                // Verificamos que el nodo sea un nodo de tipo ELEMENT_NODE (elemento de XML).
                if (pelicula.getNodeType() == Node.ELEMENT_NODE) {
                    // Convertimos el nodo en un elemento para poder acceder a sus datos.
                    Element elemento = (Element) pelicula;

                    // Imprimimos el valor del nodo "Id" del empleado.
                    System.out.printf("ID: %s\n", elemento.getElementsByTagName("id").item(0).getTextContent());

                    // Imprimimos el valor del nodo "Apellido" del empleado.
                    System.out.printf("Titulo: %s\n", elemento.getElementsByTagName("titulo").item(0).getTextContent());

                    // Imprimimos el valor del nodo "Departamento" del empleado.
                    System.out.printf("Año: %s\n", elemento.getElementsByTagName("año").item(0).getTextContent());

                    // Imprimimos el valor del nodo "Salario" del empleado.
                    System.out.printf("Director: %s\n", elemento.getElementsByTagName("director").item(0).getTextContent());

                    System.out.printf("Genero: %s\n", elemento.getElementsByTagName("genero").item(0).getTextContent());

                    System.out.printf("Duracion: %s\n", elemento.getElementsByTagName("duracion").item(0).getTextContent());

                    System.out.printf("Disponible: %s\n", elemento.getElementsByTagName("disponible").item(0).getTextContent());


                }//Fin if

                // Separador entre los registros de cada empleado para mayor legibilidad en la salida.
                System.out.println("----------------------------");
            }//Fin for

        } catch (Exception e) {
            // Captura cualquier excepción que ocurra durante la lectura o procesamiento del XML.
            e.printStackTrace();
        }//Fin try-catch

    }//Fin main
}//Fin class
