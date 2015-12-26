package co.edu.udea.mantpriorivias.negocio.entidades;

import java.io.Serializable;

public class Alternativa implements Serializable {

    private String codigoVia;
    private String danio;
    private String item;
    private double cantidad;

    public String getCodigoVia() {
        return codigoVia;
    }

    public void setCodigoVia(String codigoVia) {
        this.codigoVia = codigoVia;
    }

    public String getDanio() {
        return danio;
    }

    public void setDanio(String danio) {
        this.danio = danio;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
