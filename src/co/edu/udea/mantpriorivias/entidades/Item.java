package co.edu.udea.mantpriorivias.entidades;

public class Item {

    private String codigo;
    private String item;
    private String unidad;
    private double valorUnitario;
    private String valorUnitarioString;

    public Item(String codigo, String item, String unidad, String valorUnitarioString) {
        this.codigo = codigo;
        this.item = item;
        this.unidad = unidad;
        this.valorUnitarioString = valorUnitarioString;
    }

    public Item(String codigo, String item, String unidad, double valorUnitario) {
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

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
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
