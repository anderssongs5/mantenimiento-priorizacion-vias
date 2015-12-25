package co.edu.udea.mantpriorivias.entidades;

import java.io.Serializable;

public class Item implements Serializable {

    private String codigo;
    private String item;
    private Unidad unidad;
    private double valorUnitario;
    private String valorUnitarioString;

    public Item(String codigo, String item, Unidad unidad, String valorUnitarioString) {
        this.codigo = codigo;
        this.item = item;
        this.unidad = unidad;
        this.valorUnitarioString = valorUnitarioString;

        this.valorUnitario = Double.parseDouble(valorUnitarioString);
    }

    public Item(String codigo, String item, Unidad unidad, double valorUnitario) {
        this.codigo = codigo;
        this.item = item;
        this.unidad = unidad;
        this.valorUnitario = valorUnitario;
    }

    public Item(String codigo, String item) {
        this.codigo = codigo;
        this.item = item;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getValorUnitarioString() {
        return valorUnitarioString;
    }

    public void setValorUnitarioString(String valorUnitarioString) {
        this.valorUnitarioString = valorUnitarioString;
    }
}
