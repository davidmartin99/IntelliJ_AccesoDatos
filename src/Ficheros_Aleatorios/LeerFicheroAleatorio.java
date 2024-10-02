package Ficheros_Aleatorios;

import java.io.*;
public class LeerFicheroAleatorio {
    public static void main(String[] args) throws IOException {
        File fichero = new File("src\\Ficheros_Aleatorios\\AleatorioEjemplo.dat");
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, dep, posicion;
        Double salario;
        char apellido[] = new char[10], aux;

        posicion = 0; //para situarnos al principio

        for (;;) {
            //recorro el fichero
            file.seek(posicion); //nos posicionamos en la posición
            id = file.readInt(); //obtengo id de empleado

            //recorro uno a uno los caracteres del apellido
            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar(); //los voy guardando en el array
                apellido[i] = aux;
            }

            //convierto a String el array
            String apellidos = new String(apellido);
            dep = file.readInt(); //obtengo dep
            salario = file.readDouble(); //obtengo salario

            if (id > 0) {
                System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f\n",
                        id, apellidos.trim(), dep, salario);
            }
            //me posiciono para el siguiente empleado, cada empleado ocupa 36 bytes
            posicion = posicion + 36;

            //si he recorrido todos los bytes salgo del for
            if (file.getFilePointer() == file.length()) break;
        }

        file.close(); //cerrar fichero
    }
}
