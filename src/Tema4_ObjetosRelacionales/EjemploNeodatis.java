package Tema4_ObjetosRelacionales;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where; // Import necesario
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/*
    pag.38
 */
public class EjemploNeodatis {
    public static void main(String[] args) {
        // Creo los paises
        Paises pais1 = new Paises(1, "Espana");
        Paises pais2 = new Paises(2, "Mexico");

        // Crear instancias de Jugadores para almacenar en la base de datos
        Jugadores j1= new Jugadores ("Maria", "voleibol", "Madrid", 14, pais1);
        Jugadores j2= new Jugadores ("Miguel", "tenis", "Madrid", 15, pais1);
        Jugadores j3= new Jugadores ("Mario", "baloncesto", "Guadalajara", 15, pais2);
        Jugadores j4= new Jugadores ("Alicia", "tenis", "Madrid", 14, pais1);

        // Inserto los objetos
		/*odb.store(j1);
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		odb.store(pais1);
		odb.store(pais2);*/
        // Abrir la base de datos llamada "neodatis.test"
        ODB odb = ODBFactory.open("neodatis1.test");

        // Almacenar los objetos en la base de datos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);
        System.out.println("Jugadores almacenados con éxito.");

        // sacar los datos de todos los jugadores
        Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
        int i = 1;

        System.out.println(objects.size() + " jugadores:");

        while(objects.hasNext()){
            Jugadores jug= objects.next();

            System.out.println((i++) + "\t: " + jug.getNombre() + "*" +
                    jug.getDeporte() + "*" + jug.getCiudad() + "*" + jug.getEdad());
        }
        System.out.println("------------");


        /**
         * Consultas Sencillas pag.41
         */
        ///////CONSULTA 1//////////
        // Jugadores que practican tenis, ordenados de mayor a menor edad
        IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("deporte", "tenis"));
        query.orderByDesc("edad");
        i = 1;

        Objects<Jugadores> objects1 = odb.getObjects(query);

        System.out.println("Jugadores que juegan al tenis: ");

        while(objects1.hasNext()){
            Jugadores jug = objects1.next();

            System.out.println((i++) + "\t: " + jug.getNombre() + "*" +
                    jug.getDeporte() + "*" + jug.getCiudad() + "*" + jug.getEdad());
        }
        System.out.println("------------");


        //////CONSULTA 2 CON criterio///////
        ICriterion criterio2 = new And().add(Where.equal("ciudad", "Madrid")).
                add(Where.ge("edad",15));

        query = new CriteriaQuery(Jugadores.class, criterio2);

        Objects<Jugadores> objects2 = odb.getObjects(query);

        mostrarDatos(objects2, "Jugadores que son de Madrid y tienen 15 a�os");


        //////CONSULTA 3 CON jugadores que hay en un pais///////
        String pais = "Mexico";

        ICriterion criterio3 = new And().add(Where.equal("pais.nombrePais", pais));
        query = new CriteriaQuery(Jugadores.class, criterio3);
        Objects<Jugadores> jugadores = odb.getObjects(query);

        mostrarDatos(jugadores, "Jugadores que son de " + pais);


        ///////BORRAR UN PAIS///////
		/*query = new CriteriaQuery(Paises.class, Where.equal("nombrePais", "Espana"));
		Paises paisborrar = (Paises) odb.getObjects(query).getFirst();

		odb.delete(paisborrar);*/

        // Modificar el deporte de Maria a "vóley-playa"
        IQuery queryMaria = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "Maria"));
        Objects<Jugadores> resultadosMaria = odb.getObjects(queryMaria);

        if (!resultadosMaria.isEmpty()) {
            Jugadores maria = resultadosMaria.getFirst();
            maria.setDeporte("vóley-playa");
            odb.store(maria); // Actualizar el objeto
            odb.commit(); // Validar los cambios
            System.out.println("Deporte de Maria actualizado a 'vóley-playa'.");
        }

        // Consultar los jugadores cuya edad es 14
        ICriterion criterioEdad = Where.equal("edad", 14);
        IQuery queryEdad = new CriteriaQuery(Jugadores.class, criterioEdad);
        Objects<Jugadores> jugadoresEdad14 = odb.getObjects(queryEdad);
        mostrarDatos(jugadoresEdad14, "Jugadores con 14 años:");

        // Eliminar el jugador "Miguel"
        IQuery queryMiguel = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "Miguel"));
        Objects<Jugadores> resultadosMiguel = odb.getObjects(queryMiguel);

        if (!resultadosMiguel.isEmpty()) {
            Jugadores miguel = resultadosMiguel.getFirst();
            odb.delete(miguel); // Eliminar el objeto
            odb.commit(); // Validar los cambios
            System.out.println("Jugador Miguel eliminado.");
        }

        // Cerrar la base de datos
        odb.close();

    }//Fin main


    private static void jugadores14irlandafranciaitalia() {
        ODB odb = ODBFactory.open("neodatis1.test");
        ICriterion criterio = new And()
                .add(Where.equal("edad", 14))
                .add(new Or()
                        .add(Where.equal("pais.nombrePais", "IRLANDA"))
                        .add(Where.equal("pais.nombrePais", "ITALIA"))
                        .add(Where.equal("pais.nombrePais", "FRANCIA")));
        IQuery query = new CriteriaQuery(Jugadores.class, criterio);
        Objects<Jugadores> jugadores = odb.getObjects(query);

        if (jugadores.size() == 0) {
            System.out.println("No existen jugadores de 14 años de IRLANDA, ITALIA, FRANCIA.");
        } else {
            System.out.println("Jugadores de 14 años de IRLANDA, ITALIA, FRANCIA:");
            while (jugadores.hasNext()) {
                Jugadores jug = jugadores.next();
                System.out.printf("Nombre: %s, Edad: %d, Ciudad: %s, País: %s\n",
                        jug.getNombre(), jug.getEdad(), jug.getCiudad(), jug.getPais().getNombrePais());
            }
        }
        odb.close();
    }//Fin jugadores14irlandafranciaitalia


    private static void actualizaredadjugadoresdepais(String pais) {
        ODB odb = ODBFactory.open("neodatis1.test");
        ICriterion crit = Where.equal("pais.nombrePais", pais);
        IQuery query = new CriteriaQuery(Jugadores.class, crit);
        Objects<Jugadores> jugadores = odb.getObjects(query);

        if (jugadores.size() == 0) {
            System.out.printf("No existen jugadores de %s.\n", pais);
        } else {
            System.out.printf("ACTUALIZAMOS LA EDAD DE LOS JUGADORES DE %s:\n", pais);
            while (jugadores.hasNext()) {
                Jugadores jug = jugadores.next();
                int edadNueva = jug.getEdad() + 2;
                System.out.printf("Nombre: %s, Edad anterior: %d, Nueva edad: %d\n",
                        jug.getNombre(), jug.getEdad(), edadNueva);
                jug.setEdad(edadNueva);
                odb.store(jug);
            }
            odb.commit();
        }
        odb.close();
    }//Fin actualizaredadjugadoresdepais



    public static void mostrarDatos(Objects<Jugadores> objects, String mensaje) {
        System.out.println(mensaje);
        int i = 1;
        while(objects.hasNext()){
            Jugadores jug = objects.next();

            System.out.println((i++) + "\t: " + jug.getNombre() + "*" +
                    jug.getDeporte() + "*" + jug.getCiudad() + "*" + jug.getEdad()
                    + "*" + jug.getPais().getNombrePais());
        }
        System.out.println("------------");
    }//Fin mostrarDatos

}

