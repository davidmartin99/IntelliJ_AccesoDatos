package Ficheros_Binarios;

/*
    Main de objeto Cancion
    El usuario añade canciones y busca por artista

    Se usa la clase creada MiObjectOutputStream para que no de error al leer el archivo
 */
import java.io.*;
import java.util.Scanner;

public class MainCancion {
    public static void main(String[] args) throws IOException {
        //Creamos un fichero para guardar las canciones
        Scanner sc = new Scanner(System.in);
        String fichero = "C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\src\\Ficheros_Binarios\\canciones1.dat"; // Nombre del archivo donde se guardarán las canciones

        int opcion;

        do {
            // Mostrar el menú principal
            System.out.println("\n----- Menú -----");
            System.out.println(" 1. Añadir canción");
            System.out.println(" 2. Buscar canción");
            System.out.println(" 3. Salir ");
            System.out.print("Selecciona una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Solicitar los datos de la canción
                    try {
                        // Determinar si el archivo existe
                        FileOutputStream fileout = new FileOutputStream(fichero, true);
                        ObjectOutputStream objectOS;

                        // Si el archivo existe, usar MiObjectOutputStream
                        if (new File(fichero).exists()) {
                            objectOS = new ObjectOutputStream(fileout);
                        } else {
                            objectOS = new MiObjectOutputStream(fileout);
                        }

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
                        System.out.println("Canción añadida exitosamente.");

                        objectOS.close(); // Cerrar el flujo de salida

                    } catch (IOException e) {
                        System.err.println("Error al guardar la canción: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Ahora vamos a leer el contenido del fichero y mostrarlo por consola
                    System.out.println("Introduce el nombre del artista: ");
                    String nombreBuscar = sc.nextLine();

                    try (FileInputStream fileIn = new FileInputStream(fichero);
                         ObjectInputStream objectIS = new ObjectInputStream(fileIn)) {

                        boolean encontrado = false;
                        while (fileIn.available() > 0) {
                            Cancion cancion = (Cancion) objectIS.readObject();
                            if (cancion.getArtista().equalsIgnoreCase(nombreBuscar)) {
                                System.out.println(cancion);
                                encontrado = true;
                            }
                        }

                        if (!encontrado) {
                            System.out.println("No se encontraron canciones del artista: " + nombreBuscar);
                        }

                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Error al leer el archivo: " + e.getMessage());
                    }

                    break;

                case 3:
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            } // Fin switch

        } while (opcion != 3); // Repetir hasta que el usuario elija salir


    }//Fin main

}//Fin class
