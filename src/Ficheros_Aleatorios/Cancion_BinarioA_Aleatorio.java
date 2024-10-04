package Ficheros_Aleatorios;
/*
    Apartir del fichero binario de objetos de canciones.dat, crear un fichero aleatorio de canciones.
    Leer el fichero binario y pasarlo al aleatorio asignando los bytes.
 */

import Ficheros_Binarios.Cancion;
import java.io.*;

public class Cancion_BinarioA_Aleatorio {

    public static void main(String[] args) {
        // Rutas de los ficheros binario y aleatorio
        String ficheroBinario = "C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\out\\production\\Acceso_Datos\\Ficheros_Binarios\\canciones4.dat";
        // Fichero aleatorio de salida
        String ficheroAleatorio = "C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\src\\Ficheros_Aleatorios\\cancionesAleatorio.dat";

        try {
            leerFicheroBinarioYGuardarAleatorio(ficheroBinario, ficheroAleatorio);
            System.out.println("Fichero aleatorio creado o actualizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al crear o actualizar el fichero aleatorio: " + e.getMessage());
        }
    }//Fin main


    // Metodo que lee el fichero binario y guarda en un fichero aleatorio
    public static void leerFicheroBinarioYGuardarAleatorio(String ficheroBinario, String ficheroAleatorio) throws IOException {
        File archivoBinario = new File(ficheroBinario);
        File archivoAleatorio = new File(ficheroAleatorio);

        // Verificar si el fichero binario existe
        if (!archivoBinario.exists()) {
            System.err.println("Error: El fichero binario " + ficheroBinario + " no existe.");
            return;
        }

        // Si no existe, crear el fichero aleatorio
        if (!archivoAleatorio.exists()) {
            archivoAleatorio.createNewFile();
            System.out.println("Fichero aleatorio creado: " + ficheroAleatorio);
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoBinario));
             RandomAccessFile archivoAleatorioRAF = new RandomAccessFile(archivoAleatorio, "rw")) {

            while (true) {
                try {
                    // Leer el objeto canción desde el fichero binario
                    Cancion cancion = (Cancion) entrada.readObject();

                    // Moverse al final del fichero aleatorio
                    archivoAleatorioRAF.seek(archivoAleatorioRAF.length());

                    // Escribir la canción en el fichero aleatorio
                    escribirCancion(archivoAleatorioRAF, cancion);

                } catch (EOFException e) {
                    // Fin del fichero binario
                    System.out.println("Fin de lectura del fichero binario.");
                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("Clase no encontrada al leer el fichero binario.");
                    e.printStackTrace();
                }
            }
        }
    }//Fin leerFicheroBinarioYGuardarAleatorio


    // Metodo para escribir una canción en el fichero aleatorio con asignación de bytes
    private static void escribirCancion(RandomAccessFile archivo, Cancion cancion) throws IOException {
        // BYTES=  int ID (4 bytes) + int año (4 bytes) + String Titulo (50 * 2 = 100bytes) + String Artista (50 * 2 = 100bytes)
        //          + String Duracion (10 * 2 = 20bytes) + boolean esEspañola (1byte) = 229 bytes.
        archivo.writeInt(cancion.getId()); // ID de la canción
        archivo.writeInt(cancion.getAño()); // Año de la canción
        escribirCadena(archivo, cancion.getTitulo(), 50); // Título, ajustado a 50 caracteres
        escribirCadena(archivo, cancion.getArtista(), 50); // Artista, ajustado a 50 caracteres
        escribirCadena(archivo, cancion.getDuracion(), 10); // Duración, ajustado a 10 caracteres
        archivo.writeBoolean(cancion.getEspañola()); // Si es española o no
    }//Fin escribirCancion


    // Metodo para escribir cadenas con longitud fija en el fichero aleatorio
    private static void escribirCadena(RandomAccessFile archivo, String cadena, int longitud) throws IOException {
        StringBuffer buffer = new StringBuffer(cadena);
        buffer.setLength(longitud); // Ajustar el tamaño de la cadena
        archivo.writeChars(buffer.toString()); // Escribir los caracteres en el archivo
    }//Fin escribirCadena


}//Fin class
