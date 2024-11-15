import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarSueldo {
    public static void main(String[] args) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/hospital"; // URL de la base de datos
        String user = "root";  // Usuario de la base de datos
        String password = "1234"; // Contraseña de la base de datos

        // Actualización del sueldo del empleado con EMP_NO = 7839
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            // Sentencia SQL para actualizar el sueldo
            String sql = "UPDATE EMP SET SALARIO = SALARIO + 50 WHERE EMP_NO = ?";

            // Preparar el statement con la sentencia SQL
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, 7839);  // Seteamos el EMP_NO

            // Ejecutar la actualización
            int filasAfectadas = sentencia.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);

            // Cerrar recursos
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }
}
