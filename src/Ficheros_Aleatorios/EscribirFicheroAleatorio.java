package Ficheros_Aleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
    Crea el fichero aleatorio de empleados
 */
public class EscribirFicheroAleatorio {
    public static void main(String[] args) throws IOException {
        File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat");
        // Declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        // Array con los datos
        String apellido[] = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASILLA", "REY"}; // apellidos
        int dep[] = {10, 20, 10, 10, 30, 30, 20}; // departamentos
        Double salario[] = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0}; // salarios

        // Predefinido array de IDs
        int id[] = {1, 3, 5, 7, 4, 2, 9}; // IDs de los empleados

        int n = apellido.length; // número de elementos del array

        for (int i = 0; i < n; i++) { // recorro el array
            // Escribir los datos en el archivo
            file.writeInt(id[i]); // Insertar ID (4 bytes)

            // Ajustar apellido a 10 caracteres (20 bytes)
            StringBuffer buffer = new StringBuffer(apellido[i]);
            buffer.setLength(10); // 10 caracteres para el apellido
            file.writeChars(buffer.toString()); // Insertar apellido (20 bytes)

            // Insertar departamento (4 bytes)
            file.writeInt(dep[i]); // Insertar departamento (4 bytes)

            // Insertar salario (8 bytes)
            file.writeDouble(salario[i]); // Insertar salario (8 bytes)
        }

        file.close();
    } // Fin main
} // Fin class
