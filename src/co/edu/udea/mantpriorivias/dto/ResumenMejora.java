package co.edu.udea.mantpriorivias.dto;

import co.edu.udea.mantpriorivias.negocio.entidades.Alternativa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResumenMejora implements Serializable {

    private String via;
    private List<Alternativa> alternativas = new ArrayList<>();

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }
}
