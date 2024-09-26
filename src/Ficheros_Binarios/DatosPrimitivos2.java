package Ficheros_Binarios;

import java.io.*;
/*
    Debe añadir una variable long al fichero de los datos primitivos,
    sin que se borren los datos anteriores y visualizarlo
 */

public class DatosPrimitivos2 {
    public static void main(String[] args) throws IOException {
        // Declarar el archivo
        File f2 = new File("src\\Ficheros_Binarios\\primitivos.dat");

        // Flujo de salida
        // "true" para no borrar lo anterior
        FileOutputStream fileout = new FileOutputStream(f2,true);
        DataOutputStream dataOS = new DataOutputStream(fileout);

        // Añadir variable tipo long
        long g = 1313131313;

        // Escribir los datos en el archivo
        dataOS.writeLong(g);

        // Cerrar el flujo de salida
        dataOS.close();

        // Flujo de entrada para leer el archivo
        FileInputStream filein = new FileInputStream(f2);
        DataInputStream dataIS = new DataInputStream(filein);

        // Leer los datos en el mismo orden en que fueron escritos
        try {
                byte by = dataIS.readByte();
                System.out.println("Esto es un Byte: " + by);
                char c = dataIS.readChar();
                System.out.println("Esto es un Caracter: " + c);
                boolean b = dataIS.readBoolean();
                System.out.println("Esto es un Boolean: " + b);
                int v = dataIS.readInt();
                System.out.println("Esto es un entero: " + v);
                String n = dataIS.readUTF();
                System.out.println("Esto es un String: " + n);

                //Leer el valor long añadido
                g = dataIS.readLong();
                System.out.println("Esto es un long: " + g);

        } catch (EOFException eof) {
            // Fin del archivo alcanzado
            System.out.println("Fin de la lectura del fichero.");
        } finally {
            // Cerrar el flujo de entrada
            dataIS.close();
        }
    } // Fin main
}
