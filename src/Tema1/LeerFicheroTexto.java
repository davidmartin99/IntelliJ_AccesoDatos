package Tema1;
import java.io.*;

public class LeerFicheroTexto {
	public static void main(String[] args) throws IOException {
		//Declarar el fichero
		File fichero =
				new File("C:\\Users\\aludam2\\eclipse-workspace\\Acceso_Datos\\src\\Tema1\\LeerFicheroTexto.txt");
		
		//Crear el flujo de entrada hacie el fichero
		FileReader fic = new FileReader(fichero);
		int i;
		while((i=fic.read()) != -1) //se va leyendo un caracter
			System.out.println((char)i);
		

		//Para ir leyendo de 20 en 20 caracteres
		char b[] = new char[20];
		while((i=fic.read(b)) != -1 ) System.out.println(b);
		
	}//Fin main

}//Fin class
