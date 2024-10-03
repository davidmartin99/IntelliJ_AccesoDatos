package Ficheros_Binarios;

import java.io.*;
import java.util.Scanner;

public class MainCancion {

    public static void main(String[] args) throws IOException {
        // Creamos un fichero para guardar las canciones
        Scanner sc = new Scanner(System.in);
        String fichero = "C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\out\\production\\Acceso_Datos\\Ficheros_Binarios\\canciones4.dat"; // Nombre del archivo donde se guardarán las canciones

        int opcion;

        do {
            // Mostrar el menú principal
            System.out.println("\n----- Menú -----");
            System.out.println(" 1. Añadir canción");
            System.out.println(" 2. Buscar canción por artista");
            System.out.println(" 3. Mostrar todas las canciones");
            System.out.println(" 4. Salir ");
            System.out.print("Selecciona una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Añadir nueva canción
                    añadirCancion(fichero, sc);
                    break;

                case 2:
                    // Buscar canción por artista
                    System.out.println("Introduce el nombre del artista: ");
                    String nombreBuscar = sc.nextLine();
                    buscarCancionPorArtista(fichero, nombreBuscar);
                    break;

                case 3:
                    // Mostrar todas las canciones
                    mostrarCanciones(fichero);
                    break;

                case 4:
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }

        } while (opcion != 4); // Repetir hasta que el usuario elija salir

        sc.close();
    }

    // Método para añadir una canción al fichero
    public static void añadirCancion(String fichero, Scanner sc) {
        try {
            File archivo = new File(fichero);
            ObjectOutputStream objectOS;

            // Si el archivo existe, usa MiObjectOutputStream para evitar sobrescribir el encabezado
            if (archivo.exists() && archivo.length() > 0) {
                objectOS = new MiObjectOutputStream(new FileOutputStream(archivo, true));
            } else {
                objectOS = new ObjectOutputStream(new FileOutputStream(archivo));
            }

            // Solicitar los datos de la canción
            System.out.println("Introduce los datos de la canción:");
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            System.out.print("Año: ");
            int año = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            System.out.print("Título: ");
            String titulo = sc.nextLine();
            System.out.print("Artista: ");
            String artista = sc.nextLine();
            System.out.print("Duración: ");
            String duracion = sc.nextLine();
            System.out.print("¿Es española? (true/false): ");
            boolean española = sc.nextBoolean();
            sc.nextLine(); // Limpiar el buffer

            // Crear un objeto Cancion con los datos introducidos
            Cancion cancion = new Cancion(id, año, titulo, artista, duracion, española);

            // Guardar el objeto en el archivo
            objectOS.writeObject(cancion);
            objectOS.close(); // Cerrar el flujo de salida
            System.out.println("Canción añadida exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar la canción: " + e.getMessage());
        }
    }


    // Método para buscar canciones por artista
    public static void buscarCancionPorArtista(String fichero, String nombreBuscar) {
        try (FileInputStream fileIn = new FileInputStream(fichero);
             ObjectInputStream objectIS = new ObjectInputStream(fileIn)) {

            boolean encontrado = false;
            while (true) {
                try {
                    Cancion cancion = (Cancion) objectIS.readObject();
                    if (cancion.getArtista().equalsIgnoreCase(nombreBuscar)) {
                        System.out.println(cancion);
                        encontrado = true;
                    }
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron canciones del artista: " + nombreBuscar);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método para mostrar todas las canciones
    public static void mostrarCanciones(String fichero) {
        try (ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream(fichero))) {
            boolean hayCanciones = false;
            while (true) {
                try {
                    Cancion cancion = (Cancion) objectIS.readObject();
                    System.out.println(cancion); // Llamamos al método toString() de Cancion
                    hayCanciones = true;
                } catch (EOFException e) {
                    break; // Hemos alcanzado el final del archivo
                }
            }
            if (!hayCanciones) {
                System.out.println("No hay canciones guardadas.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
