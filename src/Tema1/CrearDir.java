package Tema1;
import java.io.*;

public class CrearDir {
	public static void main(String[] args) {
		File d = new File("NuevoDIR"); //directorio que creo
		File f1 = new File(d,"Fichero1.txt");
		File f2 = new File(d,"Fichero2.txt");

		d.mkdir(); //CREAMOS EL DIRECTORIO
		
		try {
			if(f1.createNewFile())
				System.out.println("Fichero1 creado correctamente...");
			else
				System.out.println("No se ha creado el Fichero1...");
			
			if(f2.createNewFile())
				System.out.println("Fichero2 creado correctamente...");
			else
				System.out.println("No se ha creado el Fichero2...");
		} catch (IOException ioe) {ioe.printStackTrace();}
		
		f1.renameTo(new File(d,"Fichero1Nuevo")); //Renombro el Fichero1
		
		try {
			File f3 = new File("NuevoDIR/Fichero3.txt");
			f3.createNewFile(); //Crea Fichero3 en NuevoDIR
		} catch (IOException ioe) {ioe.printStackTrace();}
		
		//Borrar un fichero
		if(f2.delete()) {
			System.out.println("Fichero borrado...");
		}else {
			System.out.println("No se ha podido borrar el fichero..");
		}
	}//Fin main

}//Fin class
