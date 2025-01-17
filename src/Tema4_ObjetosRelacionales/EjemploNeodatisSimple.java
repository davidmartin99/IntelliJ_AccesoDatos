package Tema4_ObjetosRelacionales;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;

/*
    pag.41
 */

public class EjemploNeodatisSimple {
    public static void main(String[] args) {
        // Crear jugadores
        Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14, null);
        Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, null);

        // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis_simple.test");

        // Almacenar objetos y mostrar sus OID
        OID oid1 = odb.store(j1);
        System.out.println("OID de Maria: " + oid1);

        OID oid2 = odb.store(j2);
        System.out.println("OID de Miguel: " + oid2);

        // Cerrar la base de datos
        odb.close();
    }
}
