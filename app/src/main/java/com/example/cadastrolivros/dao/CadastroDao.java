package com.example.cadastrolivros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[0], obj.getNomeLivro());
            values.put(colunas[1], obj.getAutor());
            values.put(colunas[2], obj.getEditora());
            values.put(colunas[3], obj.getDescrLivro());
            values.put(colunas[4], obj.getAnoPubli());
            values.put(colunas[5], obj.getGenero());


            return baseDados.insert(tabela,null,values);
        }catch (SQLException ex){
            Log.e("CADASTRO", "ERRO: CadastroDao.insert() " + ex.getMessage());
        }

        return 0;
    }


    @Override
    public long update(Livro obj) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], obj.getAutor());

            String[]identificador = {String.valueOf(obj.getNomeLivro())};

            return baseDados.update(tabela, values, colunas[0]+ "= ?", identificador);

        }catch (SQLException ex){
            Log.e("CADASTRO", "Erro: CadastroDao.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Livro obj) {
        try{
            String[]identificador = {String.valueOf(obj.getNomeLivro())};

            return baseDados.delete(tabela, colunas[0]+ "= ?", identificador);

        }catch (SQLException ex){
            Log.e("CADASTRO", "Erro: CadastroDao.delete() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Livro> getAll() {
        ArrayList list = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela, colunas, null, null, null, null, colunas[0]);

            while (cursor.moveToNext()) {
                Livro livro = new Livro();
                livro.setNomeLivro(cursor.getString(0));
                livro.setAutor(cursor.getString(1));
                livro.setEditora(cursor.getString(2));
                livro.setDescrLivro(cursor.getString(3));
                livro.setAnoPubli(cursor.getInt(4));
                livro.setGenero(cursor.getString(5));




                list.add(livro);
            }

            cursor.close();
            return list;
        } catch (SQLException ex) {
            Log.e("CADASTRO",
                    "ERRO: LivroDao.getAll() " +
                            ex.getMessage());
        }
        return null;
    }

    @Override
    public Livro getById( int id){
        try {
            String[] identificador =
                    {String.valueOf(id)};

            Cursor cursor = baseDados.query(tabela,
                    colunas, colunas[0] + " = ?",
                    identificador,
                    null, null, null);

            if (cursor.moveToFirst()) {
                Livro livro = new Livro();
                livro.setNomeLivro(cursor.getString(0));
                livro.setAutor(cursor.getString(1));
                livro.setEditora(cursor.getString(2));
                livro.setDescrLivro(cursor.getString(3));
                livro.setAnoPubli(cursor.getInt(4));
                livro.setGenero(cursor.getString(5));

                cursor.close();
                return livro;
            }
        } catch (SQLException ex) {
            Log.e("CADASTRO",
                    "ERRO: LivroDao.getById() " +
                            ex.getMessage());
        }
        return null;
    }
    //agora aqui seria para inserir os dados
}
