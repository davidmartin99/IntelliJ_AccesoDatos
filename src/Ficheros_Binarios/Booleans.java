package Ficheros_Binarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Creamos un fichero con una lista de personas
 * añadiendo un boolean que especifique si esa persona va a asistir una reunión o no.
 */

public class Booleans {
	public static void main(String[] args) throws IOException {
		File fichero = new
				File("C:\\Users\\aludam2\\eclipse-workspace\\Acceso_Datos\\src\\Ficheros_Binarios\\BooleansFichero.dat"); //Declarar el fichero
		// Crea el flujo de salida hacia el fichero
		FileOutputStream fileout = new FileOutputStream(fichero);
		DataOutputStream dataOS = new DataOutputStream(fileout);

		//Nombres
		String nombres[] =
			{"Ana", "Miguel", "Alicia", "Pedro", "Manuel",
					"Maria", "David", "Clara", "Eva", "Alex"};
	
		// Introducimos si asisten a la reunión o no
        boolean asistencias[] = {true, false, true, true, false, true, false, true, false, true};
		
		// Introducimos los datos
		for (int i=0; i<nombres.length; i++) {
			dataOS.writeUTF(nombres[i]);
            dataOS.writeBoolean(asistencias[i]);  // Escribe la asistencia
		}//Fin for
		
		
		dataOS.close(); //Cerrar Stream
		
		
		// Ahora vamos a leer el contenido del fichero y mostrarlo por consola
        FileInputStream filein = new FileInputStream(fichero);
        DataInputStream dataIS = new DataInputStream(filein);

        // StringBuilder para acumular las listas
        StringBuilder asistentes = new StringBuilder("Listado de personas que asisten a la reunión:\n");
        StringBuilder noAsistentes = new StringBuilder("Listado de personas que NO asisten a la reunión:\n");

        try {
            // Leer los datos del fichero
            while (true) {  // Bucle para leer hasta el final del fichero
                String nombre = dataIS.readUTF();   // Leer el nombre
                boolean asistencia = dataIS.readBoolean();  // Leer el valor booleano

                // Si la persona asiste, añadimos su nombre al StringBuilder de asistentes
                if (asistencia) {
                    asistentes.append("- Nombre: ").append(nombre).append("\n");
                } else {
                    // Si no asiste, lo añadimos al StringBuilder de no asistentes
                    noAsistentes.append("- Nombre: ").append(nombre).append("\n");
                }
            }
        } catch (IOException e) {
            // Se lanzará una excepción al final de fichero, lo que es normal
            System.out.println("Lectura del fichero finalizada.");
        } // Fin try-catch

        dataIS.close(); // Cerrar el flujo de entrada

        // Mostrar las listas
        System.out.println(asistentes.toString());
        System.out.println(noAsistentes.toString());
		
	}//Fin main
	
}//Fin class
