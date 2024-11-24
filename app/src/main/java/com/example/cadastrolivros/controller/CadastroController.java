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

    public String salvarLivro(String nomeLivro, String autorLivro, String editora, String descrLivro, String anoPubli, String generoLivro) {
        try {
            if (TextUtils.isEmpty(nomeLivro)) {
                return "Informe o NOME do livro";
            }
            if (TextUtils.isEmpty(autorLivro)) {
                return "Informe o AUTOR do livro";
            }
            if (TextUtils.isEmpty(editora)) {
                return "Informe a EDITORA do livro";
            }
            if (TextUtils.isEmpty(descrLivro)) {
                return "Informe a DESCRIÇÃO do livro";
            }
            if (TextUtils.isEmpty(anoPubli)) {
                return "Informe o ANO DE PUBLICAÇÃO do livro";
            }
            if (TextUtils.isEmpty(generoLivro)) {
                return "Informe o GÊNERO do livro";
            }


            Livro livro = new Livro();
            livro.setNomeLivro(nomeLivro);
            livro.setAutor(autorLivro);
            livro.setEditora(editora);
            livro.setDescrLivro(descrLivro);
            livro.setAnoPubli(Integer.parseInt(anoPubli));
            livro.setGenero(generoLivro);

            long id = CadastroDao.getInstance(context).insert(livro);
            if(id>0) {
                return "Livro cadastrado com sucesso!";
            }else {
                return "Não foi possível gravar os dados do livro." +
                        " Solicite suporte técnico.";
            }

        } catch (Exception ex) {
            Log.e("Cadastro",
                    "Erro salvarLivro(): " + ex.getMessage());
        }
        return "Erro ao gravar dados do Livro. " +
                "Solicite suporte técnico.";

    }

    public ArrayList<Livro> retornarTodosOsLivros(){
        return CadastroDao.getInstance(context).getAll();
    }
}
