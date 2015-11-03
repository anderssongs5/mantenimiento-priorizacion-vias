/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mantpriorivias.general;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Samsung
 */
public class Util {

    public static boolean isNumerico(String numero) {

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

        return System.getProperty("java.io.tmpdir");
    }
}
