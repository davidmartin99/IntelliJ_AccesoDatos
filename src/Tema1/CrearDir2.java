package Tema1;
import java.io.*;

public class CrearDir2 {
    public static void main(String[] args) {
        File d = new File("NuevoDIR"); // Directorio que se va a crear
        File f1 = new File(d, "Fichero1.txt");
        File f2 = new File(d, "Fichero2.txt");

        // Crear el directorio
        if (!d.exists()) {
            if (d.mkdir()) {
                System.out.println("Directorio 'NuevoDIR' creado correctamente.");
            } else {
                System.out.println("No se pudo crear el directorio 'NuevoDIR'.");
                return; // Salimos si no podemos crear el directorio
            }
        } else {
            System.out.println("El directorio 'NuevoDIR' ya existe.");
        }

        try {
            // Crear Fichero1
            if (f1.createNewFile()) {
                System.out.println("Fichero1 creado correctamente...");
            } else {
                System.out.println("No se ha creado el Fichero1 o ya existía...");
            }

            // Crear Fichero2
            if (f2.createNewFile()) {
                System.out.println("Fichero2 creado correctamente...");
            } else {
                System.out.println("No se ha creado el Fichero2 o ya existía...");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Renombrar Fichero1
        File f1Nuevo = new File(d, "Fichero1Nuevo");
        if (f1.renameTo(f1Nuevo)) {
            System.out.println("Fichero1 renombrado a 'Fichero1Nuevo'.");
        } else {
            System.out.println("No se pudo renombrar el Fichero1.");
        }

        try {
            // Crear Fichero3 en NuevoDIR
            File f3 = new File(d, "Fichero3.txt");
            if (f3.createNewFile()) {
                System.out.println("Fichero3 creado correctamente...");
            } else {
                System.out.println("No se ha creado el Fichero3 o ya existía...");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Borrar Fichero2
        if (f2.delete()) {
            System.out.println("Fichero2 borrado correctamente...");
        } else {
            System.out.println("No se ha podido borrar el Fichero2...");
        }
    } // Fin main
} // Fin class
