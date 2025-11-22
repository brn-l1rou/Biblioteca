package com.projeto.livraria.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.livraria.model.Categoria;
import com.projeto.livraria.model.CategoriaDAO;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaDAO categoriaDAO;

    public void inserirCategoria(Categoria categoria){
        categoriaDAO.inserirCategoria(categoria);
    }

    public void atualizarCategoria(Categoria categoria){
        categoriaDAO.atualizarCategoria(categoria);
    }

    public void deletarCategoria(Long id){
        categoriaDAO.deletarCategoria(id);
    }

    public ArrayList<Categoria> listarCategorias(){
        return categoriaDAO.listar();
    }

    public Categoria obterCategoria(Long id){
        return categoriaDAO.obterCategoria(id);
    }
}
