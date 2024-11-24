package Tema2_Conectores.ManipulacionDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarDepartamento {
    public static void main(String[] args) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/hospital"; // URL de la base de datos
        String user = "root";  // Usuario de la base de datos
        String password = "1234"; // Contraseña de la base de datos

        // Datos que se van a insertar en la tabla
        String dep = "10"; // Número del departamento
        String dnombre = "Ventas"; // Nombre del departamento
        String loc = "Madrid"; // Localización del departamento

        // Crear la conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            // Construir la sentencia SQL con placeholders (marcadores de posición)
            String sql = "INSERT INTO dept VALUES(?, ?, ?)"; // ? son los placeholders para los valores

            // Crear un PreparedStatement usando la sentencia SQL
            // Con uno que creemos vale
            // Es una plantilla pre-compilada en binario para que al pasarle los datos luego vaya más rápido
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            // 1º Asignar valores a los marcadores de posición usando los métodos setXXX()
            sentencia.setInt(1, Integer.parseInt(dep)); // El primer marcador de posición es para el número del departamento
            sentencia.setString(2, dnombre); // El segundo marcador es para el nombre del departamento
            sentencia.setString(3, loc); // El tercer marcador es para la localización del departamento

            // 2º Asignar valores a los marcadores de posición usando los métodos setXXX()
            sentencia.setInt(1, Integer.parseInt(dep)); // El primer marcador de posición es para el número del departamento
            sentencia.setString(2, dnombre); // El segundo marcador es para el nombre del departamento
            sentencia.setString(3, loc); // El tercer marcador es para la localización del departamento

            // Ejecutar la sentencia de inserción
            int filas = sentencia.executeUpdate(); // Ejecuta la actualización y devuelve el número de filas afectadas

            // Mostrar el resultado de la ejecución
            System.out.printf("Número de filas insertadas: %d%n", filas);

        } catch (SQLException e) {
            // Si ocurre algún error durante la conexión o ejecución, se captura la excepción y se muestra el error
            e.printStackTrace();
        }
    }
}
