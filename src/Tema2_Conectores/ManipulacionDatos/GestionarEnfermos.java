import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * pag 49
 */

public class GestionarEnfermos {
    public static void main(String[] args) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/hospital"; // URL de la base de datos
        String user = "root";  // Usuario de la base de datos
        String password = "1234"; // Contraseña de la base de datos

        // Conexión y ejecución de inserciones
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            // Sentencia SQL para insertar un nuevo enfermo con placeholders
            String sqlInsert = "INSERT INTO ENFERMO (INSCRIPCION, APELLIDO, DIRECCION, FECHA_NAC, SEXO, NSS) VALUES(?, ?, ?, ?, ?, ?)";

            // Crear el PreparedStatement para insertar datos
            PreparedStatement sentencia = conexion.prepareStatement(sqlInsert);

            // Primer enfermo con NSS
            sentencia.setInt(1, 10001);  // INSCRIPCION
            sentencia.setString(2, "Pérez");  // APELLIDO
            sentencia.setString(3, "Calle Real 1");  // DIRECCION
            sentencia.setDate(4, java.sql.Date.valueOf("1990-06-15"));  // FECHA_NAC
            sentencia.setString(5, "M");  // SEXO
            sentencia.setInt(6, 987654321);  // NSS (con valor)
            sentencia.executeUpdate();

            // Segundo enfermo con NSS NULL
            sentencia.setInt(1, 10002);  // INSCRIPCION
            sentencia.setString(2, "Martínez");  // APELLIDO
            sentencia.setString(3, "Calle Falsa 2");  // DIRECCION
            sentencia.setDate(4, java.sql.Date.valueOf("1985-11-20"));  // FECHA_NAC
            sentencia.setString(5, "F");  // SEXO
            sentencia.setNull(6, java.sql.Types.INTEGER);  // NSS (NULL)
            sentencia.executeUpdate();

            // Consultar los registros insertados
            String sqlSelect = "SELECT INSCRIPCION, APELLIDO, DIRECCION, FECHA_NAC, SEXO, NSS FROM ENFERMO";
            PreparedStatement selectSentencia = conexion.prepareStatement(sqlSelect);
            ResultSet rs = selectSentencia.executeQuery();

            // Mostrar los resultados
            System.out.println("Enfermos registrados:");
            while (rs.next()) {
                int inscripcion = rs.getInt("INSCRIPCION");
                String apellido = rs.getString("APELLIDO");
                String direccion = rs.getString("DIRECCION");
                java.sql.Date fechaNac = rs.getDate("FECHA_NAC");
                String sexo = rs.getString("SEXO");
                Integer nss = (Integer) rs.getObject("NSS"); // Usamos Object para poder manejar null

                // Mostrar la información
                System.out.printf("Inscripción: %d, Apellido: %s, Dirección: %s, Fecha Nac: %s, Sexo: %s, NSS: %s\n",
                        inscripcion, apellido, direccion, fechaNac.toString(), sexo, nss);
            }

            // Cerrar los recursos
            rs.close();
            selectSentencia.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            // Captura de excepciones y mostrar mensaje de error
            e.printStackTrace();
        }
    }
}
