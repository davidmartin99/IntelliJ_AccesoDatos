package Tema2_Conectores.DescripcionDatos;

import java.sql.*;

public class EjemploResultSetMetadata {
    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hospital", "root", "1234");

            // Create a statement object to execute the query
            Statement sentencia = conexion.createStatement();

            // Execute the query
            ResultSet rs = sentencia.executeQuery("SELECT * FROM doctor");

            // Get metadata from the result set
            ResultSetMetaData rsmd = rs.getMetaData();

            // Get the number of columns in the result set
            int nColumnas = rsmd.getColumnCount();
            System.out.printf("Número de columnas recuperadas: %d\n", nColumnas);

            // Iterate through each column and print metadata
            for (int i = 1; i <= nColumnas; i++) {
                String nula = (rsmd.isNullable(i) == ResultSetMetaData.columnNoNulls) ? "NO" : "SI";
                System.out.printf("Columna %d:\n", i);
                System.out.printf("  Nombre: %s\n", rsmd.getColumnName(i));
                System.out.printf("  Tipo: %s\n", rsmd.getColumnTypeName(i));
                System.out.printf("  Puede ser nula?: %s\n", nula);
                System.out.printf("  Máximo ancho de la columna: %d\n", rsmd.getColumnDisplaySize(i));
                System.out.println();
            }

            // Close the resources
            rs.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
