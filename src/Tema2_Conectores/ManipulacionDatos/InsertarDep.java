package Tema2_Conectores.ManipulacionDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * boton derecho al programa y luego en "More Run/Debug" abrimos y metemos los parámetros
 */
public class InsertarDep {

    public static void main(String[] args) {
        // Cargar el driver JDBC de MySQL
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecemos la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hospital", // URL de la base de datos
                    "root",                        // Usuario de la base de datos
                    "1234"                         // Contraseña del usuario
            );

            // Recuperar argumentos desde el método main
            String dep = args[0];      // Número del departamento
            String dnombre = args[1];  // Nombre del departamento
            String loc = args[2];      // Localidad del departamento

            // Construir la sentencia SQL para insertar el nuevo departamento
            String sql = String.format(
                    "INSERT INTO dept VALUES (%s, '%s', '%s')",
                    dep, dnombre, loc
            );

            // Imprimir la sentencia SQL en consola para verificar que está correcta
            System.out.println(sql);

            // Crear un objeto Statement para ejecutar la sentencia SQL
            Statement sentencia = conexion.createStatement();

            // Ejecutar la sentencia de inserción y contar las filas afectadas
            int filas = sentencia.executeUpdate(sql);
            System.out.printf("Filas afectadas: %d %n", filas); // Mostrar las filas afectadas

            // Cerrar el objeto Statement y la conexión
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException cn) {
            // Manejar excepción si no se encuentra el driver JDBC
            cn.printStackTrace();
        } catch (SQLException e) {
            // Manejar excepciones SQL, como errores de conexión o en la sentencia SQL
            e.printStackTrace();
        }
    }
}
