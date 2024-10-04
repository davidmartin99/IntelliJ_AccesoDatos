package Ficheros_Aleatorios;

import Ficheros_Binarios.Cancion;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerCancionesAleatorio {

    public static void main(String[] args) {
        String ficheroAleatorio = "C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\src\\Ficheros_Aleatorios\\cancionesAleatorio.dat";

        try {
            leerFicheroAleatorio(ficheroAleatorio);
        } catch (IOException e) {
            System.err.println("Error al leer el fichero aleatorio: " + e.getMessage());
        }
    }//Fin main


    // Metodo para leer el fichero aleatorio y mostrar las canciones
    public static void leerFicheroAleatorio(String ficheroAleatorio) throws IOException {
        RandomAccessFile archivoAleatorioRAF = new RandomAccessFile(ficheroAleatorio, "r");

        // Leer registros hasta el final del fichero
        while (true) {
            try {
                // Leer cada campo según el formato de escritura
                int id = archivoAleatorioRAF.readInt();
                int año = archivoAleatorioRAF.readInt();
                String titulo = leerCadena(archivoAleatorioRAF, 50);
                String artista = leerCadena(archivoAleatorioRAF, 50);
                String duracion = leerCadena(archivoAleatorioRAF, 10);
                boolean española = archivoAleatorioRAF.readBoolean();

                // Crear objeto Cancion y mostrar información
                Cancion cancion = new Cancion(id, año, titulo, artista, duracion, española);
                System.out.println(cancion);

            } catch (EOFException e) {
                // Fin del fichero aleatorio
                System.out.println("Fin de lectura del fichero aleatorio.");
                break;
            }
        }

        archivoAleatorioRAF.close(); // Cerrar el archivo
    }//Fin leerFicheroAleatorio


    // Metodo para leer cadenas con longitud fija
    private static String leerCadena(RandomAccessFile archivo, int longitud) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            builder.append(archivo.readChar()); // Leer un caracter a la vez
        }
        return builder.toString().trim(); // Eliminar espacios en blanco al final
    }//Fin leerCadena

}//Fin class
