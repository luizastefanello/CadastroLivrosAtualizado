package com.example.cadastrolivros.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastrolivros.R;
import com.example.cadastrolivros.adapter.CadastroListAdapter;
import com.example.cadastrolivros.controller.CadastroController;
import com.example.cadastrolivros.model.Livro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton btAddLivro;
    private CadastroController controller;
    private RecyclerView rvLivros;
    private View viewCadastro;
    private EditText edNome, edAutor, edGenero, edDescricao, edAnoPubli, edEditora;
    private AlertDialog dialog;
    private Button btInfoLivro;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new CadastroController(this);
        rvLivros = findViewById(R.id.rvLivros);
        btAddLivro = findViewById(R.id.btAddLivro);
        btAddLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaLivros();

        btInfoLivro = findViewById(R.id.btInfoLivro);
        btInfoLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LivroActivity.class);

                startActivity(intent);
            }
        });

    }

    private void atualizarListaLivros(){
        ArrayList<Livro> listaLivros = controller.retornarTodosOsLivros();

        CadastroListAdapter adapter = new CadastroListAdapter(listaLivros, this);

        rvLivros.setLayoutManager(new LinearLayoutManager(this));
        rvLivros.setAdapter(adapter);
    }

    private void abrirCadastro(){
        viewCadastro = getLayoutInflater().inflate(R.layout.dialog_cadastro_livro, null);

        edNome = viewCadastro.findViewById(R.id.edNome);
        edAutor = viewCadastro.findViewById(R.id.edAutor);
        edGenero = viewCadastro.findViewById(R.id.edGenero);
        edDescricao = viewCadastro.findViewById(R.id.edDescricao);
        edAnoPubli = viewCadastro.findViewById(R.id.edAnoPubli);
        edEditora = viewCadastro.findViewById(R.id.edEditora);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("CADASTRO LIVRO");
        builder.setView(viewCadastro);
        builder.setCancelable(false);

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("SALVAR", null);

        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                    }
                });
            }
        });
        dialog.show();
    }

    private void salvarDados(){
        String retorno = controller.salvarLivro(edNome.getText().toString(), edAutor.getText().toString(), edEditora.getText().toString(), edDescricao.getText().toString(), edAnoPubli.getText().toString(), edGenero.getText().toString());

        if(retorno.contains("NOME")){
            edNome.setError(retorno);
            edNome.requestFocus();
            return;
        }
        if(retorno.contains("AUTOR")){
            edAutor.setError(retorno);
            edAutor.requestFocus();
            return;
        }
        if(retorno.contains("EDITORA")) {
            edEditora.setError(retorno);
            edEditora.requestFocus();
            return;
        }
        if(retorno.contains("DESCRIÇÃO")) {
            edDescricao.setError(retorno);
            edDescricao.requestFocus();
            return;
        }
        if(retorno.contains("ANO")) {
            edAnoPubli.setError(retorno);
            edAnoPubli.requestFocus();
            return;
        }
        if(retorno.contains("GÊNERO")){
            edGenero.setError(retorno);
            edGenero.requestFocus();
            return;
        }




        if(retorno.contains("sucesso")){
            atualizarListaLivros();
            dialog.dismiss();
        }

        Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem busca = menu.findItem(R.id.busca);
        SearchView editDeBusca = (SearchView) busca.getActionView();
        editDeBusca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("Estado ", "submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("Estado ", "digitando");
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}