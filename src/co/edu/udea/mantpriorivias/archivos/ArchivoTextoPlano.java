package co.edu.udea.mantpriorivias.archivos;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchivoTextoPlano {

    public boolean crearArchivo(String contenido, String fullPath) {
        PrintWriter writer = null;
        boolean correcto = true;
        try {
            writer = new PrintWriter(fullPath, "UTF-8");
            writer.println(contenido);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(ArchivoTextoPlano.class.getName()).
                    log(Level.SEVERE, null, ex);
            correcto = false;
        } finally {
            writer.close();
        }

        return correcto;
    }
}
