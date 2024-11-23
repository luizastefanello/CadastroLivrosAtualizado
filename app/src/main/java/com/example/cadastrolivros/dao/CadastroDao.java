package com.example.cadastrolivros.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.cadastrolivros.helper.SQLiteDataHelper;
import com.example.cadastrolivros.model.Livro;

import java.util.ArrayList;

public class CadastroDao implements IGenericDao<Livro>{
    //Abrir a conexão com o banco
    private SQLiteDataHelper openHelper;

    //Base de dados
    private SQLiteDatabase baseDados;

    //Nome das colunas
    private String[]colunas = {"NOME", "AUTOR", "EDITORA", "ANO", "GENERO","DESCRICAO"};

    //Nome da tabela
    private String tabela = "LIVROS";

    private Context context;

    private static CadastroDao instancia;

    public static CadastroDao getInstance(Context context){
        if(instancia == null){
            instancia = new CadastroDao(context);
        }
        return instancia;
    }

    private CadastroDao(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "CADASTROLIVROS_BD", null, 1);

        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Livro obj) {
        return 0;
    }

    @Override
    public long update(Livro obj) {
        return 0;
    }

    @Override
    public long delete(Livro obj) {
        return 0;
    }

    @Override
    public ArrayList<Livro> getAll() {
        return null;
    }

    @Override
    public Livro getById(int id) {
        return null;
    }

    //agora aqui seria para inserir os dados
}
