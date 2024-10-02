package Ficheros_Aleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirFicheroAleatorio {
    public static void main(String[] args) throws IOException {
        File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo.dat");
        // Declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        // Array con los datos
        String apellido[] = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS"
                , "SEVILLA", "CASILLA", "REY"}; //apellidos

        int dep[] = {10, 20, 10, 10, 30, 30, 20}; //departamentos
        Double salario[] = {1000.45, 2400.60, 3000.0, 1500.56,
                2200.0, 1435.87, 2000.0}; //salarios

        StringBuffer buffer = null; //Buffer para almacenar apellido
        int n = apellido.length; //numero de elementos del array

        for(int i = 0; i < n; i++) { //recorro el array
            file.writeInt(i + 1); //uso i+1 para identificar empleado
            buffer = new StringBuffer(apellido[i]);
            buffer.setLength(10); // 10 characters for the surname
            file.writeChars(buffer.toString()); // Insert surname
            file.writeInt(dep[i]); // Insert department
            file.writeDouble(salario[i]); // Insert salary
        }

        file.close();



    }//Fin main

}//Fin class
