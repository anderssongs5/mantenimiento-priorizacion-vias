package co.edu.udea.mantpriorivias.negocio.validadores;

import co.edu.udea.mantpriorivias.archivos.ArchivoExcel;
import co.edu.udea.mantpriorivias.archivos.ArchivoTextoPlano;
import co.edu.udea.mantpriorivias.dto.InfoVia;
import co.edu.udea.mantpriorivias.dto.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.general.Util;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidadorPlantilla {

    private final ArchivoExcel archivoExcel = new ArchivoExcel();
    private final ArchivoTextoPlano archivoTextoPlano = new ArchivoTextoPlano();

    public String estructurarInformacionErrorPlantilla(MantPriorViasInfo mantPriorViasInfo) {
        String informacion = "";
        String separadorLinea = Util.getSeparadorLinea();
        if (mantPriorViasInfo.getErroresArchivo() != null
                && !mantPriorViasInfo.getErroresArchivo().isEmpty()) {
            informacion += "Errores del archivo:" + separadorLinea;
            informacion = mantPriorViasInfo.getErroresArchivo().stream().map((s)
                    -> "    * " + s.trim() + separadorLinea).reduce(informacion,
                            String::concat);
        }

        if (!informacion.isEmpty()) {
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "====================================================";
            informacion += separadorLinea;
            informacion += separadorLinea;
        }

        if (mantPriorViasInfo.getErroresHojaPresupuesto() != null
                && !mantPriorViasInfo.getErroresHojaPresupuesto().isEmpty()) {
            informacion += "Errores en hoja Presupuesto:" + separadorLinea;
            informacion = mantPriorViasInfo.getErroresHojaPresupuesto().stream()
                    .map((s) -> "    * " + s.trim() + separadorLinea).reduce(
                            informacion, String::concat);
        }

        if (!informacion.isEmpty()) {
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "====================================================";
            informacion += separadorLinea;
            informacion += separadorLinea;
        }

        boolean erroresVias = false;
        if (mantPriorViasInfo.existenViasConErrores()) {
            informacion += "Errores en hoja Priorización o errores en vías:";
            informacion += separadorLinea;
            informacion += separadorLinea;
            for (InfoVia iv : mantPriorViasInfo.getVias()) {
                if (iv.getErroresVia() != null && !iv.getErroresVia().isEmpty()) {
                    informacion += "Número de fila: " + iv.getFilaVia() + separadorLinea;
                    String codVia = (iv.getVia().getCodigoVia() != null)
                            ? iv.getVia().getCodigoVia() : "";
                    informacion += "Código de la vía: " + codVia + separadorLinea;
                    informacion += "Errores: " + separadorLinea + iv.getErroresVia();
                    informacion += separadorLinea;
                    informacion += separadorLinea;
                }
            }
            erroresVias = true;
        } else if (mantPriorViasInfo.getVias().isEmpty()) {
            informacion += "Errores en hoja Priorización o errores en vías:";
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "    * No existe ninguna vía.";
            erroresVias = true;
        }

        if (mantPriorViasInfo.getErroresHojaPriorizacion() != null
                && !mantPriorViasInfo.getErroresHojaPriorizacion().isEmpty()) {
            if (!erroresVias) {
                informacion += "Errores en hoja Priorización o errores en vías:";
                informacion += separadorLinea;
                informacion += separadorLinea;
            }
            informacion += mantPriorViasInfo.getErroresHojaPriorizacion().trim()
                    + separadorLinea;
        }

        return informacion;
    }

    public MantPriorViasInfo leerPlantilla(File archivo) {

        try {
            return this.archivoExcel.leerPlantilla(archivo);
        } catch (IOException ex) {
            Logger.getLogger(ValidadorPlantilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean validarArchivoPlantilla(File archivo) {

        return this.archivoExcel.validarArchivoPlantilla(archivo);
    }

    public boolean descargarPlantilla(File directorio) throws IOException {

        return this.archivoExcel.descargarPlantilla(directorio);
    }

    public boolean descargarAlternativasIntervencion(File directorio) throws IOException {

        return this.archivoExcel.descargarAlternativasIntervencion(directorio);
    }

    public boolean crearArchivo(String contenido, String fullPath) {

        return this.archivoTextoPlano.crearArchivo(contenido, fullPath);
    }
}
