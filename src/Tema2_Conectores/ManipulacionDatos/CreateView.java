package Tema2_Conectores.ManipulacionDatos;

/**
 * Create View que muestre:
 * nombre de especialidad, numero de especialidad (siempre 1),
 * numero de doctores por especialidad y salario medio por especialidad
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateView {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/hospital"; // Cambia esta URL a la de tu base de datos
        String user = "root";          // Cambia al usuario de tu base de datos
        String password = "1234";      // Cambia a la contraseña de tu base de datos

        try (Connection conexion = DriverManager.getConnection(url, user, password);
             Statement sentencia = conexion.createStatement()) {

            // Construir la sentencia SQL para crear la vista "totales"
            StringBuilder sql = new StringBuilder();
            //  Salaros medios por especialidad
            sql.append("CREATE OR REPLACE VIEW totales ");
            //
            sql.append("(hospital_cod, num_especialidad, num_doctores, salario_medio) AS ");
            sql.append("SELECT especialidad, 1 AS num_especialidad, ");
            sql.append("COUNT(*) AS num_doctores, AVG(salario) AS salario_medio ");
            sql.append("FROM DOCTOR ");
            sql.append("GROUP BY especialidad");

            // Mostrar la sentencia SQL construida
            System.out.println(sql);

            // Ejecutar la sentencia SQL para crear la vista
            int filas = sentencia.executeUpdate(sql.toString());
            System.out.printf("Resultado de la ejecución: %d %n", filas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
