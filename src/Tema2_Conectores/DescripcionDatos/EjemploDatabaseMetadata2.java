package Tema2_Conectores.DescripcionDatos;

import java.sql.*;

public class EjemploDatabaseMetadata2 {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexión con la BD 'hospital'
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "1234");
            DatabaseMetaData dbmd = conexion.getMetaData();

            // Obtener información sobre la base de datos
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
            System.out.println("=====");
            System.out.printf("Nombre: %s%n", nombre);
            System.out.printf("Driver: %s%n", driver);
            System.out.printf("URL: %s%n", url);
            System.out.printf("Usuario: %s%n", usuario);

            // Obtener información de las tablas
            ResultSet resul = dbmd.getTables(null, "hospital", null, null);
            while (resul.next()) {
                String catalogo = resul.getString(1);
                String esquema = resul.getString(2);
                String tabla = resul.getString(3);
                String tipo = resul.getString(4);

                System.out.printf("Tipo: %s, Catálogo: %s, Esquema: %s, Nombre: %s%n", tipo, catalogo, esquema, tabla);
            }

            // Obtener columnas de la tabla departamentos
            System.out.println("COLUMNAS TABLA DEPARTAMENTOS:");
            ResultSet columnas = dbmd.getColumns(null, "hospital", "departamentos", null);
            while (columnas.next()) {
                String nombCol = columnas.getString("COLUMN_NAME");
                String tipoCol = columnas.getString("TYPE_NAME");
                String tamCol = columnas.getString("COLUMN_SIZE");
                String nula = columnas.getString("IS_NULLABLE");
                System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s, ¿Puede ser Nula?: %s%n", nombCol, tipoCol, tamCol, nula);
            }

            // Obtener claves primarias
            System.out.println("Clave Primaria:");
            ResultSet pk = dbmd.getPrimaryKeys(null, "hospital", "departamentos");
            String pkDep = "";
            String separador = "";
            while (pk.next()) {
                pkDep += separador + pk.getString("COLUMN_NAME");
                separador = "+";
            }
            System.out.println("Clave Primaria: " + pkDep);

            // Obtener claves ajenas exportadas
            System.out.println("Claves ajenas exportadas:");
            ResultSet fk = dbmd.getExportedKeys(null, "hospital", "departamentos");
            while (fk.next()) {
                String pkName = fk.getString("PKCOLUMN_NAME");
                String fkName = fk.getString("FKCOLUMN_NAME");
                String pkTableName = fk.getString("PKTABLE_NAME");
                String fkTableName = fk.getString("FKTABLE_NAME");
                System.out.printf("Tabla PK: %s, Clave Primaria: %s%n", pkTableName, pkName);
                System.out.printf("Tabla FK: %s, Clave Ajena: %s%n", fkTableName, fkName);
            }

            // Obtener claves ajenas importadas
            System.out.println("Claves ajenas importadas:");
            ResultSet fkImported = dbmd.getImportedKeys(null, "hospital", "empleados");
            while (fkImported.next()) {
                String pkName = fkImported.getString("PKCOLUMN_NAME");
                String fkName = fkImported.getString("FKCOLUMN_NAME");
                String pkTableName = fkImported.getString("PKTABLE_NAME");
                String fkTableName = fkImported.getString("FKTABLE_NAME");
                System.out.printf("Tabla PK: %s, Clave Primaria: %s%n", pkTableName, pkName);
                System.out.printf("Tabla FK: %s, Clave Ajena: %s%n", fkTableName, fkName);
            }

            // Cerrar conexión
            conexion.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
