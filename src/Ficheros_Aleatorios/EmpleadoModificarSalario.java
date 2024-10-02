package Ficheros_Aleatorios;

import java.io.*;

public class EmpleadoModificarSalario {
    public static void main(String[] args) {
        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            int idModificar = 3; // ID del empleado a modificar
            double nuevoSalario = 4000.87; // Nuevo salario

            long posicion = (idModificar - 1) * 36; // Calculamos la posición
            if (posicion >= file.length()) {
                System.out.printf("ID: %d, NO EXISTE EMPLEADO...\n", idModificar);
            } else {
                file.seek(posicion + 30); // Nos posicionamos en el salario
                file.writeDouble(nuevoSalario); // Modificamos el salario
                System.out.println("Salario modificado exitosamente.");
            }

            file.close(); // Cerramos fichero
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}