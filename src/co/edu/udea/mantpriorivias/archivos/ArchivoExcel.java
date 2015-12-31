package co.edu.udea.mantpriorivias.archivos;

import co.edu.udea.mantpriorivias.dto.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.negocio.entidades.Presupuesto;
import co.edu.udea.mantpriorivias.negocio.entidades.Via;
import co.edu.udea.mantpriorivias.dto.InfoVia;
import co.edu.udea.mantpriorivias.negocio.validadores.ValidadorPresupuesto;
import co.edu.udea.mantpriorivias.negocio.validadores.ValidadorVia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ArchivoExcel {

    private final ValidadorPresupuesto validadorPresupuesto = new ValidadorPresupuesto();

    public static final String ARCHIVO_ALTERNATIVAS_INTERVENCION
            = "Alternativas_Intervencion.xlsx";

    private static final int NUMERO_COLUMNAS_PRIORIZACION = 22;
    private static final int CODIGO_VIA = 0;
    private static final int DE_COMERCIAL = 1;
    private static final int DE_INDUSTRIAL = 2;
    private static final int DE_SERVICIOS = 3;
    private static final int DE_AGROPECUARIO = 4;
    private static final int DE_MINERO = 5;
    private static final int CONECTIVIDAD = 6;
    private static final int TPD = 7;
    private static final int VIAS_ALTERNAS = 8;
    private static final int PERSONAS_TRANSPORTADAS_DIA = 9;
    private static final int URCI = 10;
    private static final int SECCION_TRANSVERSAL_INAPROPIADA_81 = 11;
    private static final int DRENAJE_LONGITUDINAL_INADECUADO_82 = 12;
    private static final int DRENAJE_TRANSVERSAL_INADECUADO_83 = 13;
    private static final int CORRUGACIONES_84 = 14;
    private static final int POLVO_85 = 15;
    private static final int BACHES_HUECOS_86 = 16;
    private static final int AHUELLAMIENTO_SURCOS_87 = 17;
    private static final int AGREGADO_SUELTO_88 = 18;
    private static final int CABEZAS_DURAS_89 = 19;
    private static final int PROBABILIDAD_DERRUMBES_90 = 20;
    private static final int DANIOS_BANCA_91 = 21;

    private static final String EXTENSION_ARCHIVO_xlsx = ".xlsx";
    private static final String EXTENSION_ARCHIVO_XLSX = ".XLSX";

    public MantPriorViasInfo leerPlantilla(File archivo)
            throws FileNotFoundException, IOException {
        MantPriorViasInfo mantPriorViasInfo = null;

        List<String> mensajesErrorArchivo = new ArrayList<>();
        List<String> mensajesErrorHojaPresupuesto = new ArrayList<>();
        String mensajesErrorHojaPriorizacion = "";
        String mensajesErrorHojaCostosMantenimiento = "";
        FileInputStream fileInputStream = new FileInputStream(archivo);
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet hojaPresupuesto = workbook.getSheet("Presupuesto");
        Presupuesto presupuesto = null;
        if (null == hojaPresupuesto) {
            mensajesErrorArchivo.add("No existe la hoja Presupuesto.");
        } else {
            presupuesto = this.obtenerInfoHojaPresupuesto(
                    hojaPresupuesto, mensajesErrorHojaPresupuesto);
        }

        Sheet hojaPriorizacion = workbook.getSheet("Priorización");
        List<InfoVia> vias = new ArrayList<>();
        if (null == hojaPriorizacion) {
            mensajesErrorArchivo.add("No existe la hoja Priorización.");
        } else {
            vias = this.obtenerInfoHojaPriorizacion(hojaPriorizacion);
        }

        if (!vias.isEmpty()) {
            if (ValidadorVia.existenViasConCodigoRepetido(vias)) {
                mensajesErrorHojaPriorizacion += "*** Existen vías con código "
                        + "igual. Por favor corrija la información ya que "
                        + "cada código de vía debe ser único. ***";
            }
        }

        mantPriorViasInfo = new MantPriorViasInfo(mensajesErrorArchivo,
                mensajesErrorHojaPresupuesto,
                mensajesErrorHojaPriorizacion, presupuesto, vias);

        return mantPriorViasInfo;
    }

    private Presupuesto obtenerInfoHojaPresupuesto(Sheet hojaPresupuesto,
            List<String> mensajesError) {
        Presupuesto presupuesto = null;
        Row fila = hojaPresupuesto.getRow(1);
        if (null == fila) {
            mensajesError.add("No se encontraron los datos. Por favor validar.");
        } else {
            String presupuestoTotIni = this.obtenerValorCelda(fila.getCell(0));
            String porcAdministracion = this.obtenerValorCelda(fila.getCell(1));
            String porcImprevistos = this.obtenerValorCelda(fila.getCell(2));
            String porcUtilidades = this.obtenerValorCelda(fila.getCell(3));

            presupuesto = this.validadorPresupuesto.validarInformacionPresupuesto(
                    presupuestoTotIni, porcAdministracion, porcImprevistos,
                    porcUtilidades, mensajesError);
        }

        return presupuesto;
    }

    private List<InfoVia> obtenerInfoHojaPriorizacion(Sheet hojaPriorizacion)
            throws IOException {
        List<InfoVia> vias = new ArrayList<>();

        Iterator<Row> rows = hojaPriorizacion.rowIterator();
        rows.next();
        rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            InfoVia infoVia = new InfoVia();
            Via via = new Via();
            for (int i = 0; i < NUMERO_COLUMNAS_PRIORIZACION; i++) {
                Cell cell = row.getCell(i);
                switch (i) {
                    case CODIGO_VIA:
                        via.setCodigoVia(this.obtenerValorCelda(cell));
                        break;
                    case DE_COMERCIAL:
                        via.getDesarrolloEconomico().setComercial(
                                this.obtenerValorCelda(cell));
                        break;
                    case DE_INDUSTRIAL:
                        via.getDesarrolloEconomico().setIndustrial(
                                this.obtenerValorCelda(cell));
                        break;
                    case DE_SERVICIOS:
                        via.getDesarrolloEconomico().setServicios(
                                this.obtenerValorCelda(cell));
                        break;
                    case DE_AGROPECUARIO:
                        via.getDesarrolloEconomico().setAgropecuario(
                                this.obtenerValorCelda(cell));
                        break;
                    case DE_MINERO:
                        via.getDesarrolloEconomico().setMinero(
                                this.obtenerValorCelda(cell));
                        break;
                    case CONECTIVIDAD:
                        via.setConectividad(this.obtenerValorCelda(cell));
                        break;
                    case TPD:
                        via.setTpd(this.obtenerValorCelda(cell));
                        break;
                    case VIAS_ALTERNAS:
                        via.setViasAlternas(this.obtenerValorCelda(cell));
                        break;
                    case PERSONAS_TRANSPORTADAS_DIA:
                        via.setPersonasTransportadasDia(this.obtenerValorCelda(cell));
                        break;
                    case URCI:
                        via.setUrci(this.obtenerValorCelda(cell));
                        break;
                    case SECCION_TRANSVERSAL_INAPROPIADA_81:
                        via.getDanioVia().setSeccionTransversalInapropiada81(
                                this.obtenerValorCelda(cell));
                        break;
                    case DRENAJE_LONGITUDINAL_INADECUADO_82:
                        via.getDanioVia().setDrenajeLongitudinalInadecuado82(
                                this.obtenerValorCelda(cell));
                        break;
                    case DRENAJE_TRANSVERSAL_INADECUADO_83:
                        via.getDanioVia().setDrenajeTransversalInadecuado83(
                                this.obtenerValorCelda(cell));
                        break;
                    case CORRUGACIONES_84:
                        via.getDanioVia().setCorrugaciones84(
                                this.obtenerValorCelda(cell));
                        break;
                    case POLVO_85:
                        via.getDanioVia().setPolvo85(
                                this.obtenerValorCelda(cell));
                        break;
                    case BACHES_HUECOS_86:
                        via.getDanioVia().setBachesHuecos86(
                                this.obtenerValorCelda(cell));
                        break;
                    case AHUELLAMIENTO_SURCOS_87:
                        via.getDanioVia().setAhuellamientoSurcos87(
                                this.obtenerValorCelda(cell));
                        break;
                    case AGREGADO_SUELTO_88:
                        via.getDanioVia().setAgregadoSuelto88(
                                this.obtenerValorCelda(cell));
                        break;
                    case CABEZAS_DURAS_89:
                        String cd89 = this.obtenerValorCelda(cell);
                        via.getDanioVia().setCabezasDuras89(
                                cd89 == null || cd89.trim().isEmpty()
                                || "N".equals(cd89.trim()) ? "N" : "S");
                        break;
                    case PROBABILIDAD_DERRUMBES_90:
                        via.getDanioVia().setProbabilidadDerrumbes90(
                                this.obtenerValorCelda(cell));
                        break;
                    case DANIOS_BANCA_91:
                        via.getDanioVia().setDanioBanca91(
                                this.obtenerValorCelda(cell));
                        break;
                }
            }

            if (ValidadorVia.getInstance().isVacia(via)) {
                continue;
            }

            List<String> daniosSeleccionados = new ArrayList<>();
            infoVia.setFilaVia(row.getRowNum() + 1);
            infoVia.setVia(via);
            infoVia.setErroresVia(ValidadorVia.getInstance().
                    validarInformacion(via, daniosSeleccionados));
            infoVia.setDaniosSeleccionados(daniosSeleccionados);
            if (infoVia.getErroresVia().isEmpty()) {
                infoVia.setSumatoriaValores(ValidadorVia.sumarValoresVia(infoVia));
            }
            vias.add(infoVia);
        }

        return vias;
    }

    public boolean validarArchivoPlantilla(File archivo) {

        return (null != archivo && archivo.exists() && archivo.isFile()
                && archivo.canRead() && (archivo.getName().endsWith(
                        EXTENSION_ARCHIVO_xlsx) || archivo.getName().endsWith(
                        EXTENSION_ARCHIVO_XLSX)));
    }

    private String obtenerValorCelda(Cell celda) {
        if (null == celda) {
            return null;
        } else if (Cell.CELL_TYPE_NUMERIC == celda.getCellType()) {
            return String.valueOf(celda.getNumericCellValue()).replace(",", ".");
        } else if (Cell.CELL_TYPE_STRING == celda.getCellType()) {
            return celda.getStringCellValue().replace(",", ".");
        } else {
            return "";
        }
    }

    public boolean descargarPlantilla(File directorio) throws IOException {
        URL url = ArchivoExcel.class.getResource("/co/edu/udea/mantpriorivias/archivos/"
                + "recursos/Plantilla_MantPriorVias.xlsx");
        InputStream inputStream = url.openStream();

        boolean respuesta = this.descargarArchivo(inputStream, directorio,
                "Plantilla_MantPriorVias.xlsx");
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    public boolean descargarAlternativasIntervencion(File directorio) throws IOException {
        URL url = ArchivoExcel.class.getResource("/co/edu/udea/mantpriorivias/archivos/"
                + "recursos/" + ARCHIVO_ALTERNATIVAS_INTERVENCION);
        InputStream inputStream = url.openStream();

        boolean respuesta = this.descargarArchivo(inputStream, directorio,
                ARCHIVO_ALTERNATIVAS_INTERVENCION);
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    private boolean descargarArchivo(InputStream inputStream, File directorio,
            String nombreArchivo) {
        boolean correcto = false;
        try {
            try (OutputStream outputStream = new FileOutputStream(directorio
                    + "/" + nombreArchivo)) {
                byte[] buffer = new byte[1024];
                int longitud;
                while ((longitud = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, longitud);
                }
            }
            correcto = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return correcto;
    }
}
