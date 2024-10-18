package Ejemplo2_JAXB_XSD;

public class Main {

    public static void main(String[] args) {
        // Método para visualizar las ventas desde el XML
        visualizarVentas();

        // Método para insertar una nueva venta
        insertarVenta(1, "Juan Pérez", 3, "2024-10-18");
    }

    // Método para visualizar las ventas (deberías implementar este método)
    private static void visualizarVentas() {
        VisualizarXML.visualizar();

    }

    // Aquí puedes incluir el código de la clase InsertarVenta o hacer referencia a la clase
    private static void insertarVenta(int numVenta, String nomCli, int uni, String fecha) {
        InsertarVenta.insertarVenta(numVenta, nomCli, uni, fecha);
    }
}
