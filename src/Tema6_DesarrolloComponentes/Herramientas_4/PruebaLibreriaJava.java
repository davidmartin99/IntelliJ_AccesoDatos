package Tema6_DesarrolloComponentes.Herramientas_4;


import MisBeans.Pedido;
import MisBeans.Producto;

/**
 * pag. 17
 */
public class PruebaLibreriaJava {
    public static void main(String[] args) {
        // Crear un objeto Producto con stock inicial 10 y mínimo 3
        Producto producto = new Producto(11, "Dabber Fur Femme 2011", 10, 3, 16);

        // Crear un objeto Pedido
        Pedido pedido = new Pedido();

        // Asociar el pedido al producto
        pedido.setProducto(producto);

        // Agregar el pedido como listener de cambios en el stock del producto
        producto.addPropertyChangeListener(pedido);

        // Cambiar el stock actual a 2, lo que disparará el evento
        producto.setStockactual(2);
    }
}

