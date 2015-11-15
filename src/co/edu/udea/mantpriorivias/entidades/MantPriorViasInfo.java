package co.edu.udea.mantpriorivias.entidades;

import co.edu.udea.mantpriorivias.validadores.ValidadorVia;
import java.util.List;

public class MantPriorViasInfo {

    private List<String> erroresArchivo;
    private List<String> erroresHojaPresupuesto;
    private String erroresHojaPriorizacion;
    private String erroresHojaCostosMantenimiento;

    private Presupuesto presupuesto;
    private List<InfoVia> vias;
    private List<InfoItem> items;

    public MantPriorViasInfo() {
        super();
    }

    public MantPriorViasInfo(List<String> erroresArchivo,
            List<String> erroresHojaPresupuesto, String erroresHojaPriorizacion,
            Presupuesto presupuesto, List<InfoVia> vias, List<InfoItem> items,
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

    public List<InfoItem> getItems() {
        return items;
    }

    public void setItems(List<InfoItem> items) {
        this.items = items;
    }

    public boolean tieneErrores() {
        /*return ((this.erroresArchivo != null && !this.erroresArchivo.isEmpty())
                || (this.erroresHojaPresupuesto != null
                && !this.erroresHojaPresupuesto.isEmpty())
                || (this.erroresHojaPriorizacion != null
                && !this.erroresHojaPriorizacion.isEmpty())
                || this.existenViasConErrores()
                || ValidadorVia.existenViasConCodigoRepetido(this.vias)
                || (this.erroresHojaCostosMantenimiento != null
                && this.erroresHojaCostosMantenimiento.isEmpty())
                || this.existenItemsConErrores());*/
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

    public boolean existenItemsConErrores() {
        if (this.items == null || this.items.isEmpty()) {
            return false;
        }

        return this.items.stream().anyMatch((ii) -> (ii != null && ii.getItem() != null
                && ii.getErroresItem() != null && !ii.getErroresItem().isEmpty()));
    }
}
