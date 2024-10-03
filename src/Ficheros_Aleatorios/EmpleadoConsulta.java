package Ficheros_Aleatorios;

import java.io.*;
import java.util.Scanner;

/*
    Clase que consulta empleado por su número de ID,
    y muestra el resultado
 */
public class EmpleadoConsulta {
    public static void main(String[] args) {
        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner sc = new Scanner(System.in);
            // Solicitar el ID del empleado a buscar
            System.out.print("Introduce el ID del empleado que deseas consultar: ");
            int identificador = sc.nextInt();

            boolean empleadoEncontrado = false; // Variable para verificar si se encontró el empleado

            // Recorremos el archivo en busca del ID
            file.seek(0); // Volver al principio del archivo
            while (file.getFilePointer() < file.length()) {
                int id = file.readInt(); // Leer el ID del empleado

                // Saltar apellido (10 caracteres x 2 bytes) + dep (4 bytes) + salario (8 bytes)
                char[] apellido = new char[10];
                for (int i = 0; i < apellido.length; i++) {
                    apellido[i] = file.readChar(); // Obtener apellido
                }
                int dep = file.readInt(); // Obtener departamento
                double salario = file.readDouble(); // Obtener salario

                // Comprobar si el ID coincide con el que se busca
                if (id == identificador) {
                    String apellidos = new String(apellido).trim(); // Convertir a String y eliminar espacios
                    System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f\n",
                            id, apellidos, dep, salario);
                    empleadoEncontrado = true; // Indicar que se encontró el empleado
                    break; // Salir del bucle
                }
            }

            // Si no se encontró el empleado, mostrar mensaje
            if (!empleadoEncontrado) {
                System.out.printf("ID: %d, NO EXISTE EMPLEADO...\n", identificador);
            }

            file.close(); // Cerrar fichero
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
