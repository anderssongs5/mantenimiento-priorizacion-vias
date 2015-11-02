package co.edu.udea.mantpriorivias.validadores;

import co.edu.udea.mantpriorivias.general.Util;
import co.edu.udea.mantpriorivias.entidades.Via;
import co.edu.udea.mantpriorivias.entidades.InfoVia;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ValidadorVia {

    private final List<String> valoresPosiblesDesarrolloEconomico;
    private final List<String> valoresPosiblesConectividad;
    private final List<String> valoresPosiblesTPD;
    private final List<String> valoresPosiblesViasAlternas;
    private final List<String> valoresPosiblesDaniosGeneral;
    private final List<String> valoresPosiblesCabezasDuras;

    private static ValidadorVia instance = null;

    private ValidadorVia() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream;
        inputStream = getClass().getResourceAsStream(
                "/co/edu/udea/mantpriorivias/validadores/"
                + "recursos/valores-posibles.properties");
        properties.load(inputStream);
        inputStream.close();

        this.valoresPosiblesDesarrolloEconomico = Util.tokenizar(properties.
                getProperty("DESARROLLO_ECONOMICO"), ",");
        this.valoresPosiblesConectividad = Util.tokenizar(properties.getProperty(
                "CONECTIVIDAD"), ",");
        this.valoresPosiblesTPD = Util.tokenizar(properties.getProperty("TPD"),
                ",");
        this.valoresPosiblesViasAlternas = Util.tokenizar(properties.getProperty(
                "VIAS_ALTERNAS"), ",");
        this.valoresPosiblesDaniosGeneral = Util.tokenizar(properties.getProperty(
                "DANIOS_VIAS_81-82-83-84-85-86-87"), ",");
        this.valoresPosiblesCabezasDuras = Util.tokenizar(properties.
                getProperty("DANIOS_VIAS_88"), ",");
    }

    public static synchronized ValidadorVia getInstance() throws IOException {
        if (instance == null) {
            instance = new ValidadorVia();
        }
        return instance;
    }

    public String validarInformacion(Via via,
            List<String> daniosSeleccionados) {
        if (null == via) {

            return "La vía no contiene ningún dato.";
        }

        String mensajesErrorVia = "";
        if (null == via.getCodigoVia() || via.getCodigoVia().isEmpty()) {
            mensajesErrorVia += "La vía no tiene código.";
        }

        if (null != via.getDesarrolloEconomico()) {
            if (null == via.getDesarrolloEconomico().getComercial()
                    || via.getDesarrolloEconomico().getComercial().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getComercial().trim())) {
                mensajesErrorVia += "Desarrollo Comercial es vacío"
                        + " o no tiene un valor válido.\n";
            }

            if (null == via.getDesarrolloEconomico().getIndustrial()
                    || via.getDesarrolloEconomico().getIndustrial().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getIndustrial().trim())) {
                mensajesErrorVia += "Desarrollo Industrial es vacío "
                        + "o no tiene un valor válido.\n";
            }

            if (null == via.getDesarrolloEconomico().getServicios()
                    || via.getDesarrolloEconomico().getServicios().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getServicios().trim())) {
                mensajesErrorVia += "Desarrollo Servicios es vacío "
                        + "o no tiene un valor válido.\n";
            }

            if (null == via.getDesarrolloEconomico().getAgropecuario()
                    || via.getDesarrolloEconomico().getAgropecuario().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getAgropecuario().trim())) {
                mensajesErrorVia += "Desarrollo Agropecuario es vacío "
                        + "o no tiene un valor válido.\n";
            }

            if (null == via.getDesarrolloEconomico().getMinero()
                    || via.getDesarrolloEconomico().getMinero().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getMinero().trim())) {
                mensajesErrorVia += "Desarrollo Minero es vacío "
                        + "o no tiene un valor válido.\n";
            }
        } else {
            mensajesErrorVia += "No existe ningún valor para desarrollo "
                    + "económico\n";
        }

        if (null == via.getConectividad() || via.getConectividad().trim().isEmpty()
                || !this.valoresPosiblesConectividad.contains(via.getConectividad().trim())) {
            mensajesErrorVia += "La conectividad está vacía o "
                    + "no tiene un valor válido.\n";
        }

        if (null == via.getTpd() || via.getTpd().isEmpty()
                || !this.valoresPosiblesTPD.contains(via.getTpd().trim())) {
            mensajesErrorVia += "El TPD es vacío o no tiene un valor válido.\n";
        }

        if (null == via.getViasAlternas() || via.getViasAlternas().isEmpty()
                || !this.valoresPosiblesViasAlternas.contains(via.getViasAlternas().trim())) {
            mensajesErrorVia += "Vías Alternas es vacío o no tiene un valor "
                    + "válido.\n";
        }

        if (null != via.getDanioVia()) {
            if (null != via.getDanioVia().getSeccionTransversalInapropiada81()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getSeccionTransversalInapropiada81())) {
                    mensajesErrorVia += "El daño Sección Transversal Inapropiada"
                            + " (81) no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("81", daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getDrenajeLateralInadecuado82()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getDrenajeLateralInadecuado82())) {
                    mensajesErrorVia += "El daño Drenaje Lateral Inadecuado (82)"
                            + " no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("82", daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getCorrugaciones83()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getCorrugaciones83())) {
                    mensajesErrorVia += "El daño Corrugaciones (83) "
                            + "no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("83", daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getPolvo84()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getPolvo84())) {
                    mensajesErrorVia += "El daño Polvo (84) "
                            + "no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("84", daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getBachesHuecos85()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getBachesHuecos85())) {
                    mensajesErrorVia += "El daño Baches / Huecos (85) "
                            + "no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("85", daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getAhuellamientoSurcos86()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getAhuellamientoSurcos86())) {
                    mensajesErrorVia += "El daño Ahuellamiento / Surcos (86) "
                            + "no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("86", daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getAgregadoSuelto87()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getAgregadoSuelto87())) {
                    mensajesErrorVia += "El daño Agregado Suelto (87) "
                            + "no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("87", daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getCabezasDuras88()) {
                if (!this.valoresPosiblesCabezasDuras.contains(via.getDanioVia().
                        getCabezasDuras88())) {
                    mensajesErrorVia += "El daño Cabezas Duras (88) "
                            + "no tiene un valor valido.\n";
                } else {
                    ValidadorVia.agregarDanio("88", daniosSeleccionados);
                }
            }
        }

        return mensajesErrorVia;
    }

    private static void agregarDanio(String danio, List<String> daniosSeleccionados) {
        if (!daniosSeleccionados.contains(danio)) {
            daniosSeleccionados.add(danio);
        }
    }

    public static boolean existenViasConCodigoRepetido(List<InfoVia> vias) {
        if (null == vias || vias.isEmpty() || vias.size() == 1) {

            return false;
        }

        for (int i = 0; i < vias.size() - 1; i++) {
            String codigoVia = vias.get(i).getVia().getCodigoVia();
            if (null == codigoVia) {
                continue;
            }
            for (int j = i + 1; j < vias.size(); j++) {
                String codVia = vias.get(j).getVia().getCodigoVia();
                if (codigoVia.equalsIgnoreCase(codVia)) {
                    return true;
                }
            }
        }

        return false;
    }
}
