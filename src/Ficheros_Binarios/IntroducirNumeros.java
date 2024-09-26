package Ficheros_Binarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*
 * DataInputStream y DataOutputStream
 * Introduce 10 numeros por teclado y los guarda en el fichero binerio
 */

public class IntroducirNumeros {
	public static void main(String[] args) throws IOException {
		File fichero = new
				File("C:\\Users\\aludam2\\eclipse-workspace\\Acceso_Datos\\src\\Ficheros_Binarios\\FicheroBinario_Numeros.dat"); //Declarar el fichero
		// Crea el flujo de salida hacia el fichero
		FileOutputStream fileout = new FileOutputStream(fichero);
		
		// Crea flujo de entrada
		FileInputStream filein = new FileInputStream(fichero);
		int i;
		
		Scanner teclado = new Scanner(System.in);
		
		// Te
		// Modificamos esta parte 
		// Un for para que introduzca 10 numeros	
		for (i=1; i<10; i++) {
			System.out.print("Introduce numero:  ");
			double num = teclado.nextDouble();
			
			fileout.write((int) num); //Escribe datos en el flujo de salida
		}//Fin for
		
		
		/*
		// Modificamos esta parte 
		// Un for para que introduzca 10 numeros
		 * No se puede hacer porq solo almacena en numeros ENTEROS
		for (i=1; i<10; i++) {
			System.out.print("Introduce numero:  ");
			double num = teclado.nextFloat();
			
			fileout.write((int) num); //Escribe datos en el flujo de salida
		}//Fin for
		*/
		
		fileout.close(); //Cerrar stream de salida
		
		// Visualizamos los datos del fichero
		while((i=filein.read()) != -1) { //Lee los datos del flujo de entrada
			System.out.println(i);
		}//Fin while
		 
		filein.close(); //Cerramos el stream de entrada
			
	}//Fin main
}
