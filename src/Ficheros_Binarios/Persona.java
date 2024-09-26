package Ficheros_Binarios;

import java.io.Serializable;

public class Persona implements Serializable {
        private String nombre;
        private int edad;

        //Constructores
        public Persona(String nombre, int edad){
            this.nombre = nombre;
            this.edad = edad;
        }
        public Persona(){
            this.nombre = null;
            this.edad = 0;
        }

        public void setNombre(String nombre) {this.nombre = nombre;}
        public void setEdad(int Edad) {this.edad = edad;}

        public String getNombre() {return this.nombre;} //devuelve nombre
        public int getEdad() {return this.edad;} //devuelve edad


        @Override
        public String toString() {
            return "Persona { " +
                    "Nombre = '" + nombre + '\'' +
                    ", Edad = " + edad +
                    " }";
        }

}//Fin class
