package co.edu.udea.mantpriorivias.negocio;

import co.edu.udea.mantpriorivias.dto.InfoVia;
import co.edu.udea.mantpriorivias.dto.MantPriorViasInfo;

public class PMVias {

    public static String estructurarInformacionErrorPlantilla(MantPriorViasInfo mantPriorViasInfo) {
        String informacion = "";
        String separadorLinea = System.getProperty("line.separator");
        if (mantPriorViasInfo.getErroresArchivo() != null
                && !mantPriorViasInfo.getErroresArchivo().isEmpty()) {
            informacion += "Errores del archivo:" + separadorLinea;
            informacion = mantPriorViasInfo.getErroresArchivo().stream().map((s)
                    -> "    * " + s.trim() + separadorLinea).reduce(informacion,
                            String::concat);
        }

        if (!informacion.isEmpty()) {
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "====================================================";
            informacion += separadorLinea;
            informacion += separadorLinea;
        }

        if (mantPriorViasInfo.getErroresHojaPresupuesto() != null
                && !mantPriorViasInfo.getErroresHojaPresupuesto().isEmpty()) {
            informacion += "Errores en hoja Presupuesto:" + separadorLinea;
            informacion = mantPriorViasInfo.getErroresHojaPresupuesto().stream()
                    .map((s) -> "    * " + s.trim() + separadorLinea).reduce(
                            informacion, String::concat);
        }

        if (!informacion.isEmpty()) {
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "====================================================";
            informacion += separadorLinea;
            informacion += separadorLinea;
        }

        boolean erroresVias = false;
        if (mantPriorViasInfo.existenViasConErrores()) {
            informacion += "Errores en hoja Priorización o errores en vías:";
            informacion += separadorLinea;
            informacion += separadorLinea;
            for (InfoVia iv : mantPriorViasInfo.getVias()) {
                if (iv.getErroresVia() != null && !iv.getErroresVia().isEmpty()) {
                    informacion += "Número de fila: " + iv.getFilaVia() + separadorLinea;
                    String codVia = (iv.getVia().getCodigoVia() != null)
                            ? iv.getVia().getCodigoVia() : "";
                    informacion += "Código de la vía: " + codVia + separadorLinea;
                    informacion += "Errores: " + separadorLinea + iv.getErroresVia();
                    informacion += separadorLinea;
                    informacion += separadorLinea;
                }
            }
            erroresVias = true;
        } else if (mantPriorViasInfo.getVias().isEmpty()) {
            informacion += "Errores en hoja Priorización o errores en vías:";
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "    * No existe ninguna vía.";
            erroresVias = true;
        }

        if (mantPriorViasInfo.getErroresHojaPriorizacion() != null
                && !mantPriorViasInfo.getErroresHojaPriorizacion().isEmpty()) {
            if (!erroresVias) {
                informacion += "Errores en hoja Priorización o errores en vías:";
                informacion += separadorLinea;
                informacion += separadorLinea;
            }
            informacion += mantPriorViasInfo.getErroresHojaPriorizacion().trim()
                    + separadorLinea;
        }

        return informacion;
    }
}
