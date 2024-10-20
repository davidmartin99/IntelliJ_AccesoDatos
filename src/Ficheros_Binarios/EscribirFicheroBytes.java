package Ficheros_Binarios;

import java.io.*;
import java.io.IOException;

/*
 * Escribe los numeros del 1 al 100 en un fichero binario
 * 
 */
public class EscribirFicheroBytes {
	public static void main(String[] args) throws IOException {
		File fichero = new
				File("src\\Ficheros_Binarios\\FicheroBinario1.dat"); //Declarar el fichero
		// Crea el flujo de salida hacia el fichero
		FileOutputStream fileout = new FileOutputStream(fichero);
		
		// Crea flujo de entrada
		FileInputStream filein = new FileInputStream(fichero);
		int i;
		
		for (i=1; i<100; i++) {
			fileout.write(i); //Escribe datos en el flujo de salida
		}//Fin for
		
		fileout.close(); //Cerrar stream de salida
		
		// Visualizamos los datos del fichero
		while((i=filein.read()) != -1) { //Lee los datos del flujo de entrada
			System.out.println(i);
		}//Fin while
		 
		filein.close(); //Cerramos el stream de entrada
			
	}//Fin main

}//Fin class
