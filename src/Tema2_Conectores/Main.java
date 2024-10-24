package Tema2_Conectores;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Main {
    static String BDPer = "DBPersonas.yap"; // Nombre de la base de datos

    public static void main(String[] args) {
        // Abrir la base de datos
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

        // Se crean objetos Persona
        Persona p1 = new Persona("Juan", "Guadalajara");
        Persona p2 = new Persona("Ana", "Madrid");
        Persona p3 = new Persona("Luis", "Granada");
        Persona p4 = new Persona("Pedro", "Asturias");

        // Almacenar objetos Persona
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);

        // Llamar a las clases para realizar operaciones
        Consulta1.consultarPersonas(db); // Consultar todas las personas
        Modificar1.modificarCiudad(db, "Juan", "Toledo"); // Modificar la ciudad de Juan
        Eliminar1.eliminarPorNombre(db, "Ana"); // Eliminar a Ana

        // Consultar nuevamente después de modificaciones
        Consulta1.consultarPersonas(db); // Consultar todas las personas

        // Cerrar la base de datos
        db.close();
    }
}
