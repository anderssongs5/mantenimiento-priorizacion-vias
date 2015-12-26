package co.edu.udea.mantpriorivias.negocio.validadores;

import co.edu.udea.mantpriorivias.general.Util;
import co.edu.udea.mantpriorivias.negocio.entidades.Presupuesto;
import java.util.List;

public class ValidadorPresupuesto {

    public Presupuesto validarInformacion(String presupuestoTotIni,
            String porcAdministracion, String porcImprevistos,
            String porcUtilidades, List<String> mensajesError) {
        Presupuesto presupuesto = null;

        if ((null == presupuestoTotIni || presupuestoTotIni.isEmpty())
                || !Util.isNumerico(presupuestoTotIni)) {
            mensajesError.add("El presupuesto total inicial no puede ser vacío.");
        }

        if (!(null == porcAdministracion || porcAdministracion.isEmpty())
                && !Util.isNumerico(porcAdministracion)) {
            mensajesError.add("El porcentaje de administración no es válido.");
        }

        if (!(null == porcImprevistos || porcImprevistos.isEmpty())
                && !Util.isNumerico(porcImprevistos)) {
            mensajesError.add("El porcentaje de imprevistos no es válido.");
        }

        if (!(null == porcUtilidades || porcUtilidades.isEmpty())
                && !Util.isNumerico(porcUtilidades)) {
            mensajesError.add("El porcentaje de utilidades no es válido.");
        }

        if (mensajesError.isEmpty()) {
            double presupuestoTotalInicial = Double.parseDouble(presupuestoTotIni);
            double porcentajeAdministracion = 0.0;
            double porcentajeImprevistos = 0.0;
            double porcentajeUtilidades = 0.0;

            if (!(null == porcAdministracion || porcAdministracion.isEmpty())) {
                porcentajeAdministracion = Double.parseDouble(porcAdministracion);
            }

            if (!(null == porcImprevistos || porcImprevistos.isEmpty())) {
                porcentajeImprevistos = Double.parseDouble(porcImprevistos);
            }

            if (!(null == porcUtilidades || porcUtilidades.isEmpty())) {
                porcentajeUtilidades = Double.parseDouble(porcUtilidades);
            }

            if (presupuestoTotalInicial <= 0.0) {
                mensajesError.add("El presupuesto total inicial debe ser "
                        + "mayor que cero.");
            }

            if (porcentajeAdministracion < 0.0) {
                mensajesError.add("El porcentaje de administración debe ser "
                        + "mayor o igual que cero.");
            }

            if (porcentajeImprevistos < 0.0) {
                mensajesError.add("El porcentaje de imprevistos debe ser "
                        + "mayor o igual que cero.");
            }

            if (porcentajeUtilidades < 0.0) {
                mensajesError.add("El porcentaje de utilidades debe ser "
                        + "mayor o igual que cero.");
            }

            if (porcentajeAdministracion >= 0.0 && porcentajeImprevistos >= 0.0
                    && porcentajeUtilidades >= 0.0) {
                if (porcentajeAdministracion + porcentajeImprevistos
                        + porcentajeUtilidades >= 1.0) {
                    mensajesError.add("El AIU no puede ser mayor o igual al 100%.");
                }
            }

            if (mensajesError.isEmpty()) {
                presupuesto = new Presupuesto(presupuestoTotalInicial,
                        porcentajeAdministracion, porcentajeImprevistos,
                        porcentajeUtilidades);
            }
        }

        return presupuesto;
    }
}
