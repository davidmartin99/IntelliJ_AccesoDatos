package Tema6_DesarrolloComponentes.Herramientas_4;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

// Clase Producto: Representa un producto con stock y notifica cambios en el stock actual.
public class Producto implements Serializable {
    private String descripcion;
    private int idproducto;
    private int stockactual;
    private int stockminimo;
    private float pvp;

    // Soporte para propiedad ligada (PropertyChangeSupport)
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor
    public Producto(String descripcion, int idproducto, int stockactual, int stockminimo, float pvp) {
        this.descripcion = descripcion;
        this.idproducto = idproducto;
        this.stockactual = stockactual;
        this.stockminimo = stockminimo;
        this.pvp = pvp;
    }

    // Métodos getter y setter con notificación de cambios
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getStockactual() {
        return stockactual;
    }

    public void setStockactual(int nuevoStock) {
        int stockAnterior = this.stockactual;
        this.stockactual = nuevoStock;
        // Notificar a los oyentes si el stock cambia
        support.firePropertyChange("stockactual", stockAnterior, nuevoStock);
    }

    public int getStockminimo() {
        return stockminimo;
    }

    public void setStockminimo(int stockminimo) {
        this.stockminimo = stockminimo;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    // Métodos para añadir y eliminar oyentes de cambios en la propiedad stockactual
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
