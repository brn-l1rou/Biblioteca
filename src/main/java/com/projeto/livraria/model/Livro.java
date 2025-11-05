package com.projeto.livraria.model;

public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private CategoriaEnum categoria;
    private int anoPublicacao;
    private int estoque;

    public Livro(Long id, String titulo, String autor, CategoriaEnum categoria, int anoPublicacao, int estoque) {
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.estoque = estoque;
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
    }

    public Livro(){}

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }




}
