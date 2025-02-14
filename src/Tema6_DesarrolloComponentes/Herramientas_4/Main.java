package Tema6_DesarrolloComponentes.Herramientas_4;

// Clase Main: Prueba el funcionamiento del sistema
public class Main {
    public static void main(String[] args) {
        // Crear un producto con stock inicial
        Producto producto = new Producto("Laptop", 101, 50, 30, 1200.0f);

        // Crear un pedido y suscribirse a cambios en el stock
        Pedido pedido = new Pedido();
        pedido.suscribirAProducto(producto);

        // Simular cambios en el stock
        System.out.println("Reduciendo stock...");
        producto.setStockactual(40);
        producto.setStockactual(25); // Aquí se generará un pedido porque es menor al stock mínimo
    }
}
