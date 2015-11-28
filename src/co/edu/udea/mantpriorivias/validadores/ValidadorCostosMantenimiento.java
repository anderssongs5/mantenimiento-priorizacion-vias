package co.edu.udea.mantpriorivias.validadores;

import co.edu.udea.mantpriorivias.entidades.InfoItem;
import co.edu.udea.mantpriorivias.general.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ValidadorCostosMantenimiento {

    private final List<String> posiblesValoresUnidad;
    private final List<String> codigosItems;

    public ValidadorCostosMantenimiento() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream;
        inputStream = getClass().getResourceAsStream(
                "/co/edu/udea/mantpriorivias/validadores/"
                + "recursos/valores-costos-mantenimiento.properties");
        properties.load(inputStream);
        inputStream.close();

        this.posiblesValoresUnidad = Util.tokenizar(properties.getProperty(
                "POSIBLES_VALORES_UNIDADES"), ",");
        this.codigosItems = Util.tokenizar(properties.getProperty(
                "CODIGOS_ITEMS"), ",");
    }

//    public String validarItem(Item item) {
//        String separadorLinea = System.getProperty("line.separator");
//        String errores = "";
//        if (null == item) {
//
//            return "El ítem no contiene ningún dato.";
//        }
//
//        if (null == item.getCodigo() || item.getCodigo().trim().isEmpty()) {
//            errores += "     * El código del ítem es vacío." + separadorLinea;
//        }
//
//        if (null == item.getItem() || item.getItem().trim().isEmpty()) {
//            errores += "     * El nombre del ítem es vacío." + separadorLinea;
//        }
//
//        if (item.getUnidad() != null && !item.getUnidad().trim().isEmpty()) {
//            if (!this.posiblesValoresUnidad.contains(item.getUnidad().trim().toUpperCase())) {
//                errores += "     * La unidad no contiene un valor válido." + separadorLinea;
//            }
//        }
//
//        double valor = 0.0;
//        if (item.getValorUnitarioString() != null
//                && !item.getValorUnitarioString().trim().isEmpty()) {
//            if (!Util.isNumerico(item.getValorUnitarioString().trim())) {
//                errores += "     * El valor unitario no es un número." + separadorLinea;
//            } else {
//                valor = Double.parseDouble(item.getValorUnitarioString().trim());
//                if (valor <= 0) {
//                    errores += "     * El valor unitario no puede ser menor o "
//                            + "igual a cero." + separadorLinea;
//                }
//            }
//        }
//
//        if (errores.isEmpty()) {
//            if ((null != item.getUnidad() && !item.getUnidad().trim().isEmpty()
//                    && (null == item.getValorUnitarioString()
//                    || item.getValorUnitarioString().trim().isEmpty()))
//                    || (null != item.getValorUnitarioString()
//                    && !item.getValorUnitarioString().isEmpty()
//                    && (null == item.getUnidad() || item.getUnidad().isEmpty()))) {
//                errores += "     * La unidad o el valor unitario son vacíos. Tener"
//                        + " presente que si se agrega uno de ellos, el otro valor  "
//                        + "también debe ser agregado." + separadorLinea;
//            } else {
//                item.setValorUnitarioString(item.getValorUnitarioString().trim());
//                item.setValorUnitario(valor);
//            }
//        }
//
//        return errores;
//    }

    public boolean contieneTodosLosItems(List<InfoItem> items) {
        List<String> codigosItemsTemp = this.codigosItems;
        if (items == null || items.isEmpty() || items.size()
                != this.codigosItems.size()) {

            return false;
        }

        for (InfoItem i : items) {
            if (null != i && i.getItem() != null && i.getItem().getItem() != null
                    && !i.getItem().getItem().isEmpty()
                    && codigosItemsTemp.contains(i.getItem().getCodigo().trim())) {
                codigosItemsTemp.remove(i.getItem().getCodigo().trim());
            } else {

                return false;
            }
        }

        return codigosItemsTemp.isEmpty();
    }
}
