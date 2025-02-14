package Tema6_DesarrolloComponentes.Herramientas_4;

// Clase Pedido: Escucha cambios en el stock de un producto y genera pedidos si es necesario.
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Pedido implements PropertyChangeListener {

    // Método que se ejecuta cuando hay un cambio en el stock actual del producto
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("stockactual".equals(evt.getPropertyName())) {
            int nuevoStock = (int) evt.getNewValue();
            Producto producto = (Producto) evt.getSource();

            // Si el stock es inferior al mínimo, se genera un pedido
            if (nuevoStock < producto.getStockminimo()) {
                System.out.println("⚠️ Stock bajo para el producto: " + producto.getDescripcion());
                System.out.println("🛒 Generando un nuevo pedido...");
            }
        }
    }

    // Método para suscribirse a un producto
    public void suscribirAProducto(Producto producto) {
        producto.addPropertyChangeListener(this);
    }

    // Método para dejar de recibir notificaciones de un producto
    public void cancelarSuscripcion(Producto producto) {
        producto.removePropertyChangeListener(this);
    }
}