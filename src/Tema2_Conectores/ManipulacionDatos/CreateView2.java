package Tema2_Conectores.ManipulacionDatos;

/**
 * Create View que muestre:
 * nombre de especialidad (OFICIO), numero de especialidad (empleados por oficio),
 * numero de doctores (empleados) por especialidad y salario medio por especialidad
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class CreateView2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/hospital"; // Cambia esta URL a la de tu base de datos
        String user = "root";          // Cambia al usuario de tu base de datos
        String password = "1234";      // Cambia a la contraseña de tu base de datos

        try (Connection conexion = DriverManager.getConnection(url, user, password);
             Statement sentencia = conexion.createStatement()) {

            // Construir la sentencia SQL para crear la vista "totales"
            StringBuilder sql = new StringBuilder();
            sql.append("CREATE OR REPLACE VIEW totales AS ");
            sql.append("SELECT d.DEPT_NO AS departamento, ");
            sql.append("d.DNOMBRE AS nombre_departamento, ");
            sql.append("COUNT(e.EMP_NO) AS num_empleados, ");
            sql.append("AVG(e.SALARIO) AS salario_medio ");
            sql.append("FROM DEPT d ");
            sql.append("LEFT JOIN EMP e ON d.DEPT_NO = e.DEPT_NO ");  // Join entre DEPT y EMP
            sql.append("GROUP BY d.DEPT_NO, d.DNOMBRE");  // Agrupamos por departamento

            // Mostrar la sentencia SQL construida
            System.out.println(sql);

            // Ejecutar la sentencia SQL para crear la vista
            int filas = sentencia.executeUpdate(sql.toString());
            System.out.printf("Resultado de la ejecución: %d %n", filas);

            // Ahora seleccionamos los datos de la vista 'totales'
            String selectSQL = "SELECT * FROM totales";
            ResultSet resultSet = sentencia.executeQuery(selectSQL);

            // Imprimir los resultados de la consulta
            while (resultSet.next()) {
                int departamento = resultSet.getInt("departamento");
                String nombreDepartamento = resultSet.getString("nombre_departamento");
                int numEmpleados = resultSet.getInt("num_empleados");
                double salarioMedio = resultSet.getDouble("salario_medio");

                System.out.print(" -Departamento:" + departamento);
                System.out.print("  -Nombre del departamento:" + nombreDepartamento);
                System.out.print(" -Número de empleados:" + numEmpleados);
                System.out.println("  -Salario medio:" + salarioMedio);
                System.out.println("-------------------------------");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
