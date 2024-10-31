package Tema2_Conectores.BBDD_Persona;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Eliminar1 {
    public static void eliminarPorNombre(ObjectContainer db, String nombre) {
        ObjectSet<Persona> result = db.queryByExample(new Persona(nombre, null));

        if (result.size() == 0) {
            System.out.println("No existe ningún registro de " + nombre + ".");
        } else {
            int count = 0;
            while (result.hasNext()) {
                Persona p = result.next();
                db.delete(p);
                count++;
            }
            System.out.printf("Se han borrado %d registros de %s.%n", count, nombre);
        }
    }
}
