package co.edu.udea.mantpriorivias.negocio.entidades;

import co.edu.udea.mantpriorivias.general.Util;
import java.io.Serializable;

public class Presupuesto implements Serializable {

    double presupuestoTotal;
    double porcentajeAdministracion;
    double porcentajeImprevistos;
    double porcentajeUtilidades;
    double presupuestoDisponible;
    double presupuestoAdicional = 0.0;
    double presupuestoActual;

    public Presupuesto(double presupuestoTotal, double porcentajeAdministracion,
            double porcentajeImprevistos, double porcentajeUtilidades) {
        this.presupuestoTotal = Util.formatearValorOperacion(presupuestoTotal);
        this.porcentajeAdministracion = Util.formatearValorOperacion(porcentajeAdministracion);
        this.porcentajeImprevistos = Util.formatearValorOperacion(porcentajeImprevistos);
        this.porcentajeUtilidades = Util.formatearValorOperacion(porcentajeUtilidades);

        this.presupuestoDisponible = Util.formatearValorOperacion(
                this.calcularPrespuestoDisponible(presupuestoTotal,
                        porcentajeAdministracion, porcentajeImprevistos,
                        porcentajeUtilidades));
        this.presupuestoActual = Util.formatearValorOperacion(
                this.calcularPrespuestoDisponible(presupuestoTotal,
                        porcentajeAdministracion, porcentajeImprevistos,
                        porcentajeUtilidades));
    }

    public double getPresupuestoTotal() {
        return presupuestoTotal;
    }

    public void setPresupuestoTotal(double presupuestoTotal) {
        this.presupuestoTotal = presupuestoTotal;
    }

    public double getPorcentajeAdministracion() {
        return porcentajeAdministracion;
    }

    public void setPorcentajeAdministracion(double porcentajeAdministracion) {
        this.porcentajeAdministracion = porcentajeAdministracion;
    }

    public double getPorcentajeImprevistos() {
        return porcentajeImprevistos;
    }

    public void setPorcentajeImprevistos(double porcentajeImprevistos) {
        this.porcentajeImprevistos = porcentajeImprevistos;
    }

    public double getPorcentajeUtilidades() {
        return porcentajeUtilidades;
    }

    public void setPorcentajeUtilidades(double porcentajeUtilidades) {
        this.porcentajeUtilidades = porcentajeUtilidades;
    }

    public double getPresupuestoDisponible() {
        return presupuestoDisponible;
    }

    public void setPresupuestoDisponible(double presupuestoDisponible) {
        this.presupuestoDisponible = presupuestoDisponible;
    }

    public double getPresupuestoAdicional() {
        return presupuestoAdicional;
    }

    public void setPresupuestoAdicional(double presupuestoAdicional) {
        this.presupuestoAdicional = presupuestoAdicional;
    }

    public double getPresupuestoActual() {
        return presupuestoActual;
    }

    public void setPresupuestoActual(double presupuestoActual) {
        this.presupuestoActual = presupuestoActual;
    }

    private double calcularPrespuestoDisponible(double presupuestoTotal,
            double porcentajeAdministracion, double porcentajeImprevistos,
            double porcentajeUtilidades) {
        return (presupuestoTotal - presupuestoTotal
                * (porcentajeAdministracion + porcentajeImprevistos
                + porcentajeUtilidades));
    }
}
