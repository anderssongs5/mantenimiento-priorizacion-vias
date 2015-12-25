package co.edu.udea.mantpriorivias.seguridad;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Seguridad {

    private static final String PASSWORD = "PASSWORD";

    public static boolean puedeContinuar(String texto) throws IOException {
        Properties properties = Seguridad.obtenerPropiedades();

        return texto != null && !texto.isEmpty()
                && texto.equals(properties.get(PASSWORD));
    }

    private static Properties obtenerPropiedades() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream;
        inputStream = Seguridad.class.getClassLoader().getResourceAsStream(
                "co/edu/udea/mantpriorivias/seguridad/"
                + "recursos/seguridad.properties");
        properties.load(inputStream);
        inputStream.close();

        return properties;
    }
}
