package Ficheros_Aleatorios;

import java.io.*;
/*
    Clase que muestra los empleados borrados
 */
public class EmpleadosListarBorrados {
    public static void main(String[] args) {
        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            long posicion = 0;
            while (posicion < file.length()) {
                file.seek(posicion); // Nos posicionamos al inicio del registro
                int id = file.readInt(); // Leemos el ID

                if (id == -1) {
                    // El registro está borrado, así que leemos el resto de los campos
                    // Saltamos el apellido (20 bytes), dep (4 bytes), y salario (8 bytes)
                    StringBuilder apellido = new StringBuilder();
                    for (int i = 0; i < 10; i++) {
                        apellido.append(file.readChar()); // Leer cada caracter del apellido
                    }
                    // Leemos el departamento
                    int departamento = file.readInt();
                    // Leemos el salario
                    double salario = file.readDouble();

                    // Mostrar detalles del empleado borrado
                    System.out.printf("Empleado borrado - ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f\n",
                            id, apellido.toString().trim(), departamento, salario);
                } else {
                    // Si el ID no es -1, saltamos el resto del registro
                    file.skipBytes(32); // Saltamos apellido (20 bytes) + dep (4 bytes) + salario (8 bytes)
                }

                posicion += 36; // Pasamos al siguiente registro
            }

            file.close(); // Cerramos fichero
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
