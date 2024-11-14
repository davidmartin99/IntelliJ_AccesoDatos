package Tema2_Conectores.BBDD_Persona;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Consulta1 {
    public static void consultarPersonas(ObjectContainer db) {
        Persona per = new Persona(null, null);
        ObjectSet<Persona> result = db.queryByExample(per);

        if (result.size() == 0) {
            System.out.println("No existen registros de personas.");
        } else {
            System.out.printf("Número de registros: %d%n", result.size());
            while (result.hasNext()) {
                Persona p = result.next();
                System.out.printf("Nombre: %s, Ciudad: %s%n", p.getNombre(), p.getCiudad());
            }
        }
    }
}
