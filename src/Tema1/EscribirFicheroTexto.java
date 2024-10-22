package Tema1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFicheroTexto {
	public static void main(String[] args) throws IOException {
		File fichero = new
				File("src\\Tema1\\EscribirFicheroTexto.txt");
		
		//Crear el flujo de salida
		FileWriter fic = new FileWriter(fichero);
		
		String cadena = "Esto es una prueba FileWriter";
		
		//Convierte la cadena en array de caracteres para extraerlos 1 a 1
		char[] cad = cadena.toCharArray();
		for(int i=0; i<cad.length; i++)
			fic.write(cad[i]);
		fic.append('*'); //se añade al final un *
		fic.close(); //cerrar el fichero
	}

}
