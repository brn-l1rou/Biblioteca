package com.projeto.livraria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conversao {
    public static ArrayList<Categoria> converterCategorias(List<Map<String, Object>> mapa){
        ArrayList<Categoria> categorias = new ArrayList<>();
        for (Map<String, Object> registro : mapa){
            Long id = ((Number) registro.get("id")).longValue();
            String nome = (String) registro.get("nome");
            categorias.add(new Categoria(id, nome));
        }
        return categorias;
    }    

    public static ArrayList<Livro> converterLivros(List<Map<String, Object>> mapa, CategoriaDAO cdao){
        ArrayList<Livro> livros = new ArrayList<>();
        for (Map<String, Object> registro : mapa){
            livros.add(converterLivro(registro, cdao));
        }
        return livros;
    }

    public static Livro converterLivro(Map<String, Object> registro, CategoriaDAO cdao){
        Livro livro = new Livro();

        livro.setId(((Number) registro.get("id")).longValue());
        livro.setTitulo((String) registro.get("titulo"));
        livro.setAutor((String) registro.get("autor"));
        livro.setAnoPublicacao((Integer) registro.get("ano_publicacao"));
        livro.setEstoque((Integer) registro.get("estoque"));
        livro.setCapaUrl((String) registro.get("capa_url"));

        Long categoriaId = ((Number) registro.get("categoria_id")).longValue();
        String categoriaNome = (String) registro.get("nome_categoria");
        
        Categoria categoria = new Categoria(categoriaId, categoriaNome);
        livro.setCategoria(categoria);

        return livro;
    }
}


