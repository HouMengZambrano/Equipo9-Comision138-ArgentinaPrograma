package org.example;

public class Equipo {
    private String nombre;
    private String description;

    public Equipo(String nombre) {
        this.nombre = nombre;
    }
    public Equipo(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + "}";
    }
}

