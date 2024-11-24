package Tema2_Conectores.DescripcionDatos;

import java.sql.*;

/**
 * en DBeaver
 */
public class EjemploProcedimientos {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de que sea el driver correcto

            // Establecemos la conexión con la BD 'ejemplo'
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital", "root", "1234" // Cambia estos valores por los tuyos
            );

            // Obtener los metadatos de la base de datos
            DatabaseMetaData dbmd = conexion.getMetaData();

            // Obtener los procedimientos almacenados en el esquema 'ejemplo'
            ResultSet proc = dbmd.getProcedures(null, "ejemplo", null); // Obtener procedimientos del esquema 'ejemplo'

            while (proc.next()) {
                String procName = proc.getString("PROCEDURE_NAME");
                String procType = proc.getString("PROCEDURE_TYPE");

                // Mostrar los detalles del procedimiento
                System.out.printf("Nombre Procedimiento: %s, Tipo: %s%n", procName, procType);
            }

            // Cerrar la conexión
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
