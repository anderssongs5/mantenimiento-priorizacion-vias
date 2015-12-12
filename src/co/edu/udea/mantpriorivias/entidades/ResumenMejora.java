package co.edu.udea.mantpriorivias.entidades;

import java.util.ArrayList;
import java.util.List;

public class ResumenMejora {
    
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
