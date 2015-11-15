package co.edu.udea.mantpriorivias.validadores;

import co.edu.udea.mantpriorivias.entidades.InfoVia;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorPriorizacion {

    public List<String> obtenerValoresUnicosDanios(List<InfoVia> vias) {
        List<String> daniosUnicos = new ArrayList<>();
        if (vias != null && !vias.isEmpty()) {
            vias.stream().forEach(via -> {
                via.getDaniosSeleccionados().stream().forEach(danio -> {
                    daniosUnicos.add(danio.trim());
                });
            });
        }

        return daniosUnicos.stream().distinct().collect(Collectors.toList());
    }
}
