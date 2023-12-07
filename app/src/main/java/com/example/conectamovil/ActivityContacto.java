package com.example.conectamovil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conectamovil.adapter.AgendaAdap;
import com.example.conectamovil.modelo.agenda;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ActivityContacto extends AppCompatActivity {

    private EditText txtBuscar;
    private Button btnAgregarContacto;
    private RecyclerView recyclerView;
    private AgendaAdap mAdapter;
    private FirebaseFirestore mFirestore;
    private TextView txtUsuariosFiltrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        mFirestore = FirebaseFirestore.getInstance();
        txtBuscar = findViewById(R.id.txtbuscar);
        recyclerView = findViewById(R.id.recyclerViewContactos);
        txtUsuariosFiltrados = findViewById(R.id.txtUsuariosFiltrados);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CollectionReference query = mFirestore.collection("contacto");
        Query baseQuery = query.orderBy("nombre"); // Ordena por nombre, ajusta según tus necesidades
        FirestoreRecyclerOptions<agenda> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<agenda>()
                .setQuery(baseQuery, agenda.class)
                .build();

        mAdapter = new AgendaAdap(firestoreRecyclerOptions);
        recyclerView.setAdapter(mAdapter);
        btnAgregarContacto = findViewById(R.id.btnAgregarContacto);

        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No se necesita implementar
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String query = charSequence.toString().trim();
                if (!query.isEmpty()) {
                    filterContacts(query);
                } else {
                    loadAllContacts();
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {
                // No se necesita implementar
            }
        });

        btnAgregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityContacto.this, AgregarContactoActivity.class));
            }
        });
    }

    private void filterContacts(String query) {
        Query filteredQuery = mFirestore.collection("contacto")
                .orderBy("nombre")
                .startAt(query)
                .endAt(query + "\uf8ff");

        FirestoreRecyclerOptions<agenda> filteredOptions = new FirestoreRecyclerOptions.Builder<agenda>()
                .setQuery(filteredQuery, agenda.class)
                .build();

        mAdapter.updateOptions(filteredOptions);
    }



    private void loadAllContacts() {
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
