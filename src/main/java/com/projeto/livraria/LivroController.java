package com.projeto.livraria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import com.projeto.livraria.repository.CategoriaRepository;
import com.projeto.livraria.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private static final String UPLOAD_DIR = "C:/livraria-uploads/img";    


    @GetMapping
    public String listarLivros(Model model){
        model.addAttribute("listaDeLivros", livroRepository.findAll());
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model){
        model.addAttribute("livro", new Livro());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "livros/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable("id") Long id, Model model){
        Livro livro = livroRepository.findById(id).orElse(null);

        if(livro == null){
            return "redirect:/livros";
        }
        
        model.addAttribute("livro", livro);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "livros/cadastro";
    }

    @GetMapping("/{id}")
    public String exibirDetalhes(@PathVariable("id") Long id, Model model){
        Livro livro = livroRepository.findById(id).orElse(null);

        if(livro == null){
            return "redirect:/livros";
        }
        model.addAttribute("livro", livro);
        return "livros/detalhes";
    }

    

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro,
                              @RequestParam("file") MultipartFile file){
        
        if (livro.getCategoria() != null && livro.getCategoria().getId() != null) {
            categoriaRepository.findById(livro.getCategoria().getId())
            .ifPresent(livro::setCategoria);
        }

        if (!file.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                
                Files.copy(file.getInputStream(), filePath);

                livro.setCapaUrl("/img/" + fileName); 

            } catch (IOException e) {
                livro.setCapaUrl("/img/default.png"); 
            }
        } else if (livro.getId() != null) {
            Livro livroExistente = livroRepository.findById(livro.getId()).orElse(null);
            if (livroExistente != null) {
                livro.setCapaUrl(livroExistente.getCapaUrl());
            }
        }
        
        
        livroRepository.save(livro); 
        return "redirect:/livros";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable("id") Long id){
        livroRepository.deleteById(id);
        return "redirect:/livros";
    }


    
}
