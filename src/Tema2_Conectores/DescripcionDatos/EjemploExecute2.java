package Tema2_Conectores.DescripcionDatos;

import java.sql.*;

/**
 * en DBeaver
 * Ejemplo de actualización de la tabla 'doctor' para convertir la especialidad a mayúsculas
 */
public class EjemploExecute2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Cargar el controlador JDBC para MySQL
        Class.forName("com.mysql.jdbc.Driver");

        // Establecer la conexión con la base de datos
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "1234");

        // Definir la consulta SQL que se va a ejecutar
        String sql = "UPDATE doctor SET especialidad = UPPER(especialidad)";  // Convertir especialidad a mayúsculas

        // Crear un objeto Statement para ejecutar la consulta
        Statement sentencia = conexion.createStatement();

        // Ejecutar la consulta SQL y comprobar si es una consulta (SELECT) o una actualización (INSERT, UPDATE, DELETE)
        boolean valor = sentencia.execute(sql);

        if (valor) {
            // Si la consulta es SELECT, obtenemos el ResultSet con los resultados
            ResultSet rs = sentencia.getResultSet();

            // Recorremos los resultados y mostramos las columnas
            while (rs.next()) {
                System.out.printf("%d, %s, %s%n", rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();  // Cerramos el ResultSet cuando ya no lo necesitamos
        } else {
            // Si la consulta es una actualización (INSERT, UPDATE, DELETE), obtenemos el número de filas afectadas
            int filasAfectadas = sentencia.getUpdateCount();
            System.out.printf("Filas afectadas: %d%n", filasAfectadas);
        }

        // Cerramos la sentencia y la conexión
        sentencia.close();
        conexion.close();
    }
}
