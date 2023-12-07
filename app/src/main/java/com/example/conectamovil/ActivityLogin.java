package com.example.conectamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText tEmail, tPassword;
    private TextView registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mAuth = FirebaseAuth.getInstance();
        tEmail = findViewById(R.id.editTextEmail);
        tPassword = findViewById(R.id.editTextPassword);
        registro =findViewById(R.id.Registro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al hacer clic en el TextView de registro, iniciar la actividad de registro
                Intent intent = new Intent(ActivityLogin.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(View view) {
        String email = tEmail.getText().toString();
        String password = tPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivityLogin.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ActivityLogin.this, ActivityMenu.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ActivityLogin.this, "Inicio de sesión fallido: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
