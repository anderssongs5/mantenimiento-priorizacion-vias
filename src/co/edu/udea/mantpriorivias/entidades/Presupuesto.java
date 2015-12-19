/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mantpriorivias.entidades;

/**
 *
 * @author Samsung
 */
public class Presupuesto {

    double presupuestoTotal;
    double porcentajeAdministracion;
    double porcentajeImprevistos;
    double porcentajeUtilidades;
    double presupuestoDisponible;

    public Presupuesto(double presupuestoTotal, double porcentajeAdministracion,
            double porcentajeImprevistos, double porcentajeUtiliadades) {
        this.presupuestoTotal = presupuestoTotal;
        this.porcentajeAdministracion = porcentajeAdministracion;
        this.porcentajeImprevistos = porcentajeImprevistos;
        this.porcentajeUtilidades = porcentajeUtiliadades;

        this.presupuestoDisponible = presupuestoTotal - presupuestoTotal
                * (porcentajeAdministracion + porcentajeImprevistos
                + porcentajeUtiliadades);
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

}
