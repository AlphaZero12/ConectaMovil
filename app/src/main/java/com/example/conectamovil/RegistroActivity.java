package com.example.conectamovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etNombre, etCorreo, etContraseña, etConfirmarClave;
    private Button btnRegistrarUsuario;
    private TextView tvTengoUnaCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        etNombre = findViewById(R.id.NombreRG);
        etCorreo = findViewById(R.id.CorreoRG);
        etContraseña = findViewById(R.id.ContraseñaRG);
        etConfirmarClave = findViewById(R.id.ConfirmarClave);
        btnRegistrarUsuario = findViewById(R.id.RegistrarUsuario);
        tvTengoUnaCuenta = findViewById(R.id.TengoUnaCuenta);

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        tvTengoUnaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irALogin();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();
        String contraseña = etContraseña.getText().toString();
        String confirmarClave = etConfirmarClave.getText().toString();

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmarClave.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que las contraseñas coincidan
        if (!contraseña.equals(confirmarClave)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registrar el usuario en Firebase Authentication
        mAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistroActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                            // Aquí puedes agregar más lógica después de registrar al usuario, si es necesario
                        } else {
                            Toast.makeText(RegistroActivity.this, "Error al registrar el usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void irALogin() {
        Intent intent = new Intent(RegistroActivity.this, ActivityLogin.class);
        startActivity(intent);
        finish(); // Esto evita que el usuario pueda volver atrás con el botón de retroceso
    }
}
