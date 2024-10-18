package Ejemplo2_JAXB_XSD;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBElement;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

public class InsertarVenta {

    public static void insertarVenta(int numVenta, String nomCli, int uni, String fecha) {
        System.out.println("AÑADIR VENTA");
        try {
            // Creamos el contexto JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Deserializamos el XML
            JAXBElement<VentasType> jaxbElement = (JAXBElement<VentasType>) unmarshaller.unmarshal(new File("C:\\Users\\aludam2\\IdeaProjects\\Acceso_Datos\\src\\JAXB\\ventasarticulos.xml"));
            VentasType miVenta = jaxbElement.getValue();
            Ventas vent = miVenta.getVentas();
            ArrayList<Ventas.Venta> listaVentas = new ArrayList<>(vent.getVenta());

            // Comprobar si existe el número de venta
            boolean existe = false;
            for (Ventas.Venta ve : listaVentas) {
                if (ve.getNumventa().intValue() == numVenta) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                // Crear el objeto Venta
                Ventas.Venta nuevaVenta = new Ventas.Venta();
                nuevaVenta.setNombrecliente(nomCli);
                nuevaVenta.setFecha(fecha);
                nuevaVenta.setUnidades(uni);
                nuevaVenta.setNumventa(BigInteger.valueOf(numVenta));

                // Añadir la venta a la lista
                listaVentas.add(nuevaVenta);

                // Crear el Marshaller
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                // Volcar la lista al fichero XML
                marshaller.marshal(jaxbElement, new File("ventasarticulos.xml"));

                System.out.println("Venta añadida: " + numVenta);
            } else {
                System.out.println("El número de venta ya existe: " + numVenta);
            }
        } catch (JAXBException je) {
            System.out.println(je.getCause());
        }
    }
}
