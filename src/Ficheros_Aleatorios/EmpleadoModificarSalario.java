package Ficheros_Aleatorios;

import java.io.*;

public class EmpleadoModificarSalario {
    public static void main(String[] args) {
        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            // Solicitar el ID del empleado a modificar
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce el ID del empleado cuyo salario deseas modificar: ");
            int idModificar = Integer.parseInt(reader.readLine()); // ID del empleado a modificar
            System.out.print("Introduce el nuevo salario: ");
            double nuevoSalario = Double.parseDouble(reader.readLine()); // Nuevo salario

            boolean empleadoEncontrado = false; // Bandera para verificar si el empleado existe
            long longitudRegistro = 36; // Longitud del registro
            file.seek(0); // Posicionamos al principio del archivo

            // Recorremos el archivo para buscar el empleado por ID
            while (file.getFilePointer() < file.length()) {
                long posicionActual = file.getFilePointer(); // Guardamos la posición actual
                int idLeido = file.readInt(); // Leemos el ID

                // Saltar los registros eliminados
                if (idLeido == -1) {
                    file.skipBytes(24); // Saltamos apellido (20 bytes) + dep (4 bytes) + salario (8 bytes)
                    continue;
                }

                if (idLeido == idModificar) {
                    empleadoEncontrado = true; // Empleado encontrado
                    // Nos posicionamos en el salario (4 + 20 + 4 = 28 bytes)
                    file.seek(posicionActual + 28); // Nos posicionamos en el salario
                    file.writeDouble(nuevoSalario); // Modificamos el salario
                    System.out.println("Salario modificado exitosamente para el ID: " + idModificar);
                    break; // Salimos del bucle
                }

                // Saltamos los bytes del registro completo (ID, apellido, dep, salario)
                file.skipBytes(32); // 4 (ID) + 20 (apellido) + 4 (dep) + 4 (salario)
            }

            if (!empleadoEncontrado) {
                System.out.printf("ID: %d, NO EXISTE EMPLEADO...\n", idModificar);
            }

            file.close(); // Cerramos fichero
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
