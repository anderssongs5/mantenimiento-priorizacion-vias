package co.edu.udea.mantpriorivias.constantes;

import co.edu.udea.mantpriorivias.entidades.Item;
import co.edu.udea.mantpriorivias.entidades.Unidad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constantes {

    public static final String[] NOMBRES_COLUMNAS = new String[]{"Código",
        "Nombre", "Unidad", "Valor Unitario"};
    public static final int CANTIDAD_COLUMNAS = 4;
    public static final int CANTIDAD_ITEMS_MEJORAS = 8;
    public static final int TSR_INICIO = 1;
    public static final int TSR_FINAL = 5;
    public static final int EA_INICIO = 6;
    public static final int EA_FINAL = 8;
    public static final List<Unidad> UNIDADES_POSIBLES = new ArrayList<>();
    public static final Map<String, Item> ITEMS = new HashMap<>();
    public static final Map<String, String> ALTERNATIVAS_INTERVENCION_MANTENIMIENTO = new HashMap<>();
    public static final Map<String, Double> VIAS_ALTERNAS_VALORES = new HashMap<>();
    public static final Map<String, String> NOMBRES_DANIOS = new HashMap<>();
    public static final List<String> DANIOS_PERMANECEN_POR_MEJORAS = new ArrayList<>();

    static {
        ITEMS.put("ME1", new Item("ME1", "Slurry Seal"));
        ITEMS.put("ME2", new Item("ME2", "Mapia"));
        ITEMS.put("ME3", new Item("ME3", "Micropavimento"));
        ITEMS.put("ME4", new Item("ME4", "Tratamiento superficial doble"));
        ITEMS.put("ME5", new Item("ME5", "Tratamiento superficial simple"));
        ITEMS.put("ME6", new Item("ME6", "Estabilización Física"));
        ITEMS.put("ME7", new Item("ME7", "Estabilización Química"));
        ITEMS.put("ME8", new Item("ME8", "Estabilización Mecánica"));
        ITEMS.put("MA1", new Item("MA1", "Perfilado ligero, no contempla adición "
                + "de material"));
        ITEMS.put("MA2", new Item("MA2", "Perfilado del terreno y adición de m"
                + "aterial"));
        ITEMS.put("MA3", new Item("MA3", "Corte de la base existente y adición "
                + "de agregado. Incluye conformación y compactación"));
        ITEMS.put("MA4", new Item("MA4", "Limpieza de cunetas "));
        ITEMS.put("MA5", new Item("MA5", "Conformación y ampliación de las cunetas"));
        ITEMS.put("MA6", new Item("MA6", "Construcción e instalación de sistemas "
                + "de drenajes longitudinales"));
        ITEMS.put("MA7", new Item("MA7", "Limpieza de alcantarillas y box culverts"));
        ITEMS.put("MA8", new Item("MA8", "Construcción y ampliación de alcantarillas"));
        ITEMS.put("MA9", new Item("MA9", "Construcción e instalación de sistemas "
                + "de drenajes transversales"));
        ITEMS.put("MA10", new Item("MA10", "Adición de agua"));
        ITEMS.put("MA11", new Item("MA11", "Reaplicación localizada de grava"));
        ITEMS.put("MA12", new Item("MA12", "Adición de estabilizante control polvo"));
        ITEMS.put("MA13", new Item("MA13", "Corte de la base existente y adición "
                + "de agregado. Incluye conformación y compactación"));
        ITEMS.put("MA14", new Item("MA14", "Perfilado del terreno y adición de m"
                + "aterial"));
        ITEMS.put("MA15", new Item("MA15", "Perfilado y revegetalización de taludes"));
        ITEMS.put("MA16", new Item("MA16", "Construcción de sistemas de drenaje "
                + "para taludes"));
        ITEMS.put("MA17", new Item("MA17", "Construcción de obras de disipación "
                + "de energía"));
        ITEMS.put("MA18", new Item("MA18", "Estabilización de talud"));
        ITEMS.put("MA19", new Item("MA19", "Construcción de obra de contención "
                + "para taludes"));
        ITEMS.put("MA20", new Item("MA20", "Nivelación y conformación de la banca"));
        ITEMS.put("MA21", new Item("MA21", "Construcción de lleno para estabilizar "
                + "la banca"));
        ITEMS.put("MA22", new Item("MA22", "Construcción de obra de contención "
                + "para la banca"));

        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("81L", "MA1");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("81M", "MA2");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("81H", "MA3");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("82L", "MA4");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("82M", "MA5");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("82H", "MA6");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("83L", "MA7");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("83M", "MA8");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("83H", "MA9");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("84L", "MA1");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("84M", "MA2");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("84H", "MA3");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("85L", "MA10");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("85M", "MA12");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("85H", "MA13");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("86L", "MA11");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("86M", "MA14");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("86H", "MA3");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("87L", "MA1");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("87M", "MA2");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("87H", "MA3");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("88L", "MA1");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("88M", "MA2");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("88H", "MA3");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("89N", "");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("89S", "MA1");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("90L", "MA15");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("90M", "MA16,MA17");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("90H", "MA18,MA19");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("91L", "MA20");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("91M", "MA21");
        ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.put("91H", "MA22");

        UNIDADES_POSIBLES.add(new Unidad("M", "Metros"));
        UNIDADES_POSIBLES.add(new Unidad("M2", "Metros cuadrados"));
        UNIDADES_POSIBLES.add(new Unidad("M3", "Metros cúbicos"));
        UNIDADES_POSIBLES.add(new Unidad("KM", "Kilómetros"));

        VIAS_ALTERNAS_VALORES.put("S", 0.5);
        VIAS_ALTERNAS_VALORES.put("N", 1.0);

        NOMBRES_DANIOS.put("81", "Sección Transversal Inapropiada");
        NOMBRES_DANIOS.put("82", "Drenaje Longitudinal Inadecuado");
        NOMBRES_DANIOS.put("83", "Drenaje Transversla Inadecuado");
        NOMBRES_DANIOS.put("84", "Corrugaciones");
        NOMBRES_DANIOS.put("85", "Polvo");
        NOMBRES_DANIOS.put("86", "Baches / Huecos");
        NOMBRES_DANIOS.put("87", "Ahuellamiento / Surcos");
        NOMBRES_DANIOS.put("88", "Agregado Suelto");
        NOMBRES_DANIOS.put("89", "Cabezas Duras");
        NOMBRES_DANIOS.put("90", "Probabilidad de Derrumbes");
        NOMBRES_DANIOS.put("91", "Daños en la Banca");

        DANIOS_PERMANECEN_POR_MEJORAS.add("82");
        DANIOS_PERMANECEN_POR_MEJORAS.add("83");
        DANIOS_PERMANECEN_POR_MEJORAS.add("90");
        DANIOS_PERMANECEN_POR_MEJORAS.add("91");
    }
}
