package co.edu.udea.mantpriorivias.archivos;

import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.entidades.Presupuesto;
import co.edu.udea.mantpriorivias.entidades.Via;
import co.edu.udea.mantpriorivias.entidades.InfoVia;
import co.edu.udea.mantpriorivias.validadores.ValidadorPresupuesto;
import co.edu.udea.mantpriorivias.validadores.ValidadorVia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LectorArchivoExcel {

    private static final int CODIGO_VIA = 0;
    private static final int DE_COMERCIAL = 1;
    private static final int DE_INDUSTRIAL = 2;
    private static final int DE_SERVICIOS = 3;
    private static final int DE_AGROPECUARIO = 4;
    private static final int DE_MINERO = 5;
    private static final int CONECTIVIDAD = 6;
    private static final int TPD = 7;
    private static final int VIAS_ALTERNAS = 8;
    private static final int SECCION_TRANSVERSAL_INAPROPIADA_81 = 9;
    private static final int DRENAJE_LATERAL_INADECUADO_82 = 10;
    private static final int CORRUGACIONES_83 = 11;
    private static final int POLVO_84 = 12;
    private static final int BACHES_HUECOS_85 = 13;
    private static final int AHUELLAMIENTO_SURCOS_86 = 14;
    private static final int AGREGADO_SUELTO_87 = 15;
    private static final int CABEZAS_DURAS_88 = 16;

    private static final String EXTENSION_ARCHIVO_xlsx = ".xlsx";
    private static final String EXTENSION_ARCHIVO_XLSX = ".XLSX";

    public MantPriorViasInfo leerArchivo(String rutaCompleta) throws FileNotFoundException,
            IOException {
        File archivo = new File(rutaCompleta);
        String mensajeErrorGeneral = "";
        MantPriorViasInfo mantPriorViasInfo = null;
        if (this.validarArchivo(archivo)) {
            List<String> mensajesErrorArchivo = new ArrayList<>();
            List<String> mensajesErrorHojaPresupuesto = new ArrayList<>();
            String mensajesErrorHojaPriorizacion = "";
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
            List<String> daniosSeleccionados = new ArrayList<>();
            if (null == hojaPriorizacion) {
                mensajesErrorArchivo.add("No existe la hoja Priorización.");
            } else {
                vias = this.obtenerInfoHojaPriorizacion(hojaPriorizacion,
                        daniosSeleccionados);
            }

            if (!vias.isEmpty()) {
                if (ValidadorVia.existenViasConCodigoRepetido(vias)) {
                    mensajesErrorHojaPriorizacion += "Existen vías con código "
                            + "igual. Por favor corrija la información ya que "
                            + "cada código de vía debe ser único.";
                }
            }

            System.out.println("Errores de archivo:");
            mensajesErrorArchivo.stream().forEach((String s) -> {
                System.out.println("\t" + s);
            });

            System.out.println("\nErrores Hoja Presupuesto:");
            mensajesErrorHojaPresupuesto.stream().forEach((String s) -> {
                System.out.println("\t" + s);
            });

            System.out.println("\nErrores Hoja Priorización:");
            for (InfoVia vu : vias) {
                System.out.println("Número de Fila: " + vu.getFilaVia());
                System.out.println("Errores: " + vu.getErroresVia() + "\n");
            }
            if (!mensajesErrorHojaPriorizacion.isEmpty()) {
                System.out.println(mensajesErrorHojaPriorizacion);
            }

            Collections.sort(daniosSeleccionados);
            mantPriorViasInfo = new MantPriorViasInfo(mensajesErrorArchivo,
                    mensajesErrorHojaPresupuesto,
                    mensajesErrorHojaPriorizacion, presupuesto, vias,
                    daniosSeleccionados);
        } else {
            mensajeErrorGeneral += "El archivo no es correcto. Por favor valide"
                    + " que el archivo exista y que su extensión sea XLSX.";
            mantPriorViasInfo = new MantPriorViasInfo(mensajeErrorGeneral);
            System.out.println(mensajeErrorGeneral);
        }

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

            presupuesto = ValidadorPresupuesto.validarInformacion(
                    presupuestoTotIni, porcAdministracion, porcImprevistos,
                    porcUtilidades, mensajesError);
        }

        return presupuesto;
    }

    private List<InfoVia> obtenerInfoHojaPriorizacion(Sheet hojaPriorizacion,
            List<String> daniosSeleccionados)
            throws IOException {
        List<InfoVia> vias = new ArrayList<>();

        Iterator<Row> rows = hojaPriorizacion.iterator();
        rows.next();
        rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            InfoVia infoVia = new InfoVia();
            Via via = new Via();
            for (int i = 0; i < 17; i++) {
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
                    case SECCION_TRANSVERSAL_INAPROPIADA_81:
                        via.getDanioVia().setSeccionTransversalInapropiada81(
                                this.obtenerValorCelda(cell));
                        break;
                    case DRENAJE_LATERAL_INADECUADO_82:
                        via.getDanioVia().setDrenajeLateralInadecuado82(
                                this.obtenerValorCelda(cell));
                        break;
                    case CORRUGACIONES_83:
                        via.getDanioVia().setCorrugaciones83(
                                this.obtenerValorCelda(cell));
                        break;
                    case POLVO_84:
                        via.getDanioVia().setPolvo84(
                                this.obtenerValorCelda(cell));
                        break;
                    case BACHES_HUECOS_85:
                        via.getDanioVia().setBachesHuecos85(
                                this.obtenerValorCelda(cell));
                        break;
                    case AHUELLAMIENTO_SURCOS_86:
                        via.getDanioVia().setAhuellamientoSurcos86(
                                this.obtenerValorCelda(cell));
                        break;
                    case AGREGADO_SUELTO_87:
                        via.getDanioVia().setAgregadoSuelto87(
                                this.obtenerValorCelda(cell));
                        break;
                    case CABEZAS_DURAS_88:
                        via.getDanioVia().setCabezasDuras88(
                                this.obtenerValorCelda(cell));
                        break;
                }
            }

            infoVia.setFilaVia(row.getRowNum() + 1);
            infoVia.setVia(via);
            infoVia.setErroresVia(ValidadorVia.getInstance().
                    validarInformacion(via, daniosSeleccionados));
            vias.add(infoVia);
        }

        return vias;
    }

    private boolean validarArchivo(File archivo) {

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
}
