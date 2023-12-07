package com.example.conectamovil.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conectamovil.R;
import com.example.conectamovil.modelo.agenda;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class AgendaAdap extends FirestoreRecyclerAdapter<agenda, AgendaAdap.ViewHolder> implements Filterable {

    private ArrayList<agenda> listaOriginal; // Cambiado a List en lugar de ArrayList
    private ArrayList<agenda> listaFiltrada; // Cambiado a List en lugar de ArrayList

    public AgendaAdap(@NonNull FirestoreRecyclerOptions<agenda> options) {
        super(options);
        listaOriginal = new ArrayList<>();
        listaFiltrada = new ArrayList<>();
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull agenda model) {
        holder.nombre.setText(model.getNombre());
        holder.apellido.setText(model.getApellido());
        Log.d("Adapter", "onBindViewHolder: " + model.getNombre() + " " + model.getApellido());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contacto_single, parent, false);
        Log.d("Adapter", "onCreateViewHolder");
        return new ViewHolder(v);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<agenda> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) { // Cambiado a 0
                    filteredList.addAll(listaOriginal);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (agenda item : listaOriginal) {
                        if (item.getNombre().toLowerCase().contains(filterPattern)
                                || item.getApellido().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                Log.d("Adapter", "performFiltering: Filtro aplicado, resultados: " + filteredList.size());
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaFiltrada.clear();
                listaFiltrada.addAll((List<agenda>) results.values);
                notifyDataSetChanged();
                Log.d("Adapter", "publishResults: Lista filtrada actualizada");
            }
        };
    }

    public void clear() {
        listaOriginal.clear();
        listaFiltrada.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<agenda> filteredContacts) {
        listaOriginal.addAll(filteredContacts);
        listaFiltrada.addAll(filteredContacts);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, apellido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.Nombre);
            apellido = itemView.findViewById(R.id.apellido);
        }
    }
    public void updateOptions(@NonNull FirestoreRecyclerOptions<agenda> options) {
        if (options != null) {
            listaOriginal.clear();
            listaOriginal.addAll(options.getSnapshots());
            listaFiltrada.clear();
            listaFiltrada.addAll(options.getSnapshots());
        }
        super.updateOptions(options);
    }

}

