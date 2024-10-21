package Ficheros_Binarios;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

// La clase MiObjectOutputStream extiende ObjectOutputStream para cambiar su comportamiento.
public class MiObjectOutputStream extends ObjectOutputStream {

    // Constructor que recibe un OutputStream como parámetro
    // y lo pasa al constructor de la superclase ObjectOutputStream.
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);  // Llama al constructor de la superclase ObjectOutputStream con el OutputStream.
    }

    // Constructor protegido sin parámetros que llama al constructor por defecto de ObjectOutputStream.
    // No es necesario que lo uses directamente, pero está aquí para mantener la estructura de la clase.
    protected MiObjectOutputStream() throws IOException, SecurityException {
        super();  // Llama al constructor por defecto de ObjectOutputStream.
    }

    // Sobrescribe el método writeStreamHeader para que no haga nada.
    // Normalmente, ObjectOutputStream escribe un encabezado al inicio del stream.
    // Al sobrescribir este método, se evita que el encabezado se escriba de nuevo
    // cuando añadimos más objetos a un archivo que ya tiene datos.
    protected void writeStreamHeader() throws IOException {
        // No hace nada, evitando que se escriba un nuevo encabezado.
    }

} // Fin de la clase
