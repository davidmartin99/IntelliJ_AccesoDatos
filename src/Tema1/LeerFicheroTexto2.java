package Tema1;
import java.io.*;

public class LeerFicheroTexto2 {
	public static void main(String[] args) throws IOException {
		//Declarar el fichero
		File fichero =
				new File("src\\Tema1\\LeerFicheroTexto.txt");
		
		//Crear el flujo de entrada hacia el fichero
		FileReader fic = new FileReader(fichero);
		//Para ir leyendo de 20 en 20 caracteres
				char b[] = new char[20];
				while((fic.read(b)) != -1 ) System.out.println(b);
		fic.close(); //Cerramos el fichero
		
	}//Fin main

}//Fin class