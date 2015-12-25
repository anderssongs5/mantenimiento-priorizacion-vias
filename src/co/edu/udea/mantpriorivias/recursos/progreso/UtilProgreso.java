package co.edu.udea.mantpriorivias.recursos.progreso;

import co.edu.udea.mantpriorivias.entidades.Progreso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilProgreso {

    private static final String NOMBRE_ARCHIVO_PROGRESO = "progreso.ser";
    private static final String EXTENSION_ARCHIVO_ser = ".ser";
    private static final String EXTENSION_ARCHIVO_SER = ".SER";

    public static boolean guardarProgreso(Progreso progreso, String ruta) {
        boolean respuesta = true;

        try {
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(ruta + "/"
                    + NOMBRE_ARCHIVO_PROGRESO);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(progreso);
            fileOutputStream.close();
        } catch (IOException ex) {
            respuesta = false;
        }

        return respuesta;
    }

    public static Progreso cargarProgreso(File file) {
        Progreso progreso = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            progreso = (Progreso) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilProgreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UtilProgreso.class.getName()).log(Level.SEVERE, null, ex);
        }

        return progreso;
    }

    public static boolean validarArchivo(File archivo) {

        return (null != archivo && archivo.exists() && archivo.isFile()
                && archivo.canRead() && (archivo.getName().endsWith(
                        EXTENSION_ARCHIVO_ser) || archivo.getName().endsWith(
                        EXTENSION_ARCHIVO_SER)));
    }
}
