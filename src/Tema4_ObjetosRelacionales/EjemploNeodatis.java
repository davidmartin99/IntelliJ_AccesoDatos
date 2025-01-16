package Tema4_ObjetosRelacionales;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class EjemploNeodatis {
    public static void main(String[] args) {
        // Crear instancias de Jugadores para almacenar en la base de datos
        Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14);
        Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15);
        Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15);
        Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14);

        // Abrir la base de datos llamada "neodatis.test"
        ODB odb = ODBFactory.open("neodatis.test");

        // Almacenar los objetos en la base de datos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);
        System.out.println("Jugadores almacenados con éxito.");

        // Recuperar todos los objetos de la clase Jugadores
        Objects<Jugadores> objects = odb.getObjects(Jugadores.class);

        // Mostrar el número de objetos recuperados
        System.out.printf("%d Jugadores recuperados:\n", objects.size());

        // Visualizar cada jugador almacenado
        while (objects.hasNext()) {
            Jugadores jug = objects.next();
            System.out.printf("Nombre: %s, Deporte: %s, Ciudad: %s, Edad: %d\n",
                    jug.getNombre(), jug.getDeporte(), jug.getCiudad(), jug.getEdad());
        }

        // Cerrar la base de datos
        odb.close();
        System.out.println("Base de datos cerrada.");
    }
}
