package com.example.conectamovil;

public class DatosUsuario {
    private String nombre;
        private String apellido;
        private String email;
        private String usuario;
        private String imageUrl;

        // Constructor, getters y setters
        // ...

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }


        public DatosUsuario() {
            // Constructor vacío necesario para Firebase
        }

        public DatosUsuario(String nombre, String apellido, String email, String usuario, String imageUrl) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.usuario = usuario;
            this.imageUrl = imageUrl;
        }
}