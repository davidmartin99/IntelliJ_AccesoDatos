package Ficheros_Aleatorios;

import java.io.*;

public class EmpleadosListarBorrados {
    public static void main(String[] args) {
        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            long posicion = 0;
            while (posicion < file.length()) {
                file.seek(posicion); // Nos posicionamos al inicio del registro
                int id = file.readInt(); // Leemos el ID

                if (id == -1) {
                    System.out.printf("Empleado borrado en posición: %d\n", posicion / 36 + 1);
                }

                posicion += 36; // Pasamos al siguiente registro
            }

            file.close(); // Cerramos fichero
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}