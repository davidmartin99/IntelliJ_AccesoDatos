package Tema1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Realizar un programa Java que lea un fichero de texto y 
 * compruebe que no sobrepase los 1.000 caracteres(los blancos son caracteres),
 * ni 150 palabras(palabra = mas de 1 caracter).
 * 
 */

public class Ejercicio1_ContarCaracteres {

    public static void main(String[] args) {
        String archivo = "src\\Tema1/Texto_Ejercicio1.txt"; // Reemplazar por la ruta del fichero
        int maxCaracteres = 1000;
        int maxPalabras = 150;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            int contadorCaracteres = 0;
            int contadorPalabras = 0;

            while ((linea = lector.readLine()) != null) {
                // Contar caracteres
                contadorCaracteres += linea.length();

                // Contar palabras (una palabra tiene más de un caracter)
                String[] palabras = linea.trim().split("\\s+");
                for (String palabra : palabras) {
                    if (palabra.length() > 1) {
                        contadorPalabras++;
                    }
                }//Fin for

                // Verificar si se superan los límites
                if (contadorCaracteres > maxCaracteres) {
                    System.out.println("El archivo supera el límite de 1000 caracteres.");
                    break;
                }
                if (contadorPalabras > maxPalabras) {
                    System.out.println("El archivo supera el límite de 150 palabras.");
                    break;
                }
            }//Fin while

            lector.close();

            // Mostrar resultados si no se ha sobrepasado ningún límite
            if (contadorCaracteres <= maxCaracteres && contadorPalabras <= maxPalabras) {
                System.out.println("El archivo tiene " + contadorCaracteres + " caracteres y " + contadorPalabras + " palabras.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
    }//Fin main
    
}//Fin class
