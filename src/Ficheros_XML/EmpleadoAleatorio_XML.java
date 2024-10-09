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

public class EmpleadoAleatorio_XML {
    public static void main(String args[]) throws IOException {
        //Abro el fichero AleatorioEjemplo5 que es donde tengo los empleados
        File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        int id, dep, posicion = 0; //para situarnos al principio del fichero
        double salario;
        char apellido[] = new char[10], aux;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            for (;;) {
                file.seek(posicion); //nos posicionamos
                id = file.readInt(); // obtengo id de empleado
                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidoStr = new String(apellido);
                dep = file.readInt(); // obtengo departamento
                salario = file.readDouble(); // obtengo salario

                Element raiz = document.createElement("Empleado");
                document.getDocumentElement().appendChild(raiz);

                CrearElemento("Id", Integer.toString(id), raiz, document);
                CrearElemento("Apellido", apellidoStr.trim(), raiz, document);
                CrearElemento("Departamento", Integer.toString(dep), raiz, document);
                CrearElemento("Salario", Double.toString(salario), raiz, document);

                posicion += 36; // avanzo para el siguiente empleado
                if (file.getFilePointer() >= file.length()) break;
            }//Fin for
            // Ya hemos creado un arbol en la RAM con todos los datos de los emplados, en este caso un documento

            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("src\\Ficheros_XML\\Empleados.xml"));
            //Pasamos el arbol al fichero XML con Transformer
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        file.close(); //cerrar fichero
    }//Fin main

    //Inserción de los datos del empleado
    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor); //damos valor
        elem.appendChild(text); //pegamos el valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
    }//Fin CrearElemento

}//Fin class
