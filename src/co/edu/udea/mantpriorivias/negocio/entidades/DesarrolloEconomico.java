package co.edu.udea.mantpriorivias.negocio.entidades;

import java.io.Serializable;

public class DesarrolloEconomico implements Serializable {

    private String comercial;
    private String industrial;
    private String servicios;
    private String agropecuario;
    private String minero;

    public String getComercial() {
        return comercial;
    }

    public void setComercial(String comercial) {
        this.comercial = comercial;
    }

    public String getIndustrial() {
        return industrial;
    }

    public void setIndustrial(String industrial) {
        this.industrial = industrial;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getAgropecuario() {
        return agropecuario;
    }

    public void setAgropecuario(String agropecuario) {
        this.agropecuario = agropecuario;
    }

    public String getMinero() {
        return minero;
    }

    public void setMinero(String minero) {
        this.minero = minero;
    }
}
