package Ficheros_Aleatorios;

import java.io.*;
import java.util.Scanner;

public class EmpleadoBorrar {
    public static void main(String[] args) {
        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            //int idBorrar = 4; // ID del empleado a borrar
            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner sc = new Scanner(System.in);
            // Solicitar el ID del empleado a buscar
            System.out.print("Introduce el ID del empleado que deseas borrar: ");
            int idBorrar = sc.nextInt();

            long posicion = (idBorrar - 1) * 36; // Calculamos la posición

            if (posicion >= file.length()) {
                System.out.printf("ID: %d, NO EXISTE EMPLEADO...\n", idBorrar);
            } else {
                file.seek(posicion); // Nos posicionamos al inicio del registro
                file.writeInt(-1); // Cambiamos el ID a -1
                System.out.println("Empleado borrado exitosamente.");
            }

            file.close(); // Cerramos fichero
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}