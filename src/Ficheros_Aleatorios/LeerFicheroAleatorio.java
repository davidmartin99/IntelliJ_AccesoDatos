package Ficheros_Aleatorios;

import java.io.*;

/*
    Clase que visualiza todos los empleados secuencialmente
 */
public class LeerFicheroAleatorio {
    public static void main(String[] args) throws IOException {
        File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo5.dat");
        // Declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, dep;
        double salario;
        char apellido[] = new char[10];

        long posicion = 0; // Para situarnos al principio

        while (posicion < file.length()) { // Loop until the end of the file
            // Recorro el fichero
            file.seek(posicion); // Nos posicionamos en la posición

            // Check if we can read the ID before actually reading it
            if (file.getFilePointer() >= file.length()) {
                break; // If at the end of file, break
            }

            id = file.readInt(); // Obtengo id de empleado

            // Si el ID es -1, saltamos el registro
            if (id == -1) {
                // Saltamos los bytes del registro (apellido, dep, salario)
                file.skipBytes(24); // 10 chars x 2 bytes + 4 bytes (dep) + 8 bytes (salario)
                posicion += 36; // Muevo la posición al siguiente registro
                continue; // Continuamos al siguiente registro
            }

            // Recorro uno a uno los caracteres del apellido
            for (int i = 0; i < apellido.length; i++) {
                apellido[i] = file.readChar(); // Los voy guardando en el array
            }

            // Convierto a String el array
            String apellidos = new String(apellido);
            dep = file.readInt(); // Obtengo dep
            salario = file.readDouble(); // Obtengo salario

            // Muestro la información del empleado
            System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f\n",
                    id, apellidos.trim(), dep, salario);

            // Me posiciono para el siguiente empleado, cada empleado ocupa 36 bytes
            posicion += 36; // Increment position for next record
        }

        file.close(); // Cerrar fichero
    }
}
