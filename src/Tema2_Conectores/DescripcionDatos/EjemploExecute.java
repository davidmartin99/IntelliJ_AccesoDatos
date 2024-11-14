package Tema2_Conectores.DescripcionDatos;

import java.sql.*;

/**
 * pag 42. IMPORTANTE
 */

public class EjemploExecute {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Cargar el controlador JDBC para MySQL
        Class.forName("com.mysql.jdbc.Driver");

        // Establecer la conexión con la base de datos
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "1234");

        // Definir la consulta SQL que se va a ejecutar
        String sql = "SELECT * FROM doctor";  // Seleccionamos todas las columnas de la tabla doctor

        // Crear un objeto Statement para ejecutar la consulta
        Statement sentencia = conexion.createStatement();

        // Ejecutar la consulta SQL y comprobar si es una consulta (SELECT) o una actualización (INSERT, UPDATE, DELETE)
        boolean valor = sentencia.execute(sql);

        if (valor) {
            // Si la consulta es SELECT, obtenemos el ResultSet con los resultados
            ResultSet rs = sentencia.getResultSet();

            // Recorremos los resultados y mostramos las columnas
            while (rs.next()) {
                // Mostramos todas las columnas de cada fila de la tabla 'doctor'
                System.out.printf("Hospital Cod: %d, Doctor No: %d, Apellido: %s, Especialidad: %s, Salario: %d%n",
                        rs.getInt("HOSPITAL_COD"),
                        rs.getInt("DOCTOR_NO"),
                        rs.getString("APELLIDO"),
                        rs.getString("ESPECIALIDAD"),
                        rs.getInt("SALARIO"));
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
