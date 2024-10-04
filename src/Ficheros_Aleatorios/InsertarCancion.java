package Ficheros_Aleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
/*
    3) Realizar una clase java que permita añadir al final del fichero aleatorio de canciones una nueva
    canción. El id de la canción se generará automáticamente.
 */
public class InsertarCancion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RandomAccessFile file = null;

        try {
            File fichero = new File("src\\Ficheros_Aleatorios\\cancionesAleatorio.dat");
            file = new RandomAccessFile(fichero, "rw");

            // Determinar el próximo ID
            int nuevoId = obtenerSiguienteId(file);
            System.out.println("El nuevo ID de la canción será: " + nuevoId);

            // Solicitar el resto de los datos de la canción
            System.out.print("Introduce el año de la canción: ");
            int año = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            System.out.print("Introduce el título de la canción (máximo 50 caracteres): ");
            String titulo = sc.nextLine();
            if (titulo.length() > 50) {
                System.out.println("El título debe tener como máximo 50 caracteres. Se truncará.");
                titulo = titulo.substring(0, 50);
            }

            System.out.print("Introduce el artista de la canción (máximo 50 caracteres): ");
            String artista = sc.nextLine();
            if (artista.length() > 50) {
                System.out.println("El artista debe tener como máximo 50 caracteres. Se truncará.");
                artista = artista.substring(0, 50);
            }

            System.out.print("Introduce la duración de la canción (máximo 10 caracteres): ");
            String duracion = sc.nextLine();
            if (duracion.length() > 10) {
                System.out.println("La duración debe tener como máximo 10 caracteres. Se truncará.");
                duracion = duracion.substring(0, 10);
            }

            System.out.print("¿Es española? (true/false): ");
            boolean esEspañola = sc.nextBoolean();

            // Posicionar al final del archivo para insertar la nueva canción
            file.seek(file.length());

            // Insertar los datos de la nueva canción en el archivo
            file.writeInt(nuevoId); // Insertar ID (4 bytes)
            file.writeInt(año); // Insertar año (4 bytes)

            // Asegurar que el título ocupe 100 bytes (50 caracteres)
            StringBuffer bufferTitulo = new StringBuffer(titulo);
            bufferTitulo.setLength(50); // Ajustar el tamaño del título a 50 caracteres
            file.writeChars(bufferTitulo.toString()); // Insertar título (100 bytes)

            // Asegurar que el artista ocupe 100 bytes (50 caracteres)
            StringBuffer bufferArtista = new StringBuffer(artista);
            bufferArtista.setLength(50); // Ajustar el tamaño del artista a 50 caracteres
            file.writeChars(bufferArtista.toString()); // Insertar artista (100 bytes)

            // Asegurar que la duración ocupe 20 bytes (10 caracteres)
            StringBuffer bufferDuracion = new StringBuffer(duracion);
            bufferDuracion.setLength(10); // Ajustar el tamaño de la duración a 10 caracteres
            file.writeChars(bufferDuracion.toString()); // Insertar duración (20 bytes)

            file.writeBoolean(esEspañola); // Insertar si es española (1 byte)

            System.out.println("Canción insertada correctamente.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } finally {
            // Asegurarse de cerrar el archivo y el escáner
            try {
                if (file != null) {
                    file.close();
                }
                sc.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }//Fin main


    // Metodo para obtener el siguiente ID basado en el último ID en el archivo
    private static int obtenerSiguienteId(RandomAccessFile file) throws IOException {
        int ultimoId = -1; // Valor por defecto si no hay canciones
        file.seek(0); // Volver al principio del archivo

        while (file.getFilePointer() < file.length()) {
            int idLeido = file.readInt(); // Leer el ID actual

            // Si el ID es -1, saltar el registro
            if (idLeido == -1) {
                file.skipBytes(225); // Saltar año (4 bytes) + título (100 bytes) + artista (100 bytes) + duración (20 bytes) + booleano (1 byte)
                continue; // Continuar al siguiente registro
            }

            // Saltar el resto de los datos de la canción
            file.skipBytes(225);
            ultimoId = idLeido; // Actualizar el último ID leído
        }

        return ultimoId + 1; // Retornar el siguiente ID
    }
}
