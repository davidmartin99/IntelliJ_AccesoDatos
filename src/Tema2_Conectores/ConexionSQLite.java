package Tema2_Conectores;

import java.sql.*;

public class ConexionSQLite {
    public static void main(String[] args) {
        try {
            // Cargar el driver de SQLite
            Class.forName("org.sqlite.JDBC");

            // Conexión a la base de datos SQLite (ajustar la ruta según tu configuración)
            String dbURL = "jdbc:sqlite:C:/Users/aludam2/Desktop/sqlite-tools-win-x64-3470000/ejemplo.db";
            Connection conexion = DriverManager.getConnection(dbURL);

            // Crear un Statement para ejecutar la consulta
            Statement stmt = conexion.createStatement();
            // Ejecutar la consulta SQL
            ResultSet rs = stmt.executeQuery("SELECT * FROM departamentos");

            // Recorrer los resultados y mostrarlos
            while (rs.next()) {
                // Usar los nombres correctos de las columnas
                int deptNo = rs.getInt("dept_no");
                String dnombre = rs.getString("dnombre");
                String loc = rs.getString("loc");

                System.out.println("Dept No: " + deptNo);
                System.out.println("Nombre: " + dnombre);
                System.out.println("Localización: " + loc);
                System.out.println("----------------------------");
            }

            // Cerrar la conexión y los objetos
            rs.close();
            stmt.close();
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
