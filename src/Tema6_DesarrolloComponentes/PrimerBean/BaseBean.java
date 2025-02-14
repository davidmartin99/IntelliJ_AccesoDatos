package Tema6_DesarrolloComponentes.PrimerBean;

import java.beans.*;
import java.io.Serializable;


/**
 * Clase BeanFuente: Un JavaBean que notifica cambios de propiedad (Propiedades Ligadas).
 */
class BeanFuente implements Serializable {
    private PropertyChangeSupport propertySupport;
    private String propiedad;

    /**
     * Constructor que inicializa PropertyChangeSupport.
     */
    public BeanFuente() {
        propertySupport = new PropertyChangeSupport(this);
    }

    /**
     * Agrega un oyente de cambios de propiedad.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    /**
     * Remueve un oyente de cambios de propiedad.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    /**
     * Establece la propiedad y notifica a los oyentes si hay cambios.
     */
    public void setPropiedad(String nuevaPropiedad) {
        String valorAntiguo = this.propiedad;
        this.propiedad = nuevaPropiedad;
        propertySupport.firePropertyChange("propiedad", valorAntiguo, nuevaPropiedad);
    }
}

////////////////////////////////////////////////////////////////////////////////////////
/**
 * Clase BeanReceptor: Un Bean que escucha cambios en una propiedad.
 */
class BeanReceptor implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Valor anterior: " + evt.getOldValue());
        System.out.println("Valor actual: " + evt.getNewValue());
    }
}

////////////////////////////////////////////////////////////////////////////////////////
/**
 * Clase BeanFuenteConVeto: Un JavaBean con una propiedad restringida.
 */
class BeanFuenteConVeto implements Serializable {
    private VetoableChangeSupport soporteVeto;
    private int edad;

    /**
     * Constructor que inicializa VetoableChangeSupport.
     */
    public BeanFuenteConVeto() {
        soporteVeto = new VetoableChangeSupport(this);
    }

    /**
     * Agrega un oyente de veto.
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        soporteVeto.addVetoableChangeListener(listener);
    }

    /**
     * Remueve un oyente de veto.
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        soporteVeto.removeVetoableChangeListener(listener);
    }

    /**
     * Establece la edad con validación de veto.
     */
    public void setEdad(int nuevaEdad) throws PropertyVetoException {
        int edadAntigua = this.edad;
        soporteVeto.fireVetoableChange("edad", edadAntigua, nuevaEdad);
        this.edad = nuevaEdad;
    }
}

////////////////////////////////////////////////////////////////////////////////////////
/**
 * Clase BeanReceptorVeto: Un Bean que impone restricciones en la propiedad "edad".
 */
class BeanReceptorVeto implements VetoableChangeListener {
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        int nuevaEdad = (int) evt.getNewValue();
        if (nuevaEdad < 18) {
            throw new PropertyVetoException("La edad no puede ser menor a 18", evt);
        }
    }
}