package Tema2_Conectores.ManipulacionDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModificarSalario {
    public static void main(String[] args) {
        // Comprobar que se han pasado los argumentos necesarios
        if (args.length < 2) {
            System.out.println("Uso: java ModificarSalario <codigo_hospital> <subida>");
            return;
        }

        // Recuperar los argumentos de main
        String hospitalCod = args[0];   // Código del hospital
        String subida = args[1];        // Monto de la subida

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hospital", "root", "1234"
            );

            // Construir la sentencia SQL de actualización
            String sql = String.format(
                    "UPDATE DOCTOR SET SALARIO = SALARIO + %s WHERE HOSPITAL_COD = %s",
                    subida, hospitalCod
            );

            // Imprimir la sentencia SQL para verificarla
            System.out.println(sql);

            // Crear el objeto Statement para ejecutar la sentencia SQL
            Statement sentencia = conexion.createStatement();

            // Ejecutar la sentencia SQL y obtener el número de filas afectadas
            int filas = sentencia.executeUpdate(sql);

            // Imprimir el número de doctores modificados
            System.out.printf("Doctores modificados: %d %n", filas);

            // Cerrar el Statement y la conexión
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace(); // Manejar excepción de driver no encontrado
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar errores de SQL
        }
    }
}
