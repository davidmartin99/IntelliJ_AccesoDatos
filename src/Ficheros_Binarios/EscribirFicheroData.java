package Ficheros_Binarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirFicheroData {
	public static void main(String[] args) throws IOException {
		File fichero = new
				File("C:\\Users\\aludam2\\eclipse-workspace\\Acceso_Datos\\src\\Ficheros_Binarios\\DataFichero.dat"); //Declarar el fichero
		// Crea el flujo de salida hacia el fichero
		FileOutputStream fileout = new FileOutputStream(fichero);
		DataOutputStream dataOS = new DataOutputStream(fileout);

		//Nombres
		String nombres[] =
			{"Ana", "Miguel", "Alicia", "Pedro", "Manuel",
					"Maria", "David", "Clara", "Eva", "Alex"};
		
		//Edades
		int edades[] = {12,19,13,34,18,23,20,28,25,24};
		
		//Alturas
		double alturas[] = 
			{178.3, 182.3, 168.4, 198.3, 167.3, 192.4, 149.3, 169.0, 183.5, 176.9};
		
		// Introducimos los datos
		for (int i=0; i<edades.length; i++) {
			dataOS.writeUTF(nombres[i]);
			dataOS.writeInt(edades[i]);
			dataOS.writeDouble(alturas[i]);
		}//Fin for
		
		dataOS.close(); //Cerrar Stream
		
		// Ahora vamos a leer el contenido del fichero y mostrarlo por consola
        FileInputStream filein = new FileInputStream(fichero);
        DataInputStream dataIS = new DataInputStream(filein);

        // Leer y mostrar los datos
        System.out.println("Leyendo el contenido del fichero:");
        try {
            while (true) {  // Se utiliza un bucle infinito que se rompe con EOFException
                String nombre = dataIS.readUTF();
                int edad = dataIS.readInt();
                double altura = dataIS.readDouble();
                
                // Mostramos los datos en la consola
                System.out.println("Nombre: " + nombre + ", Edad: " + edad + ", Altura: " + altura);
            }
        } catch (IOException e) {
            // Se lanzará una excepción al final de fichero, lo que es normal
            System.out.println("Fin de la lectura del fichero.");
        }

        dataIS.close(); // Cerrar el flujo de entrada
		
	}//Fin main

}//Fin class
