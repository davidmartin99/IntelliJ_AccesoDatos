package Tema1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFicheroTexto2 {
	public static void main(String[] args) throws IOException {
		File fichero = new
				File("src\\Tema1\\EscribirFicheroTexto.txt");
		
		//Crear el flujo de salida
		FileWriter fic = new FileWriter(fichero);
		
		String prov[] = 
			{"Albacete", "Avila", "Badajoz", "Huelva", "Jaen", "Madrid",
					"Segovia", "Toledo", "Zamora"};
	
		// Escribimos las provincias en orden inverso (de la última a la primera)
		for (int i = prov.length - 1; i >= 0; i--) {
            fic.write(prov[i] + " "); // Se escriben las provincias con un espacio entre ellas
        }
		
		fic.close(); //cerrar el fichero
	}

}
