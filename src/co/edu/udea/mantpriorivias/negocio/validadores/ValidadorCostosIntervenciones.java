package co.edu.udea.mantpriorivias.negocio.validadores;

import co.edu.udea.mantpriorivias.general.Constantes;
import co.edu.udea.mantpriorivias.general.Util;
import co.edu.udea.mantpriorivias.negocio.entidades.Item;
import co.edu.udea.mantpriorivias.negocio.entidades.Unidad;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorCostosIntervenciones {

    public List<Item> obtenerCodigosMantenimientoUnicos(List<String> daniosSeleccionados) {
        List<Item> infoItems = new ArrayList<>();
        for (String ds : daniosSeleccionados) {
            String s = Constantes.ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.get(ds);
            if (!s.trim().isEmpty()) {
                List<String> valoresObtenidos = Util.tokenizar(s, ",");
                for (String vo : valoresObtenidos) {
                    infoItems.add(Constantes.ITEMS.get(vo));
                }
            }
        }

        infoItems = infoItems.stream().distinct().collect(
                Collectors.toList());

        return infoItems;
    }

    public String validarInformacionItems(List<Item> items, Object[][] datosItems) {
        String errores = "";
        String separadorLinea = Util.getSeparadorLinea();
        for (Object[] datosItem : datosItems) {
            String codigo = (String) datosItem[0];
            String item = (String) datosItem[1];
            Unidad unidad = (Unidad) datosItem[2];
            String valorUnitario = (String) datosItem[3];
            if (unidad == null
                    || !this.contieneUnidad(unidad)
                    || valorUnitario == null || valorUnitario.trim().isEmpty()
                    || valorUnitario.contains(".")
                    || !Util.isNumerico(valorUnitario)
                    || (Util.isNumerico(valorUnitario)
                    && Double.parseDouble(valorUnitario.replaceAll(",", ".")) < 0)) {
                if ((unidad == null
                        || !this.contieneUnidad(unidad))
                        && (valorUnitario == null || valorUnitario.trim().isEmpty()
                        || valorUnitario.contains(".")
                        || !Util.isNumerico(valorUnitario)
                        || (Util.isNumerico(valorUnitario)
                        && Double.parseDouble(valorUnitario) < 0))) {
                    if (valorUnitario != null && Util.isNumerico(valorUnitario)
                            && Double.parseDouble(valorUnitario.replaceAll(",", ".")) < 0) {
                        errores += "* " + codigo + ": no tiene unidad ni valor unitario, "
                                + "ninguno de los dos es válido, no tiene unidad "
                                + "válida o el valor no es válido o es menor que cero."
                                + separadorLinea;
                        continue;
                    }
                    errores += "* " + codigo + ": no tiene unidad ni valor unitario o "
                            + "ninguno de los dos es válido." + separadorLinea;
                    continue;
                }

                if (unidad == null
                        || !this.contieneUnidad(unidad)) {
                    errores += "* " + codigo + ": no tiene unidad o es no válido." + separadorLinea;
                    continue;
                }

                if (valorUnitario == null || valorUnitario.trim().isEmpty()
                        || valorUnitario.contains(".")
                        || !Util.isNumerico(valorUnitario)) {
                    errores += "* " + codigo + ": no tiene valor unitario o es no "
                            + "válido." + separadorLinea;
                } else if (!valorUnitario.contains(".") && Util.isNumerico(valorUnitario)
                        && Double.parseDouble(valorUnitario.replaceAll(",", ".")) < 0) {
                    errores += "* " + codigo + ": el valor unitario es menor "
                            + "que cero." + separadorLinea;
                }
            } else {
                items.add(new Item(codigo, item, unidad, valorUnitario));
            }
        }

        return errores;
    }

    private boolean contieneUnidad(Unidad unidad) {
        if (unidad == null) {

            return false;
        } else {
            if (Constantes.UNIDADES_POSIBLES.stream().anyMatch(u
                    -> (u.getUnidad().equals(unidad.getUnidad())))) {

                return true;
            }
        }

        return false;
    }
}
