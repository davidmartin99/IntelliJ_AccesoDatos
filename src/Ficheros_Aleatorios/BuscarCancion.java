package Ficheros_Aleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/*
    4) Realizar una clase java que busque una canción por su identificador introducido por teclado y
    visualice los datos completos de la canción si existe o un mensaje indicando que no se ha encontrado.
 */

public class BuscarCancion {
    public static void main(String[] args) {
        RandomAccessFile file = null;

        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\cancionesAleatorio.dat");
            file = new RandomAccessFile(fichero, "r");

            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner sc = new Scanner(System.in);
            // Solicitar el ID de la canción a buscar
            System.out.print("Introduce el ID de la canción que deseas consultar: ");
            int idBuscado = sc.nextInt();

            boolean cancionEncontrada = false; // Variable para verificar si se encontró la canción

            // Recorremos el archivo en busca del ID
            file.seek(0); // Volver al principio del archivo
            while (file.getFilePointer() < file.length()) {
                int id = file.readInt(); // Leer el ID de la canción

                // Leer el resto de los datos de la canción
                int año = file.readInt(); // Leer año
                String titulo = leerCadena(file, 50); // Leer título
                String artista = leerCadena(file, 50); // Leer artista
                String duracion = leerCadena(file, 10); // Leer duración
                boolean esEspañola = file.readBoolean(); // Leer si es española

                // Comprobar si el ID coincide con el que se busca
                if (id == idBuscado) {
                    System.out.printf("ID: %d, Año: %d, Título: %s, Artista: %s, Duración: %s, ¿Es española? %s\n",
                            id, año, titulo, artista, duracion, (esEspañola ? "Sí" : "No"));
                    cancionEncontrada = true; // Indicar que se encontró la canción
                    break; // Salir del bucle
                }

                // Saltar el resto de la canción (ya se lee antes)
            }

            // Si no se encontró la canción, mostrar mensaje
            if (!cancionEncontrada) {
                System.out.printf("ID: %d, NO EXISTE CANCIÓN...\n", idBuscado);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el fichero
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//Fin main

    // Metodo para leer una cadena de longitud fija del archivo
    private static String leerCadena(RandomAccessFile archivo, int longitud) throws IOException {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            cadena.append(archivo.readChar()); // Leer caracteres
        }
        return cadena.toString().trim(); // Retornar cadena sin espacios finales
    }
}//Fin class
