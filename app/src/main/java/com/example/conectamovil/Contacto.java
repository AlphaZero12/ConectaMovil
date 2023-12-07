package com.example.conectamovil;

public class Contacto {
    private String nombre;
    private String correo;

    public Contacto() {
        // Constructor vacío necesario para Firebase
    }

    public Contacto(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
