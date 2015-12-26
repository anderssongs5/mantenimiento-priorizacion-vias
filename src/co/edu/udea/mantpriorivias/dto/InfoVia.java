package co.edu.udea.mantpriorivias.dto;

import co.edu.udea.mantpriorivias.negocio.entidades.Via;
import java.io.Serializable;
import java.util.List;

public class InfoVia implements Serializable {

    private Via via;
    private int filaVia;
    private String erroresVia;
    private double sumatoriaValores;
    private List<String> daniosSeleccionados;

    public Via getVia() {
        return via;
    }

    public void setVia(Via via) {
        this.via = via;
    }

    public int getFilaVia() {
        return filaVia;
    }

    public void setFilaVia(int filaVia) {
        this.filaVia = filaVia;
    }

    public String getErroresVia() {
        return erroresVia;
    }

    public void setErroresVia(String erroresVia) {
        this.erroresVia = erroresVia;
    }

    public double getSumatoriaValores() {
        return sumatoriaValores;
    }

    public void setSumatoriaValores(double sumatoriaValores) {
        this.sumatoriaValores = sumatoriaValores;
    }

    public List<String> getDaniosSeleccionados() {
        return daniosSeleccionados;
    }

    public void setDaniosSeleccionados(List<String> daniosSeleccionados) {
        this.daniosSeleccionados = daniosSeleccionados;
    }
}
