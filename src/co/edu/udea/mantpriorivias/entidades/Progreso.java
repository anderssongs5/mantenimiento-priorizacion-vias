package co.edu.udea.mantpriorivias.entidades;

import java.io.Serializable;
import java.util.List;

public class Progreso implements Serializable {

    private MantPriorViasInfo mantPriorViasInfo;
    private List<Alternativa> alternativasMantenimiento;
    private List<Alternativa> alternativasMejorasTSR;
    private List<Alternativa> alternativasMejorasEA;
    private List<ResumenMantenimiento> viasResumenMantenimiento;
    private List<ResumenMejora> viasResumenMejora;

    public MantPriorViasInfo getMantPriorViasInfo() {
        return mantPriorViasInfo;
    }

    public void setMantPriorViasInfo(MantPriorViasInfo mantPriorViasInfo) {
        this.mantPriorViasInfo = mantPriorViasInfo;
    }

    public List<ResumenMantenimiento> getViasResumenMantenimiento() {
        return viasResumenMantenimiento;
    }

    public void setViasResumenMantenimiento(List<ResumenMantenimiento> viasResumenMantenimiento) {
        this.viasResumenMantenimiento = viasResumenMantenimiento;
    }

    public List<ResumenMejora> getViasResumenMejora() {
        return viasResumenMejora;
    }

    public void setViasResumenMejora(List<ResumenMejora> viasResumenMejora) {
        this.viasResumenMejora = viasResumenMejora;
    }

    public List<Alternativa> getAlternativasMantenimiento() {
        return alternativasMantenimiento;
    }

    public void setAlternativasMantenimiento(List<Alternativa> alternativasMantenimiento) {
        this.alternativasMantenimiento = alternativasMantenimiento;
    }

    public List<Alternativa> getAlternativasMejorasTSR() {
        return alternativasMejorasTSR;
    }

    public void setAlternativasMejorasTSR(List<Alternativa> alternativasMejorasTSR) {
        this.alternativasMejorasTSR = alternativasMejorasTSR;
    }

    public List<Alternativa> getAlternativasMejorasEA() {
        return alternativasMejorasEA;
    }

    public void setAlternativasMejorasEA(List<Alternativa> alternativasMejorasEA) {
        this.alternativasMejorasEA = alternativasMejorasEA;
    }
}
