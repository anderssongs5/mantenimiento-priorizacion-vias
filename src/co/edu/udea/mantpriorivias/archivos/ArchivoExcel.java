package co.edu.udea.mantpriorivias.archivos;

import co.edu.udea.mantpriorivias.entidades.InfoItem;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.entidades.Presupuesto;
import co.edu.udea.mantpriorivias.entidades.Via;
import co.edu.udea.mantpriorivias.entidades.InfoVia;
import co.edu.udea.mantpriorivias.entidades.Item;
import co.edu.udea.mantpriorivias.validadores.ValidadorCostosMantenimiento;
import co.edu.udea.mantpriorivias.validadores.ValidadorPresupuesto;
import co.edu.udea.mantpriorivias.validadores.ValidadorVia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
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
        List<String> daniosSeleccionados = new ArrayList<>();
        if (null == hojaPriorizacion) {
            mensajesErrorArchivo.add("No existe la hoja Priorización.");
        } else {
            vias = this.obtenerInfoHojaPriorizacion(hojaPriorizacion,
                    daniosSeleccionados);
        }

        if (!vias.isEmpty()) {
            if (ValidadorVia.existenViasConCodigoRepetido(vias)) {
                mensajesErrorHojaPriorizacion += "*** Existen vías con código "
                        + "igual. Por favor corrija la información ya que "
                        + "cada código de vía debe ser único. ***";
            }
        }

        Sheet hojaCostosMantenimiento = workbook.getSheet("Costos de mantenimiento");
        List<InfoItem> items = new ArrayList<>();
        if (null == hojaCostosMantenimiento) {
            mensajesErrorArchivo.add("No existe la hoja Costos de mantenimiento.");
        } else {
            items = this.obtenerInfoCostosMantenimiento(hojaCostosMantenimiento);
            ValidadorCostosMantenimiento vcs = new ValidadorCostosMantenimiento();
            if (!vcs.contieneTodosLosItems(items)) {
                mensajesErrorHojaCostosMantenimiento += "La hoja Costos de "
                        + "mantenimiento no contiene todos los ítems establecidos "
                        + "para la misma.";
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

        System.out.println("\nErrores en Hoja Costos de mantenimiento:");
        for (InfoItem ii : items) {
            System.out.println("Número de fila: " + ii.getFila());
            System.out.println("Código ítem: " + ii.getItem().getCodigo());
            System.out.println("Errores: " + ii.getErroresItem());
        }

        Collections.sort(daniosSeleccionados);
        mantPriorViasInfo = new MantPriorViasInfo(mensajesErrorArchivo,
                mensajesErrorHojaPresupuesto,
                mensajesErrorHojaPriorizacion, presupuesto, vias,
                daniosSeleccionados, items, mensajesErrorHojaCostosMantenimiento);

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
                        via.getDanioVia().setCabezasDuras89(
                                this.obtenerValorCelda(cell));
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

            infoVia.setFilaVia(row.getRowNum() + 1);
            infoVia.setVia(via);
            infoVia.setErroresVia(ValidadorVia.getInstance().
                    validarInformacion(via, daniosSeleccionados));
            vias.add(infoVia);
        }

        return vias;
    }

    private List<InfoItem> obtenerInfoCostosMantenimiento(Sheet hojaCostosMantenimiento)
            throws IOException {
        ValidadorCostosMantenimiento validadorCostosMantenimiento
                = new ValidadorCostosMantenimiento();
        List<InfoItem> items = new ArrayList<>();
        Iterator<Row> rows = hojaCostosMantenimiento.iterator();

        rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            String codigo = this.obtenerValorCelda(row.getCell(1));
            String item = this.obtenerValorCelda(row.getCell(2));
            String unidad = this.obtenerValorCelda(row.getCell(1));
            String valorUnitario = this.obtenerValorCelda(row.getCell(1));
            int fila = row.getRowNum();

            Item i = new Item(codigo, item, unidad, valorUnitario);
            String erroresItem = validadorCostosMantenimiento.validarItem(i);

            items.add(new InfoItem(i, erroresItem, fila));
        }

        return items;
    }

    public boolean validarArchivo(File archivo) {

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

    public boolean descargarPlantilla(File directorio) {
        boolean correcto = false;
        InputStream inputStream = ArchivoExcel.class.getClassLoader().
                getResourceAsStream("./co/edu/udea/mantpriorivias/archivos/"
                        + "recursos/Plantilla_MantPriorVias.xlsx");
        try {
            OutputStream outputStream = new FileOutputStream(directorio
                    + "/Plantilla_MantPriorVias.xlsx");
            byte[] buffer = new byte[1024];
            int longitud;
            while ((longitud = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, longitud);
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
