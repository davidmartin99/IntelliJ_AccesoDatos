package Ficheros_Aleatorios;

import java.io.*;
import java.util.Scanner;

/*
    Clase que elimina un empleado.
    Se debe poner un -1 al identificador para que al leerlo se salte todos los ID -1.
    Realmente no se borra.
 */
public class EmpleadoBorrar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat"); // Ajustar la ruta del fichero
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            // Solicitar el ID del empleado a borrar
            System.out.print("Introduce el ID del empleado que deseas borrar: ");
            int idBorrar = sc.nextInt();

            boolean empleadoEncontrado = false;
            file.seek(0); // Posicionamos al principio del archivo

            // Recorremos el archivo para buscar el empleado
            while (file.getFilePointer() < file.length()) {
                long posicionActual = file.getFilePointer(); // Guardamos la posición actual
                int idLeido = file.readInt(); // Leemos el ID

                // Si encontramos el ID que queremos borrar
                if (idLeido == idBorrar) {
                    empleadoEncontrado = true;
                    file.seek(posicionActual); // Nos posicionamos en el inicio del registro
                    file.writeInt(-1); // Ponemos el ID en -1 para "borrar" al empleado
                    System.out.println("Empleado con ID " + idBorrar + " borrado exitosamente.");
                    break; // Salimos del bucle
                }

                // Si el ID es -1, saltamos el registro
                if (idLeido == -1) {
                    // Saltamos el apellido (20 bytes) + dep (4 bytes) + salario (8 bytes)
                    file.skipBytes(32); // Total bytes to skip for each record
                    continue; // Saltamos el registro y continuamos con la siguiente iteración
                }

                // Saltamos los bytes del registro (apellido, dep, salario)
                file.skipBytes(32); // Saltamos apellido (20 bytes) + dep (4 bytes) + salario (8 bytes)
            }

            if (!empleadoEncontrado) {
                System.out.println("No se encontró ningún empleado con el ID " + idBorrar + ".");
            }

            file.close(); // Cerramos el fichero
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
