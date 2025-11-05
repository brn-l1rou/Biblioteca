package com.projeto.livraria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.livraria.model.Categoria;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    private List<Categoria> categorias = new ArrayList<>();
    private long nextId = 4L;

    {
        categorias.add(new Categoria(1L, "Ficção Científica"));
        categorias.add(new Categoria(2L, "Romance Histórico"));
        categorias.add(new Categoria(3L, "Autoajuda"));
    }

    @GetMapping
    public String listarCategorias(Model model){
        model.addAttribute("listaDeCategorias", categorias);
        return "categorias/lista";
    }

    @GetMapping("/nova")
    public String exibirFormularioCadastro(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categorias/cadastro";
    }

    @PostMapping
    public String salvarCategoria(Categoria categoria){
        if (categoria.getId() == null || categoria.getId() == 0){
            categoria.setId(nextId++);
            categorias.add(categoria);
        } else{
            categorias.stream()
                      .filter(c -> c.getId().equals(categoria.getId()))
                      .findFirst()
                      .ifPresent(c -> c.setNome(categoria.getNome()));
        }
        return "redirect:/categorias";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable("id") Long id){
        categorias = categorias.stream()
                               .filter(c -> !c.getId().equals(id))
                               .collect(Collectors.toList());
        return "redirect:/categorias";
    }
}
