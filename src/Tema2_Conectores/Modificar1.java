package Tema2_Conectores;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Modificar1 {
    public static void modificarCiudad(ObjectContainer db, String nombre, String nuevaCiudad) {
        ObjectSet<Persona> result = db.queryByExample(new Persona(nombre, null));

        if (result.size() == 0) {
            System.out.println("No existe " + nombre + ".");
        } else {
            Persona existe = result.next();
            existe.setCiudad(nuevaCiudad);
            db.store(existe);

            // Consultar los datos
            result = db.queryByExample(new Persona(nombre, null));
            if (result.hasNext()) {
                Persona actualizado = result.next();
                System.out.printf("Nombre: %s, Nueva Ciudad: %s%n", actualizado.getNombre(), actualizado.getCiudad());
            }
        }
    }
}
