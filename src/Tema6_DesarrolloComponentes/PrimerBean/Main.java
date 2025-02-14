package Tema6_DesarrolloComponentes.PrimerBean;

import java.beans.*;

/**
 * Clase Main para probar los JavaBeans.
 */
public class Main {
    public static void main(String[] args) {
        // Ejemplo de propiedad ligada
        BeanFuente fuente = new BeanFuente();
        BeanReceptor receptor = new BeanReceptor();
        fuente.addPropertyChangeListener(receptor);
        fuente.setPropiedad("Nuevo Valor");

        // Ejemplo de propiedad restringida
        BeanFuenteConVeto fuenteVeto = new BeanFuenteConVeto();
        BeanReceptorVeto receptorVeto = new BeanReceptorVeto();
        fuenteVeto.addVetoableChangeListener(receptorVeto);
        try {
            fuenteVeto.setEdad(20); // Permitido
            fuenteVeto.setEdad(16); // Lanzará una excepción
        } catch (PropertyVetoException e) {
            System.out.println("Excepción atrapada: " + e.getMessage());
        }
    }
}