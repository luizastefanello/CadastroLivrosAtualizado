package com.example.cadastrolivros.controller;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.cadastrolivros.dao.CadastroDao;
import com.example.cadastrolivros.model.Livro;

import java.util.ArrayList;

public class CadastroController {

    private Context context;

    public CadastroController(Context context){
        this.context = context;
    }

    public String salvarLivro(String nomeLivro, String autorLivro, String generoLivro) {
        try {
            if (TextUtils.isEmpty(nomeLivro)) {
                return "Informe o NOME do livro";
            }
            if (TextUtils.isEmpty(autorLivro)) {
                return "Informe o AUTOR do livro";
            }
            if (TextUtils.isEmpty(generoLivro)) {
                return "Informe o GÊNERO do livro";
            }

            Livro livro = new Livro();
            livro.setNomeLivro(nomeLivro);
            livro.setAutor(autorLivro);
            livro.setGenero(generoLivro);

            long id = CadastroDao.getInstance(context).insert(livro);
            return "Livro cadastrado com sucesso!";

        } catch (Exception ex) {
            Log.e("Unipar",
                    "Erro salvarLivro(): " + ex.getMessage());
        }
        return "Erro ao gravar dados do Livro. " +
                "Solicite suporte técnico.";

    }

    public ArrayList<Livro> retornarTodosOsLivros(){
        return CadastroDao.getInstance(context).getAll();
    }
}
