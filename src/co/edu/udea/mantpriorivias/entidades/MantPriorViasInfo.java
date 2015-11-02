package co.edu.udea.mantpriorivias.entidades;

import java.util.List;

public class MantPriorViasInfo {

    private String errorGeneral;
    private List<String> erroresArchivo;
    private List<String> erroresHojaPresupuesto;
    private String erroresHojaPriorizacion;

    private Presupuesto presupuesto;
    private List<InfoVia> vias;
    private List<String> daniosSeleccionados;

    public MantPriorViasInfo() {
        super();
    }

    public MantPriorViasInfo(String errorGeneral) {
        this.errorGeneral = errorGeneral;
    }

    public MantPriorViasInfo(List<String> erroresArchivo,
            List<String> erroresHojaPresupuesto, String erroresHojaPriorizacion,
            Presupuesto presupuesto, List<InfoVia> vias,
            List<String> daniosSeleccionados) {
        this.erroresArchivo = erroresArchivo;
        this.erroresHojaPresupuesto = erroresHojaPresupuesto;
        this.erroresHojaPriorizacion = erroresHojaPriorizacion;
        this.presupuesto = presupuesto;
        this.vias = vias;
        this.daniosSeleccionados = daniosSeleccionados;
    }

    public String getErrorGeneral() {
        return errorGeneral;
    }

    public void setErrorGeneral(String errorGeneral) {
        this.errorGeneral = errorGeneral;
    }

    public List<String> getErroresArchivo() {
        return erroresArchivo;
    }

    public void setErroresArchivo(List<String> erroresArchivo) {
        this.erroresArchivo = erroresArchivo;
    }

    public List<String> getErroresHojaPresupuesto() {
        return erroresHojaPresupuesto;
    }

    public void setErroresHojaPresupuesto(List<String> erroresHojaPresupuesto) {
        this.erroresHojaPresupuesto = erroresHojaPresupuesto;
    }

    public String getErroresHojaPriorizacion() {
        return erroresHojaPriorizacion;
    }

    public void setErroresHojaPriorizacion(String erroresHojaPriorizacion) {
        this.erroresHojaPriorizacion = erroresHojaPriorizacion;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<InfoVia> getVias() {
        return vias;
    }

    public void setVias(List<InfoVia> vias) {
        this.vias = vias;
    }

    public List<String> getDaniosSeleccionados() {
        return daniosSeleccionados;
    }

    public void setDaniosSeleccionados(List<String> daniosSeleccionados) {
        this.daniosSeleccionados = daniosSeleccionados;
    }
}
