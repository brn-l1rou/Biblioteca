package com.projeto.livraria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.livraria.model.CategoriaEnum;
import com.projeto.livraria.model.Livro;

@Controller
public class LivroController {
    private  List<Livro> livros = new ArrayList<>();
    private  Long nextId = 4L;

    {
        livros.add(new Livro(1L, "O Senhor dos Anéis", "J.R.R. Tolkien", CategoriaEnum.FANTASIA, 1954, 15));
        livros.add(new Livro(2L, "Fahreinheit 451", "Ray Bradbury", CategoriaEnum.FICCAO, 1953, 10));
        livros.add(new Livro(3L, "O Fim da Infância", "Arthur C. Clarke", CategoriaEnum.FICCAO, 1953, 7));
    }

    @GetMapping("/livros")
    public String listarLivros(Model model){
        model.addAttribute("listaDeLivros", livros);
        return "livros/lista";
    }

    @GetMapping("/livros/novo")
    public String exibirFormularioCadastro(Model model){
        model.addAttribute("livro", new Livro());
        return "livros/cadastro";
    }

    @PostMapping("/livros")
    public String salvarLivro(Livro livro){
        if(livro.getId() == null || livro.getId() == 0){
            livro.setId(nextId++);
            livros.add(livro);
        } else{
            Livro livroExistente = livros.stream()
                                   .filter(l -> l.getId()
                                   .equals(livro.getId()))
                                   .findFirst().orElse(null);
            if(livroExistente != null){
                livroExistente.setTitulo(livro.getTitulo());
                livroExistente.setAutor(livro.getAutor());
            }
        }
        System.out.println("Livro salvo (simulado): " + livro.getTitulo());
        return "redirect:/livros";
    }

    @GetMapping("livros/excluir/{id}")
    public String excluirLivro(@PathVariable("id") Long id){
        livros = livros.stream()
                 .filter(l -> !l.getId().equals(id))
                 .collect(Collectors.toList());

        return "redirect:/livros";
    }

    @GetMapping("/livros/{id}")
    public String exibirDetalhes(@PathVariable("id") Long id, Model model){
        Livro livro = livros.stream()
                            .filter(l -> l.getId().equals(id))
                            .findFirst()
                            .orElse(null);

        if(livro == null){
            return "redirect:/livros";
        }
        model.addAttribute("livro", livro);
        return "livros/detalhes";
    }

    @GetMapping("/livros/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable("id") Long id, Model model){
        Livro livro = livros.stream()
                            .filter(l -> l.getId().equals(id))
                            .findFirst()
                            .orElse(null);
        if(livro == null){
            return "redirect:/livros";
        }

        model.addAttribute("livro", livro);
        return "livros/cadastro";
    }
    
}
