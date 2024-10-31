package Tema2_Conectores;

import java.sql.*;

public class MainPrueba1 {

    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establecemos la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/prueba1", "prueba1", "prueba1");

            // Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM alumno";
            ResultSet resul = sentencia.executeQuery(sql);

            /*
            // Verificar si hay datos en el ResultSet
                    if (!resul.next()) {
                        System.out.println("No hay registros en la tabla.");
                        return;
                    }else{
                        System.out.println("SI hay registros en la tabla.");
                    }
             */

            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van mostrando
            while (resul.next()) {
                System.out.printf("ID: %d, Nombre: %s, Dirección: %s, Localidad: %s%n",
                        resul.getInt("Codalumno"),
                        resul.getString("Nombre"),
                        resul.getString("Direccion"),
                        resul.getString("Localidad"));
            }

            // Mueve el puntero al último registro y lo muestra
            if (resul.last()) {
                System.out.printf("   -Último registro -> ID: %d, Nombre: %s, Dirección: %s, Localidad: %s%n",
                        resul.getInt("Codalumno"),
                        resul.getString("Nombre"),
                        resul.getString("Direccion"),
                        resul.getString("Localidad"));
            }

            // Obtener y mostrar el número de la fila actual (última posición después de .last())
            System.out.println("    -Número de la última fila: " + resul.getRow());

            // Mueve el puntero al registro anterior (penúltimo) y lo muestra
            if (resul.previous()) {
                System.out.printf("    -Registro anterior -> ID: %d, Nombre: %s, Dirección: %s, Localidad: %s%n",
                        resul.getInt("Codalumno"),
                        resul.getString("Nombre"),
                        resul.getString("Direccion"),
                        resul.getString("Localidad"));
            }

            // Mueve el puntero justo antes del primer registro
            resul.beforeFirst();
            System.out.println("Puntero movido justo antes del primer registro.");

            // Iterar por todos los registros usando next() desde el principio
            while (resul.next()) {
                System.out.printf("ID: %d, Nombre: %s, Dirección: %s, Localidad: %s%n",
                        resul.getInt("Codalumno"),
                        resul.getString("Nombre"),
                        resul.getString("Direccion"),
                        resul.getString("Localidad"));
                System.out.println("   Número de la fila actual: " + resul.getRow());
            }

            // Cerrar ResultSet
            resul.close();
            // Cerrar Statement
            sentencia.close();
            // Cerrar conexión
            conexion.close();

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // fin de main

} // fin de la clase

