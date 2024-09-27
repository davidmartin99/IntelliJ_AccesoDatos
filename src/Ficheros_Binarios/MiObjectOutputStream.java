package Ficheros_Binarios;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream
{
    public MiObjectOutputStream(OutputStream out) throws IOException
        {  super(out);  }
        protected MiObjectOutputStream()
            throws IOException, SecurityException
        { super();}
        //Redefinició del método de escribir la cabecera
        // para que no haga nada
        protected void writeStreamHeader() throws IOException
        { }


}//Fin class
