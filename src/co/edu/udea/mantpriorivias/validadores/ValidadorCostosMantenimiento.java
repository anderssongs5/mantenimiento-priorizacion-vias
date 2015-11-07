package co.edu.udea.mantpriorivias.validadores;

import co.edu.udea.mantpriorivias.entidades.Item;
import co.edu.udea.mantpriorivias.general.Util;
import java.util.List;

public class ValidadorCostosMantenimiento {

    private List<String> posiblesValoresUnidad;

    public ValidadorCostosMantenimiento() {

    }

    public Item validarItem(String codigo, String nombreItem,
            String unidad, String valorUnitario, int numeroFila, List<String> errores) {
        Item item;
        if (null == codigo || codigo.trim().isEmpty()) {
            errores.add("El código es vacío.");
        }

        if (null == nombreItem || nombreItem.trim().isEmpty()) {
            errores.add("El nombre del ítem es vacío.");
        }

        if (unidad != null && !unidad.isEmpty()) {
            if (!posiblesValoresUnidad.contains(unidad)) {
                errores.add("La unidad no contiene un valor válido.");
            }
        }

        double valor = 0.0;
        if (valorUnitario != null && !valorUnitario.isEmpty()) {
            if (!Util.isNumerico(valorUnitario)) {
                errores.add("El valor unitario no es un número.");
            } else {
                valor = Double.parseDouble(valorUnitario);
                if (valor <= 0) {
                    errores.add("El valor unitario no puede ser menor o igual a cero.");
                }
            }
        }

        if (errores.isEmpty()) {
            if (null != unidad && !unidad.trim().isEmpty() && null != valorUnitario
                    && !valorUnitario.trim().isEmpty()) {
                item = new Item(codigo.trim(), nombreItem.trim(), unidad.trim(),
                valor);
            } else {
                errores.add("La unidad o el valor unitario son vacíos.");
            }
        }

        return null;
    }
}
