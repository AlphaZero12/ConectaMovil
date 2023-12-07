package com.example.conectamovil.modelo;

public class agenda {
    private String nombre;
    private String apellido;

    // Constructor sin argumentos requeridos para Firestore
    public agenda() {}

    // Constructor con argumentos
    public agenda(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
