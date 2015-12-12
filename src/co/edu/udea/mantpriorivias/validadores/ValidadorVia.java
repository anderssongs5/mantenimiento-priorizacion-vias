package co.edu.udea.mantpriorivias.validadores;

import co.edu.udea.mantpriorivias.constantes.Constantes;
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
    private final List<String> valoresPosiblesPersonasTransportadasDia;
    private final List<String> valoresPosiblesUrci;
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
        this.valoresPosiblesPersonasTransportadasDia = Util.tokenizar(properties.getProperty(
                "PERSONAS_TRANSPORTADAS_DIA"), ",");
        this.valoresPosiblesUrci = Util.tokenizar(properties.getProperty(
                "URCI"), ",");
        this.valoresPosiblesDaniosGeneral = Util.tokenizar(properties.getProperty(
                "DANIOS_VIAS_81-82-83-84-85-86-88-90-91"), ",");
        this.valoresPosiblesCabezasDuras = Util.tokenizar(properties.
                getProperty("DANIOS_VIAS_89"), ",");
    }

    public static synchronized ValidadorVia getInstance() throws IOException {
        if (instance == null) {
            instance = new ValidadorVia();
        }
        return instance;
    }

    public String validarInformacion(Via via,
            List<String> daniosSeleccionados) {
        String separadorLinea = System.getProperty("line.separator");
        if (null == via) {

            return "La vía no contiene ningún dato.";
        }

        String mensajesErrorVia = "";
        if (null == via.getCodigoVia() || via.getCodigoVia().isEmpty()) {
            mensajesErrorVia += "     * La vía no tiene código." + separadorLinea;
        }

        if (null != via.getDesarrolloEconomico()) {
            if (null == via.getDesarrolloEconomico().getComercial()
                    || via.getDesarrolloEconomico().getComercial().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getComercial().trim())) {
                mensajesErrorVia += "     * Desarrollo Comercial es vacío"
                        + " o no tiene un valor válido." + separadorLinea;
            }

            if (null == via.getDesarrolloEconomico().getIndustrial()
                    || via.getDesarrolloEconomico().getIndustrial().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getIndustrial().trim())) {
                mensajesErrorVia += "     * Desarrollo Industrial es vacío "
                        + "o no tiene un valor válido." + separadorLinea;
            }

            if (null == via.getDesarrolloEconomico().getServicios()
                    || via.getDesarrolloEconomico().getServicios().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getServicios().trim())) {
                mensajesErrorVia += "     * Desarrollo Servicios es vacío "
                        + "o no tiene un valor válido." + separadorLinea;
            }

            if (null == via.getDesarrolloEconomico().getAgropecuario()
                    || via.getDesarrolloEconomico().getAgropecuario().trim().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getAgropecuario().trim())) {
                mensajesErrorVia += "     * Desarrollo Agropecuario es vacío "
                        + "o no tiene un valor válido." + separadorLinea;
            }

            if (null == via.getDesarrolloEconomico().getMinero()
                    || via.getDesarrolloEconomico().getMinero().isEmpty()
                    || !this.valoresPosiblesDesarrolloEconomico.contains(via.
                            getDesarrolloEconomico().getMinero().trim())) {
                mensajesErrorVia += "     * Desarrollo Minero es vacío "
                        + "o no tiene un valor válido." + separadorLinea;
            }
        } else {
            mensajesErrorVia += "     * No existe ningún valor para desarrollo "
                    + "económico" + separadorLinea;
        }

        if (null == via.getConectividad() || via.getConectividad().trim().isEmpty()
                || !this.valoresPosiblesConectividad.contains(via.getConectividad().trim())) {
            mensajesErrorVia += "     * La conectividad está vacía o "
                    + "no tiene un valor válido." + separadorLinea;
        }

        if (null == via.getTpd() || via.getTpd().isEmpty()
                || !this.valoresPosiblesTPD.contains(via.getTpd().trim())) {
            mensajesErrorVia += "     * El TPD es vacío o no tiene un valor válido."
                    + separadorLinea;
        }

        if (null == via.getViasAlternas() || via.getViasAlternas().isEmpty()
                || !this.valoresPosiblesViasAlternas.contains(via.getViasAlternas().trim())) {
            mensajesErrorVia += "     * Vías Alternas es vacío o no tiene un valor "
                    + "válido." + separadorLinea;
        }

        if (null == via.getPersonasTransportadasDia() || via.
                getPersonasTransportadasDia().trim().isEmpty()
                || !this.valoresPosiblesPersonasTransportadasDia.contains(
                        via.getPersonasTransportadasDia().trim())) {
            mensajesErrorVia += "     * Personas transportadas por día es vacío "
                    + "o no tiene un valor válido." + separadorLinea;
        }

        if (null == via.getUrci() || via.getUrci().trim().isEmpty()) {
            if (!this.valoresPosiblesUrci.contains(via.getUrci())) {
                mensajesErrorVia += "     * URCI es vacío "
                        + "o no tiene un valor válido." + separadorLinea;
            }
        }

        if (null != via.getDanioVia()) {
            if (null != via.getDanioVia().getSeccionTransversalInapropiada81()
                    && !via.getDanioVia().getSeccionTransversalInapropiada81().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getSeccionTransversalInapropiada81())) {
                    mensajesErrorVia += "     * El daño Sección Transversal Inapropiada"
                            + " (81) no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("81" + via.getDanioVia().
                            getSeccionTransversalInapropiada81().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getDrenajeLongitudinalInadecuado82()
                    && !via.getDanioVia().getDrenajeLongitudinalInadecuado82().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getDrenajeLongitudinalInadecuado82())) {
                    mensajesErrorVia += "     * El daño Drenaje Longitudinal Inadecuado (82)"
                            + " no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("82" + via.getDanioVia().
                            getDrenajeLongitudinalInadecuado82().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getDrenajeTransversalInadecuado83()
                    && !via.getDanioVia().getDrenajeTransversalInadecuado83().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getDrenajeTransversalInadecuado83())) {
                    mensajesErrorVia += "     * El daño Drenaje Transversal Inadecuado (83)"
                            + " no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("83" + via.getDanioVia().
                            getDrenajeTransversalInadecuado83().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getCorrugaciones84()
                    && !via.getDanioVia().getCorrugaciones84().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getCorrugaciones84())) {
                    mensajesErrorVia += "     * El daño Corrugaciones (84) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("84" + via.getDanioVia().
                            getCorrugaciones84().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getPolvo85()
                    && !via.getDanioVia().getPolvo85().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getPolvo85())) {
                    mensajesErrorVia += "     * El daño Polvo (85) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("85" + via.getDanioVia().
                            getPolvo85().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getBachesHuecos86()
                    && !via.getDanioVia().getBachesHuecos86().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getBachesHuecos86())) {
                    mensajesErrorVia += "     * El daño Baches / Huecos (86) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("86" + via.getDanioVia().
                            getBachesHuecos86().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getAhuellamientoSurcos87()
                    && !via.getDanioVia().getAhuellamientoSurcos87().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getAhuellamientoSurcos87())) {
                    mensajesErrorVia += "     * El daño Ahuellamiento / Surcos (87) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("87" + via.getDanioVia().
                            getAhuellamientoSurcos87().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getAgregadoSuelto88()
                    && !via.getDanioVia().getAgregadoSuelto88().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getAgregadoSuelto88())) {
                    mensajesErrorVia += "     * El daño Agregado Suelto (88) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("88" + via.getDanioVia().
                            getAgregadoSuelto88().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getCabezasDuras89()
                    && !via.getDanioVia().getCabezasDuras89().trim().isEmpty()) {
                if (!this.valoresPosiblesCabezasDuras.contains(via.getDanioVia().
                        getCabezasDuras89())) {
                    mensajesErrorVia += "     * El daño Cabezas Duras (89) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("89" + via.getDanioVia().
                            getCabezasDuras89().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getProbabilidadDerrumbes90()
                    && !via.getDanioVia().getProbabilidadDerrumbes90().trim().isEmpty()) {
                if (!this.valoresPosiblesDaniosGeneral.contains(via.getDanioVia().
                        getProbabilidadDerrumbes90())) {
                    mensajesErrorVia += "     * El daño Probablidad de "
                            + "Derrumbes (90) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("90" + via.getDanioVia().
                            getProbabilidadDerrumbes90().trim(), daniosSeleccionados);
                }
            }

            if (null != via.getDanioVia().getDanioBanca91()
                    && !via.getDanioVia().getDanioBanca91().trim().isEmpty()) {
                if (!this.valoresPosiblesCabezasDuras.contains(via.getDanioVia().
                        getCabezasDuras89())) {
                    mensajesErrorVia += "     * Daños en la Banca (91) "
                            + "no tiene un valor valido." + separadorLinea;
                } else {
                    ValidadorVia.agregarDanio("91" + via.getDanioVia().
                            getDanioBanca91().trim(), daniosSeleccionados);
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

    public static double sumarValoresVia(InfoVia via) {
        double suma = 0.0;
        suma += Double.parseDouble(via.getVia().getDesarrolloEconomico().getAgropecuario());
        suma += Double.parseDouble(via.getVia().getDesarrolloEconomico().getComercial());
        suma += Double.parseDouble(via.getVia().getDesarrolloEconomico().getIndustrial());
        suma += Double.parseDouble(via.getVia().getDesarrolloEconomico().getMinero());
        suma += Double.parseDouble(via.getVia().getDesarrolloEconomico().getServicios());

        suma += Double.parseDouble(via.getVia().getConectividad());

        suma += Double.parseDouble(via.getVia().getTpd());

        suma += Constantes.VIAS_ALTERNAS_VALORES.get(via.getVia().getViasAlternas().trim());

        suma += Double.parseDouble(via.getVia().getPersonasTransportadasDia());

        suma += Double.parseDouble(via.getVia().getUrci());

        return suma;
    }

    public boolean isVacia(Via via) {

        return via == null || (via.getCodigoVia() == null && via.getConectividad() == null
                && via.getPersonasTransportadasDia() == null && via.getTpd() == null
                && via.getUrci() == null && via.getViasAlternas() == null
                && (via.getDanioVia() == null
                || (via.getDanioVia().getAhuellamientoSurcos87() == null
                && via.getDanioVia().getSeccionTransversalInapropiada81() == null
                && via.getDanioVia().getDrenajeLongitudinalInadecuado82() == null
                && via.getDanioVia().getDrenajeTransversalInadecuado83() == null
                && via.getDanioVia().getCorrugaciones84() == null
                && via.getDanioVia().getPolvo85() == null
                && via.getDanioVia().getBachesHuecos86() == null
                && via.getDanioVia().getAgregadoSuelto88() == null
                && (via.getDanioVia().getCabezasDuras89() == null
                || via.getDanioVia().getCabezasDuras89().equals("N"))
                && via.getDanioVia().getProbabilidadDerrumbes90() == null
                && via.getDanioVia().getDanioBanca91() == null))
                && (via.getDesarrolloEconomico() == null
                || (via.getDesarrolloEconomico().getAgropecuario() == null
                && via.getDesarrolloEconomico().getComercial() == null
                && via.getDesarrolloEconomico().getIndustrial() == null
                && via.getDesarrolloEconomico().getMinero() == null
                && via.getDesarrolloEconomico().getServicios() == null)));
    }
}
