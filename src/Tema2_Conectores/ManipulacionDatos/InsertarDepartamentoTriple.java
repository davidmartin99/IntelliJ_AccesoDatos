import java.sql.*;

/**
 * PrepareStatement
 */
public class InsertarDepartamentoTriple {
    public static void main(String[] args) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/hospital"; // URL de la base de datos
        String user = "root";  // Usuario de la base de datos
        String password = "1234"; // Contraseña de la base de datos

        // Crear la conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            // Sentencia SQL para insertar un departamento con placeholders
            String sqlInsert = "INSERT INTO dept VALUES(?, ?, ?)";

            // Crear un PreparedStatement para insertar datos
            PreparedStatement sentencia = conexion.prepareStatement(sqlInsert);

            // Insertar datos (primer departamento)
            sentencia.setInt(1, 10);
            sentencia.setString(2, "Ventas");
            sentencia.setString(3, "Madrid");
            sentencia.executeUpdate(); // Ejecuta la inserción para el primer departamento

            // Insertar datos (segundo departamento)
            sentencia.setInt(1, 20);
            sentencia.setString(2, "Marketing");
            sentencia.setString(3, "Barcelona");
            sentencia.executeUpdate(); // Ejecuta la inserción para el segundo departamento

            // Insertar datos (tercer departamento)
            sentencia.setInt(1, 30);
            sentencia.setString(2, "Recursos Humanos");
            sentencia.setString(3, "Alcorcon");
            sentencia.executeUpdate(); // Ejecuta la inserción para el tercer departamento

            // Mostrar mensaje de éxito
            System.out.println("Filas insertadas correctamente.");

            // Sentencia SQL para recuperar los departamentos insertados
            String sqlSelect = "SELECT * FROM dept";

            // Ejecutar la consulta SELECT para obtener todos los departamentos
            PreparedStatement selectStatement = conexion.prepareStatement(sqlSelect);
            ResultSet resultSet = selectStatement.executeQuery();  // Ejecuta la consulta

            // Mostrar los datos de los departamentos insertados
            System.out.println("Datos insertados en la tabla departamentos:");
            while (resultSet.next()) {
                int deptNo = resultSet.getInt("DEPT_NO");
                String dnombre = resultSet.getString("DNOMBRE");
                String loc = resultSet.getString("LOC");

                // Imprimir los datos de cada departamento
                System.out.println("Departamento Nº: " + deptNo + ", Nombre: " + dnombre + ", Localización: " + loc);
            }

        } catch (SQLException e) {
            // Si ocurre algún error durante la conexión o ejecución, se captura la excepción y se muestra el error
            e.printStackTrace();
        }
    }
}
