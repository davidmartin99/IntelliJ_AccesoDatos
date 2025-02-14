package Tema6_DesarrolloComponentes.Herramientas_4;


/*
public class PruebaLibreriaJava {
    public static void main(String[] args) {
        MiClase miObjeto = new MiClase();
        miObjeto.metodoDePrueba();
    }
}
 */
class PruebaLibreriaJava {
    public static void main(String[] args) {
        Producto miObjeto = new Producto(
        "leche", 33 , 88, 1, 4 );
        System.out.println(miObjeto.getStockactual());
        miObjeto.getStockactual();
    }
}

