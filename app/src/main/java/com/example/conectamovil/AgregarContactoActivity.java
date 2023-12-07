package com.example.conectamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarContactoActivity extends AppCompatActivity {
    Button btnAgregarContacto;
    EditText nombre,apellido;
    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
        this.setTitle("Crear Usuario");
        mfirestore = FirebaseFirestore.getInstance();
        nombre=findViewById(R.id.Nombre);
        apellido=findViewById(R.id.Apellido);
        btnAgregarContacto=findViewById(R.id.btnAgregarContacto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        btnAgregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombrecontacto = nombre.getText().toString().trim();
                String apellidocontacto = apellido.getText().toString().trim();
                if (nombrecontacto.isEmpty()&&apellidocontacto.isEmpty()){
                    Toast.makeText(AgregarContactoActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postContacto(nombrecontacto,apellidocontacto);
                }
            }
        });
    }

    private void postContacto(String nombrecontacto, String apellidocontacto) {
        Map<String,Object>map = new HashMap<>();
        map.put("nombre",nombrecontacto);
        map.put("apellido",apellidocontacto);

    mfirestore.collection("contacto").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
            Toast.makeText(AgregarContactoActivity.this, "Creado exitosamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(AgregarContactoActivity.this, "Error al ingresar", Toast.LENGTH_SHORT).show();
        }
    });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}