package co.edu.udea.mantpriorivias.entidades;

import java.io.Serializable;

public class Unidad implements Serializable {

    private String unidad;
    private String nombre;

    public Unidad(String unidad, String nombre) {
        this.unidad = unidad;
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
