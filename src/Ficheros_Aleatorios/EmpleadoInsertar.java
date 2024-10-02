package Ficheros_Aleatorios;

import java.io.*;

public class EmpleadoInsertar {
    public static void main(String[] args) {
        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            long posicion = file.length(); // Posicionar al final del archivo
            file.seek(posicion); // Nos posicionamos al final

            // Datos del nuevo empleado
            int id = 6; // ID del nuevo empleado
            String apellido = "MARTINEZ"; // Apellido
            int dep = 30; // Departamento
            double salario = 2500.45; // Salario

            // Insertamos los datos en el archivo
            file.writeInt(id); // Insertar ID
            StringBuffer buffer = new StringBuffer(apellido);
            buffer.setLength(10); // Ajustar el tamaño del apellido a 10 caracteres
            file.writeChars(buffer.toString()); // Insertar apellido
            file.writeInt(dep); // Insertar departamento
            file.writeDouble(salario); // Insertar salario

            file.close(); // Cerramos fichero
            System.out.println("Empleado insertado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}