package Ficheros_Binarios;

import java.io.*;
/*
    Vamos a excribir el objeto Persona en un fichero
 */

public class EscribirFicheroObjetos {
    public static void main(String[] args) throws IOException {
        Persona persona; //defino variable persona
        //Declaro el fichero
        File fichero = new File("src\\Ficheros_Binarios\\persona.dat");

        //Crea el flujo de salida
        FileOutputStream fileout = new FileOutputStream(fichero);
        //Conecta el flujo de bytes al flujo de datos
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        //Nombres
        String nombres[] =
                {"Ana", "Miguel", "Alicia", "Pedro", "Manuel",
                        "Maria", "David", "Clara", "Eva", "Alex"};
        //Edades
        int edades[] = {12,19,13,34,18,23,20,28,25,24};

        //Recorro las variables
        for (int i = 0; i<nombres.length; i++) {
            persona = new Persona(nombres[i], edades[i]);
            dataOS.writeObject(persona); //Escribo la persona en el fichero
        }//Fin for

        dataOS.close();
        fileout.close();

    }//Fin main

}//Fin class
