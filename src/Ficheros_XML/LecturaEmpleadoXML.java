package Ficheros_XML;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/*
    Clase que se encarga de leer un archivo XML de empleados
    y mostrar la información de cada empleado.
 */
public class LecturaEmpleadoXML {

    public static void main(String[] args) {
        // Creamos una instancia de DocumentBuilderFactory para obtener un parser de XML.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // Creamos un DocumentBuilder para parsear el archivo XML.
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parseamos el archivo Empleados.xml, obteniendo el DOM (Document Object Model) del archivo.
            Document document = builder.parse(new File("src\\Ficheros_XML\\Empleados.xml"));

            // Normalizamos el documento para eliminar nodos redundantes y unificar la estructura.
            document.getDocumentElement().normalize();

            // Imprimimos el nombre del elemento raíz del documento XML.
            System.out.printf("Elemento raiz: %s\n", document.getDocumentElement().getNodeName());

            // Creamos una lista de nodos que contienen todos los elementos "Empleado" en el archivo XML.
            NodeList empleados = document.getElementsByTagName("Empleado");
            System.out.printf("Nodos empleado a recorrer: %d\n", empleados.getLength());

            // Recorremos la lista de nodos "Empleado".
            // Utilizamos .getLenght() para saber cuantos empleados tenemos
            for (int i = 0; i < empleados.getLength(); i++) {

                /*
                    !!!!!! Obtenemos el nodo individual de cada empleado.
                    Aquí se obtiene un nodo de empleado en la posición i de la lista empleados.
                    Cada nodo contiene toda la información de un empleado
                    (por ejemplo, ID, apellido, departamento, salario).
                 */
                Node emple = empleados.item(i);

                // Verificamos que el nodo sea un nodo de tipo ELEMENT_NODE (elemento de XML).
                if (emple.getNodeType() == Node.ELEMENT_NODE) {
                    // Convertimos el nodo en un elemento para poder acceder a sus datos.
                    Element elemento = (Element) emple;

                    // Imprimimos el valor del nodo "Id" del empleado.
                    System.out.printf("ID: %s\n", elemento.getElementsByTagName("Id").item(0).getTextContent());

                    // Imprimimos el valor del nodo "Apellido" del empleado.
                    System.out.printf("Apellido: %s\n", elemento.getElementsByTagName("Apellido").item(0).getTextContent());

                    // Imprimimos el valor del nodo "Departamento" del empleado.
                    System.out.printf("Departamento: %s\n", elemento.getElementsByTagName("Departamento").item(0).getTextContent());

                    // Imprimimos el valor del nodo "Salario" del empleado.
                    System.out.printf("Salario: %s\n", elemento.getElementsByTagName("Salario").item(0).getTextContent());
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
