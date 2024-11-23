package com.example.cadastrolivros.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastrolivros.R;
import com.example.cadastrolivros.model.Livro;

import java.util.ArrayList;

public class CadastroListAdapter extends RecyclerView.Adapter<CadastroListAdapter.ViewHolder>{

    private ArrayList<Livro> listaLivros;
    private Context context;

    public CadastroListAdapter(ArrayList<Livro> listaLivros, Context context){
        this.listaLivros = listaLivros;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View listItem = inflater.inflate(R.layout.item_list_livro, parent, false);

        return new ViewHolder(listItem);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Livro livro = listaLivros.get(position);
        holder.tvNome.setText(String.valueOf(livro.getNomeLivro()));
        holder.tvAutor.setText(String.valueOf(livro.getAutor()));
        holder.TvGenero.setText(String.valueOf(livro.getGenero()));
    }

    public int getItemCount() {
        return listaLivros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNome, tvAutor, TvGenero;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            this.tvNome = itemView.findViewById(R.id.tvNome);
            this.tvAutor = itemView.findViewById(R.id.tvAutor);
            this.TvGenero = itemView.findViewById(R.id.tvGenero);
        }
    }

}
