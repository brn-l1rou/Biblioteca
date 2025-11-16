package com.projeto.livraria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String autor;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    private Integer estoque;

    @Column(name = "capa_url")
    private String capaUrl;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable  = false)
    private Categoria categoria;

    public Livro(){}
    
    public Livro(Long id, String titulo, String autor, Categoria categoria, Integer anoPublicacao, Integer estoque, String capaUrl) {
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.estoque = estoque;
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.capaUrl = capaUrl;
    }

    

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public String getCapaUrl(){
        return capaUrl;
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

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void setCapaUrl(String capaUrl){
        this.capaUrl = capaUrl;
    }    

}
