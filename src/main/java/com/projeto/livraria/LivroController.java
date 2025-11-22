package com.projeto.livraria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.livraria.model.Livro;
import com.projeto.livraria.service.CategoriaService;
import com.projeto.livraria.service.LivroService;


@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private CategoriaService categoriaService;   

    @GetMapping
    public String listarLivros(Model model){
        model.addAttribute("listaDeLivros", livroService.listarLivros());
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model){
        model.addAttribute("livro", new Livro());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "livros/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable("id") Long id, Model model){
        Livro livro = livroService.obterLivro(id);

        if(livro == null){
            return "redirect:/livros";
        }
        
        model.addAttribute("livro", livro);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "livros/cadastro";
    }

    @GetMapping("/{id}")
    public String exibirDetalhes(@PathVariable("id") Long id, Model model){
        Livro livro = livroService.obterLivro(id);

        if(livro == null){
            return "redirect:/livros";
        }
        model.addAttribute("livro", livro);
        return "livros/detalhes";
    }

    

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro,
                              @RequestParam("file") MultipartFile file,
                              Model model){

            try {
                if (livro.getId() == null  || livro.getId() == 0){
                    livroService.inserirLivro(livro, file);
                } else{
                    livroService.atualizarLivro(livro, file);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Erro de Validação: " + e.getMessage());
                model.addAttribute("erro", e.getMessage());
                model.addAttribute("livro", livro);
                model.addAttribute("categorias", categoriaService.listarCategorias());
                return "livros/cadastro";
            }
    
        return "redirect:/livros";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable("id") Long id){
        livroService.deletar(id);
        return "redirect:/livros";
    }


    
}
