package Ficheros_Binarios;

import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;

public class LeerFicheroObjetos {
    public static void main(String[] args) throws IOException {
        Persona persona; //defino variable persona
        File fichero = new File("src\\Ficheros_Binarios\\persona.dat");

        //Crea el flujo de salida
        FileInputStream filein = new FileInputStream(fichero);
        //Conecta el flujo de bytes al flujo de datos
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        try{
            while(true){//lectura del fichero
                persona = (Persona) dataIS.readObject(); //Leer una Persona
                System.out.println(persona);
            }//Fin while
        }catch (EOFException e){
            System.out.println("Fin de lectura");
        }//Fin try-catch
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        dataIS.close();

    }//Fin main

}//Fin class
