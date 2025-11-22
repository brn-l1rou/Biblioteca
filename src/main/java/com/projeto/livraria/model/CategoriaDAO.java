package com.projeto.livraria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class CategoriaDAO {
    
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirCategoria(Categoria categoria){
        String sql = "INSERT INTO categoria (nome) VALUES (?)";
        jdbc.update(sql, categoria.getNome());
    }

    public ArrayList<Categoria> listar(){
        String sql = "SELECT * FROM categoria ORDER BY nome";
        List<Map<String, Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterCategorias(mapa);
    }

    public Categoria obterCategoria(Long id){
        String sql = "SELECT * FROM categoria WHERE id = ?";
        Map<String, Object> mapa = jdbc.queryForMap(sql, id);

        Long idCategoria = ((Number) mapa.get("id")).longValue();
        String nome = (String) mapa.get("nome");
        return new Categoria(idCategoria, nome);
    }

    public void atualizarCategoria(Categoria categoria){
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
        Object[] obj = new Object[2];
        obj[0] = categoria.getNome();
        obj[1] = categoria.getId();
        jdbc.update(sql, obj);

    }

    public void deletarCategoria(Long id){
        String sql = "DELETE FROM categoria WHERE id = ?";
        jdbc.update(sql, id);
    }
}
