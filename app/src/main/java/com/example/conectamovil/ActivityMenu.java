package com.example.conectamovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Establecer la Toolbar como la action bar de la actividad
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener referencias a los botones
        Button btnIrAPerfilUsuario = findViewById(R.id.btnIrPerfilUsuario);
        Button btnIrAContactos = findViewById(R.id.btnIrContactos);
        Button btnIrMqttt = findViewById(R.id.btnIrMqtt);

        // Configurar listeners para los botones


        btnIrAPerfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAPerfilUsuario();
            }
        });

        btnIrAContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAContactos();
            }
        });



        btnIrMqttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAMqtt();
            }
        });
    }

    // Métodos para abrir las actividades
    private void irAPerfilUsuario() {
        startActivity(new Intent(this, PerfilUsuarioActivity.class));
    }

    private void irAMqtt() {
        startActivity(new Intent(this, activity_chat.class));
    }

    private void irAContactos() {
        startActivity(new Intent(this, activityPrincipal.class));
    }

    private void agregarContactos() {
        startActivity(new Intent(this, AgregarContactoActivity.class));
    }
}
