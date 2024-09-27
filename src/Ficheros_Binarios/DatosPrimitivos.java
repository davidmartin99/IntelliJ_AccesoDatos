package Ficheros_Binarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DatosPrimitivos {

	public static void main(String[] args) throws IOException {
		// Declarar el archivo
		File f2 = new File("src\\Ficheros_Binarios\\primitivos.dat");
		f2.createNewFile();

		// Flujo de salida
		FileOutputStream fileout = new FileOutputStream(f2);
		DataOutputStream dataOS = new DataOutputStream(fileout);

		// Valores a escribir
		int v = 1;
		char c = 'A';  // Cambié el valor a un carácter real
		boolean b = true;  // Cambié a tipo primitivo
		String n = "hola";
		byte by = 0;

		// Escribir los datos en el archivo
		dataOS.writeByte(by);
		dataOS.writeChar(c);
		dataOS.writeBoolean(b);
		dataOS.writeInt(v);
		dataOS.writeUTF(n);

		// Cerrar el flujo de salida
		dataOS.close();

		// Flujo de entrada para leer el archivo
		FileInputStream filein = new FileInputStream(f2);
		DataInputStream dataIS = new DataInputStream(filein);

		try {
			// Leer los datos en el mismo orden en que fueron escritos
			while (dataIS.available() > 0 ) {
				by = dataIS.readByte();
				System.out.println("Esto es un Byte: " + by);
				c = dataIS.readChar();
				System.out.println("Esto es un Caracter: " + c);
				b = dataIS.readBoolean();
				System.out.println("Esto es un Boolean: " + b);
				v = dataIS.readInt();
				System.out.println("Esto es un entero: " + v);
				n = dataIS.readUTF();
				System.out.println("Esto es un String: " + n);
			}
		} catch (EOFException eof) {
			// Fin del archivo alcanzado
			System.out.println("Fin de la lectura del fichero.");
		} finally {
			// Cerrar el flujo de entrada
			dataIS.close();
		}
	} // Fin main

} // Fin class
