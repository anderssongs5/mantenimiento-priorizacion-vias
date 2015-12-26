package co.edu.udea.mantpriorivias.negocio.entidades;

import java.io.Serializable;

public class Via implements Serializable {

    private String codigoVia;
    private DesarrolloEconomico desarrolloEconomico;
    private String conectividad;
    private String tpd;
    private String personasTransportadasDia;
    private String urci;
    private String viasAlternas;
    private DanioVia danioVia;

    public Via() {
        this.desarrolloEconomico = new DesarrolloEconomico();
        this.danioVia = new DanioVia();
    }

    public String getCodigoVia() {
        return codigoVia;
    }

    public void setCodigoVia(String codigoVia) {
        this.codigoVia = codigoVia;
    }

    public DesarrolloEconomico getDesarrolloEconomico() {
        return desarrolloEconomico;
    }

    public void setDesarrolloEconomico(DesarrolloEconomico desarrolloEconomico) {
        this.desarrolloEconomico = desarrolloEconomico;
    }

    public String getConectividad() {
        return conectividad;
    }

    public void setConectividad(String conectividad) {
        this.conectividad = conectividad;
    }

    public String getTpd() {
        return tpd;
    }

    public void setTpd(String tpd) {
        this.tpd = tpd;
    }

    public String getViasAlternas() {
        return viasAlternas;
    }

    public void setViasAlternas(String viasAlternas) {
        this.viasAlternas = viasAlternas;
    }

    public DanioVia getDanioVia() {
        return danioVia;
    }

    public void setDanioVia(DanioVia danioVia) {
        this.danioVia = danioVia;
    }

    public String getPersonasTransportadasDia() {
        return personasTransportadasDia;
    }

    public void setPersonasTransportadasDia(String personasTransportadasDia) {
        this.personasTransportadasDia = personasTransportadasDia;
    }

    public String getUrci() {
        return urci;
    }

    public void setUrci(String urci) {
        this.urci = urci;
    }
}
