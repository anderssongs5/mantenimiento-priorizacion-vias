package co.edu.udea.mantpriorivias.negocio.validadores;

import co.edu.udea.mantpriorivias.dto.InfoVia;
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
        daniosSeleccionados.stream().map((ds)
                -> Constantes.ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.get(ds)).filter(
                        (s) -> (!s.trim().isEmpty())).map((s) -> Util.tokenizar(s, ",")).
                forEach((valoresObtenidos) -> {
                    valoresObtenidos.stream().map((vo) -> Constantes.ITEMS.get(vo)).
                    filter((item) -> (!infoItems.contains(item))).forEach((item) -> {
                        infoItems.add(item);
                    });
                });

        return infoItems;
    }

    public List<String> obtenerValoresUnicosDanios(List<InfoVia> vias) {
        List<String> daniosUnicos = new ArrayList<>();
        if (vias != null && !vias.isEmpty()) {
            vias.stream().forEach(via -> {
                via.getDaniosSeleccionados().stream().forEach(danio -> {
                    daniosUnicos.add(danio.trim());
                });
            });
        }

        return daniosUnicos.stream().distinct().collect(Collectors.toList());
    }

    public String validarInformacionItems(List<Item> items, Object[][] datosItems) {
        String errores = "";
        String separadorLinea = Util.getSeparadorLinea();
        boolean todosVacios = true;
        for (Object[] datosItem : datosItems) {
            String codigo = (String) datosItem[0];
            String item = (String) datosItem[1];
            Unidad unidad = (Unidad) datosItem[2];
            String valorUnitario = (String) datosItem[3];
            if ((unidad == null || Constantes.UNIDAD_VACIA.equals(unidad.getUnidad()))
                    && valorUnitario != null && !valorUnitario.trim().isEmpty()) {
                errores += "* " + codigo + ": no se seleccionó ninguna unidad y "
                        + "se ingresó un valor unitario." + separadorLinea;
                continue;
            }

            if (unidad != null && !Constantes.UNIDAD_VACIA.equals(unidad.getUnidad())
                    && (valorUnitario == null || valorUnitario.trim().isEmpty()
                    || !Util.isNumerico(valorUnitario) || (Util.isNumerico(valorUnitario)
                    && Double.parseDouble(valorUnitario.replace(",", ".")) <= 0))) {
                errores += "* " + codigo + ": se seleccionó una unidad y no se "
                        + "ingresó un valor unitario válido. Recuerde que el valor "
                        + "unitario debe ser mayor a cero." + separadorLinea;
                continue;
            }

            if (todosVacios && unidad != null && !Constantes.UNIDAD_VACIA.equals(
                    unidad.getUnidad()) && valorUnitario != null
                    && Util.isNumerico(valorUnitario)) {
                todosVacios = false;
            }

            if (unidad != null && Constantes.UNIDAD_VACIA.equals(unidad.getUnidad())) {
                unidad = null;
            }

            if (valorUnitario == null || valorUnitario.trim().isEmpty()) {
                valorUnitario = "0";
            }

            items.add(new Item(codigo, item, unidad, valorUnitario));
        }

        if (todosVacios) {
            errores += "Todos los ítems vacíos: por favor ingrese la información "
                    + "para al menos uno de los ítems o intervenciones de la "
                    + "lista." + separadorLinea;
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
