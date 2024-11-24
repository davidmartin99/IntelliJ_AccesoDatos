import java.sql.*;

public class ConexionDerby {
    public static void main(String[] args) {
        try {
            // Cargar el driver de Apache Derby
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // Conexión a la base de datos Apache Derby
            String dbURL = "jdbc:derby:D:/DB/DERBY/ejemplo";
            Connection conexion = DriverManager.getConnection(dbURL);

            // Crear un Statement para ejecutar la consulta
            Statement stmt = conexion.createStatement();
            // Ejecutar la consulta SQL
            ResultSet rs = stmt.executeQuery("SELECT * FROM departamentos");

            // Recorrer los resultados y mostrarlos
            while (rs.next()) {
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
