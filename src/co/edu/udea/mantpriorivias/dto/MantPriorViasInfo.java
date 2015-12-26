package co.edu.udea.mantpriorivias.dto;

import co.edu.udea.mantpriorivias.negocio.entidades.Item;
import co.edu.udea.mantpriorivias.negocio.entidades.Presupuesto;
import co.edu.udea.mantpriorivias.negocio.validadores.ValidadorVia;
import java.io.Serializable;
import java.util.List;

public class MantPriorViasInfo implements Serializable {

    private List<String> erroresArchivo;
    private List<String> erroresHojaPresupuesto;
    private String erroresHojaPriorizacion;
    private String erroresHojaCostosMantenimiento;

    private Presupuesto presupuesto;
    private List<InfoVia> vias;
    private List<Item> items;

    public MantPriorViasInfo() {
        super();
    }

    public MantPriorViasInfo(List<String> erroresArchivo,
            List<String> erroresHojaPresupuesto, String erroresHojaPriorizacion,
            Presupuesto presupuesto, List<InfoVia> vias, List<Item> items,
            String erroresHojaCostosMantenimiento) {
        this.erroresArchivo = erroresArchivo;
        this.erroresHojaPresupuesto = erroresHojaPresupuesto;
        this.erroresHojaPriorizacion = erroresHojaPriorizacion;
        this.presupuesto = presupuesto;
        this.vias = vias;
        this.items = items;
        this.erroresHojaCostosMantenimiento = erroresHojaCostosMantenimiento;
    }

    public MantPriorViasInfo(List<String> erroresArchivo,
            List<String> erroresHojaPresupuesto, String erroresHojaPriorizacion,
            Presupuesto presupuesto, List<InfoVia> vias) {
        this.erroresArchivo = erroresArchivo;
        this.erroresHojaPresupuesto = erroresHojaPresupuesto;
        this.erroresHojaPriorizacion = erroresHojaPriorizacion;
        this.presupuesto = presupuesto;
        this.vias = vias;
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

    public String getErroresHojaCostosMantenimiento() {
        return erroresHojaCostosMantenimiento;
    }

    public void setErroresHojaCostosMantenimiento(String erroresHojaCostosMantenimiento) {
        this.erroresHojaCostosMantenimiento = erroresHojaCostosMantenimiento;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean tieneErrores() {
        return ((this.erroresArchivo != null && !this.erroresArchivo.isEmpty())
                || (this.erroresHojaPresupuesto != null
                && !this.erroresHojaPresupuesto.isEmpty())
                || (this.erroresHojaPriorizacion != null
                && !this.erroresHojaPriorizacion.isEmpty())
                || this.vias.isEmpty()
                || this.existenViasConErrores()
                || ValidadorVia.existenViasConCodigoRepetido(this.vias));
    }

    public boolean existenViasConErrores() {
        if (this.vias == null || this.vias.isEmpty()) {
            return false;
        }

        return this.vias.stream().anyMatch((iv) -> (iv.getErroresVia() != null
                && !iv.getErroresVia().isEmpty()));
    }
}
