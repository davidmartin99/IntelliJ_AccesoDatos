package Ficheros_XML;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
/*
    Le
 */
public class LecturaEmpleadoXML {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src\\Ficheros_XML\\Empleados.xml"));
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raiz: %s\n", document.getDocumentElement().getNodeName());

            // Crear una lista con todos los nodos empleado
            NodeList empleados = document.getElementsByTagName("Empleado");
            System.out.printf("Nodos empleado a recorrer: %d\n", empleados.getLength());

            // Recorrer la lista
            for (int i = 0; i < empleados.getLength(); i++) {
                Node emple = empleados.item(i); // Obtener un nodo empleado

                if (emple.getNodeType() == Node.ELEMENT_NODE) { // Tipo de nodo
                    // Obtener los elementos del nodo
                    Element elemento = (Element) emple;

                    System.out.printf("ID: %s\n", elemento.getElementsByTagName("Id").item(0).getTextContent());
                    System.out.printf("Apellido: %s\n", elemento.getElementsByTagName("Apellido").item(0).getTextContent());
                    System.out.printf("Departamento: %s\n", elemento.getElementsByTagName("Departamento").item(0).getTextContent());
                    System.out.printf("Salario: %s\n", elemento.getElementsByTagName("Salario").item(0).getTextContent());
                }//Fin if
            }//Fin for

        } catch (Exception e) {
            e.printStackTrace();
        }//Fin try-catch

    }//Fin main

}//Fin class

