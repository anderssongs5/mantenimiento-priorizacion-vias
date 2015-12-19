package co.edu.udea.mantpriorivias.general;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang3.math.NumberUtils;

public class Util {

    public static boolean isNumerico(String numero) {
        if (numero != null) {
            numero = numero.replaceAll(",", ".");
        }

        return (NumberUtils.isNumber(numero));
    }

    public static List<String> tokenizar(String valores, String token) {
        List<String> valoresTokenizados = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(valores, token);

        while (stringTokenizer.hasMoreTokens()) {
            valoresTokenizados.add(stringTokenizer.nextToken());
        }

        return valoresTokenizados;
    }

    public static String getRutaTemporal() {
        String temporal = System.getProperty("java.io.tmpdir");
        String home = System.getProperty("user.home");
        File file = new File(temporal);

        return (file != null && file.canWrite()) ? temporal : home;
    }

    public static boolean isDirectorioValido(File archivo) {

        return (null != archivo && archivo.isDirectory() && archivo.canWrite());
    }
}
