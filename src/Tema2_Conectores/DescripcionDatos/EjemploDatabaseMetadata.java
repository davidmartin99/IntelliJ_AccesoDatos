package Tema2_Conectores.DescripcionDatos;

import java.sql.*;
/**
 * CONECTARSE CON DBEAVER
 */
public class EjemploDatabaseMetadata {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de que sea el driver correcto

            // Establecemos la conexión con la BD 'hospital'
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital", "root", "1234" // Cambia estos valores por los tuyos
            );

            // Obtener los metadatos de la base de datos
            DatabaseMetaData dbmd = conexion.getMetaData();

            // Obtener información sobre la base de datos
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
            System.out.println("=====");
            System.out.printf("Nombre: %s%n", nombre);
            System.out.printf("Driver: %s%n", driver);
            System.out.printf("URL: %s%n", url);
            System.out.printf("Usuario: %s%n", usuario);

            // Obtener información de las tablas
            System.out.println("\nTablas en la base de datos:");
            ResultSet resul = dbmd.getTables(null, null, null, new String[]{"TABLE"});
            while (resul.next()) {
                String catalogo = resul.getString(1); // columna 1
                String esquema = resul.getString(2);  // columna 2
                String tabla = resul.getString(3);    // columna 3
                String tipo = resul.getString(4);     // columna 4

                System.out.printf("Tipo: %s, Catálogo: %s, Esquema: %s, Nombre: %s%n", tipo, catalogo, esquema, tabla);
            }

            // Cerrar conexión
            conexion.close();
        } catch (ClassNotFoundException cn) {
            System.out.println("Driver no encontrado. Verifica el nombre del driver y la dependencia.");
            cn.printStackTrace();
        } catch (SQLException se) {
            System.out.println("Error en la conexión a la base de datos.");
            se.printStackTrace();
        }
    }
}
