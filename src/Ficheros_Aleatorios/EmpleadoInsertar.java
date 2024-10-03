package Ficheros_Aleatorios;

import java.io.*;
import java.util.Scanner;

/*
    Insertar empleado en el fichero aleatorio,
    si ya existe el ID, deberá mostrar un mensaje indicandolo y no insertarlo,
    si no existe el ID insertarlo.
 */
public class EmpleadoInsertar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RandomAccessFile file = null;

        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat");
            file = new RandomAccessFile(fichero, "rw");

            // Solicitar el ID del nuevo empleado por teclado
            System.out.print("Introduce el ID del empleado: ");
            int id = sc.nextInt();

            // Comprobamos si el ID ya existe
            boolean idExiste = false;
            file.seek(0); // Volvemos al principio del archivo para verificar IDs
            while (file.getFilePointer() < file.length()) {
                int idLeido = file.readInt(); // Leer el ID actual

                // Si el ID es -1, saltamos el registro
                if (idLeido == -1) {
                    // Saltar apellido (20 bytes) + dep (4 bytes) + salario (8 bytes)
                    file.skipBytes(32);
                    continue; // Continuamos al siguiente registro
                }

                // Saltar apellido (20 bytes) + dep (4 bytes) + salario (8 bytes)
                file.skipBytes(32);

                if (idLeido == id) {
                    idExiste = true;
                    break;
                }
            }

            // Si el ID ya existe, mostramos el mensaje y cerramos el programa
            if (idExiste) {
                System.out.println("ID " + id + " ya asignado. No se puede insertar el empleado.");
                return; // Termina el programa si el ID ya está asignado
            }

            // Si el ID no está asignado, solicitamos el resto de los datos del empleado
            System.out.print("Introduce el apellido del empleado (máximo 10 caracteres): ");
            String apellido = sc.next();
            if (apellido.length() > 10) {
                System.out.println("El apellido debe tener como máximo 10 caracteres. Se truncará.");
                apellido = apellido.substring(0, 10);
            }

            System.out.print("Introduce el departamento del empleado: ");
            int dep = sc.nextInt();

            System.out.print("Introduce el salario del empleado: ");
            double salario = sc.nextDouble();

            // Posicionar al final del archivo para insertar el nuevo empleado
            file.seek(file.length());

            // Insertar los datos del nuevo empleado en el archivo
            file.writeInt(id); // Insertar ID (4 bytes)

            // Asegurar que el apellido ocupe 20 bytes (10 caracteres)
            StringBuffer buffer = new StringBuffer(apellido);
            buffer.setLength(10); // Ajustar el tamaño del apellido a 10 caracteres
            file.writeChars(buffer.toString()); // Insertar apellido (20 bytes)
            file.writeInt(dep); // Insertar departamento (4 bytes)
            file.writeDouble(salario); // Insertar salario (8 bytes)

            System.out.println("Empleado insertado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } finally {
            // Asegurarse de cerrar el archivo y el escáner
            try {
                if (file != null) {
                    file.close();
                }
                sc.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }
}
