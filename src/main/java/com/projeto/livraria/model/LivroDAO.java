package com.projeto.livraria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
@DependsOn("jdbcTemplate")
public class LivroDAO {
    
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @Autowired
    CategoriaDAO categoriaDAO;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirLivro(Livro livro){
        String sql = "INSERT INTO livro (titulo, autor, ano_publicacao, estoque, capa_url, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] obj = new Object[6];

        obj[0] = livro.getTitulo();
        obj[1] = livro.getAutor();
        obj[2] = livro.getAnoPublicacao();
        obj[3] = livro.getEstoque();
        obj[4] = livro.getCapaUrl();
        obj[5] = livro.getCategoria().getId();

        jdbc.update(sql, obj);
    }

    public void atualizarLivro(Livro livro){
        String sql = "UPDATE livro SET titulo=?, autor=?, ano_publicacao=?, estoque=?, capa_url=?, categoria_id=? WHERE id=?";
        Object[] obj = new Object[7];
        obj[0] = livro.getTitulo();
        obj[1] = livro.getAutor();
        obj[2] = livro.getAnoPublicacao();
        obj[3] = livro.getEstoque();
        obj[4] = livro.getCapaUrl();
        obj[5] = livro.getCategoria().getId();
        obj[6] = livro.getId();

        jdbc.update(sql, obj);
    }

    public ArrayList<Livro> listarLivros(){
        String sql = "SELECT l.*, c.nome AS nome_categoria FROM livro l LEFT JOIN categoria c ON l.categoria_id = c.id ORDER BY l.titulo";
        List<Map<String, Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterLivros(mapa, categoriaDAO);
    }

    public Livro obterLivro(Long id){
        String sql = "SELECT l.*, c.nome AS nome_categoria FROM livro l LEFT JOIN categoria c ON l.categoria_id = c.id WHERE l.id = ?";
        Map<String, Object> mapa = jdbc.queryForMap(sql, id);

        return Conversao.converterLivro(mapa, categoriaDAO);
    }

    public void deletarLivro(Long id){
        String sql = "DELETE FROM livro WHERE id = ?";
        jdbc.update(sql, id);
    }
}
