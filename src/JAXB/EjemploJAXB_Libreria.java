package JAXB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class EjemploJAXB_Libreria {

    private static final String MIARCHIVO_XML = "src\\JAXB\\libreria3.xml";

    public static void main(String[] args) throws JAXBException, IOException {
        // Se crea la lista de libros
        ArrayList<Libro> ListaLibro = new ArrayList<>();

        // Creamos libros y los añadimos
        Libro librol = new Libro("Entornos de Desarrollo", "Alicia Ranca", "Marceta", "978-84-1545-297-3");
        ListaLibro.add(librol);
        Libro libro2 = new Libro("Acceso a Datos", "Maria Jesús Ravce", "Santillana", "978-84-1565-228-7");
        ListaLibro.add(libro2);

        // Creamos la librería y le asignamos la lista de libros
        Libreria milibreria = new Libreria();
        milibreria.setNombre("Prueba de librería JAXB");
        milibreria.setLugar("Talavera");
        milibreria.setListaLibros(ListaLibro);

        // Creamos el contexto indicando la clase raíz
        JAXBContext context = JAXBContext.newInstance(Libreria.class);

        // Creamos el marshaller, convierte el java bean a XML
        Marshaller marshaller = context.createMarshaller();
        // Formateamos el XML para que quede bien
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Escribimos en el archivo y en consola
        marshaller.marshal(milibreria, System.out);
        marshaller.marshal(milibreria, new File(MIARCHIVO_XML));

        // Ahora realizamos el UNMARSHALLER
        System.out.println("\nLeyendo el XML...");

        // Se crea Unmarshaller en el contexto de la clase Libreria
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Utilizamos el método unmarshal, para obtener datos
        Libreria libreria2 = (Libreria) unmarshaller.unmarshal(new File(MIARCHIVO_XML));

        // Mostramos los datos de la librería
        System.out.println("Nombre de la librería: " + libreria2.getNombre());
        System.out.println("Lugar de la librería: " + libreria2.getLugar());
        System.out.println("Libros de la librería:");

        // Recuperamos el ArrayList y lo visualizamos
        ArrayList<Libro> listaLibros = libreria2.getListaLibros();
        for (Libro libro : listaLibros) {
            System.out.println("    -Título del libro: " + libro.getNombre() + ", Autor: " + libro.getAutor());
        }//Fin for

    }//Fin main

}//Fin class

