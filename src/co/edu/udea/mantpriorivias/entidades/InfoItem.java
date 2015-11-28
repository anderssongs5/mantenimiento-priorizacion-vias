package co.edu.udea.mantpriorivias.entidades;

public class InfoItem {

    private Item item;
    private String erroresItem;
    private int fila;

    public InfoItem() {
        super();
    }

    public InfoItem(Item item, String erroresItem, int fila) {
        this.item = item;
        this.erroresItem = erroresItem;
        this.fila = fila;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getErroresItem() {
        return erroresItem;
    }

    public void setErroresItem(String erroresItem) {
        this.erroresItem = erroresItem;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
}
