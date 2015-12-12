package co.edu.udea.mantpriorivias.entidades;

import java.util.ArrayList;
import java.util.List;

public class ResumenMantenimiento {

    private String via;
    private List<String> daniosPorVia = new ArrayList<>();
    private List<List<Alternativa>> alternativasPorDanio = new ArrayList<>();

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public List<String> getDaniosPorVia() {
        return daniosPorVia;
    }

    public void setDaniosPorVia(List<String> daniosPorVia) {
        this.daniosPorVia = daniosPorVia;
    }

    public List<List<Alternativa>> getAlternativasPorDanio() {
        return alternativasPorDanio;
    }

    public void setAlternativasPorDanio(List<List<Alternativa>> alternativasPorDanio) {
        this.alternativasPorDanio = alternativasPorDanio;
    }
}
