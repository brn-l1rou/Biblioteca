package com.projeto.livraria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.livraria.model.Categoria;
import com.projeto.livraria.repository.CategoriaRepository;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listarCategorias(Model model){
        model.addAttribute("listaDeCategorias", categoriaRepository.findAll());
        return "categorias/lista";
    }

    @GetMapping("/nova")
    public String exibirFormularioCadastro(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categorias/cadastro";
    }

    @PostMapping
    public String salvarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable("id") Long id){
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable("id") Long id, Model model) {
    
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if (categoria == null) {
            return "redirect:/categorias"; 
        }

        model.addAttribute("categoria", categoria); 
        return "categorias/cadastro"; 
    }
}
