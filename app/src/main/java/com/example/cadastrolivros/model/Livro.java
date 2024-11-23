package com.example.cadastrolivros.model;

public class Livro {
    private  String nomeLivro;
    private String autor;
    private String editora;
    private int anoPubli;
    private String descrLivro;
    private String genero;

    public Livro(){

    }

    public String getNomeLivro(){return nomeLivro;}

    public void setNomeLivro(String nomeLivro) {this.nomeLivro = nomeLivro;}

    public String getAutor(){return autor;}

    public void setAutor(String autor) {this.autor = autor;}

    public String getEditora(){return editora;}

    public void setEditora(String editora){this.editora = editora;}

    public int getAnoPubli(){return anoPubli;}

    public void setAnoPubli(int anoPubli){this.anoPubli = anoPubli;}

    public String getDescrLivro(){return descrLivro;}

    public void setDescrLivro(String descrLivro){this.descrLivro = descrLivro;}

    public String getGenero(){return genero;}

    public void setGenero(String genero){this.genero = genero;}
}
