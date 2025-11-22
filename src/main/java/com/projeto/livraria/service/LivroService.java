package com.projeto.livraria.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.livraria.model.Livro;
import com.projeto.livraria.model.LivroDAO;

@Service
public class LivroService {

    @Autowired
    LivroDAO livroDAO;

    private static final String UPLOAD_DIR = "C:/livraria-uploads/img";

    public ArrayList<Livro> listarLivros(){
        return livroDAO.listarLivros();
    }

    public Livro obterLivro(Long id){
        return livroDAO.obterLivro(id);
    }
    
    public void inserirLivro(Livro livro, MultipartFile file){
        validarLivro(livro);
        processarUpload(livro, file);
        livroDAO.inserirLivro(livro);
    }

    public void atualizarLivro(Livro livro, MultipartFile file){
        validarLivro(livro);
        processarUploadOuManterUrl(livro, file);
        livroDAO.atualizarLivro(livro);
    }

    public void deletar(Long id){
        livroDAO.deletarLivro(id);
    }
    
    private void validarLivro(Livro livro){
        if (livro.getAnoPublicacao() == null || livro.getAnoPublicacao() <= 0){
            throw new IllegalArgumentException("Ano de publicação inválido. Deve ser maior que zero.");
        }

        if (livro.getEstoque() == null || livro.getEstoque() < 0){
            throw new IllegalArgumentException("Estoque inválido. Não pode ser negativo.");
        }
    }

    private void processarUpload(Livro livro, MultipartFile file){
        if (file != null && !file.isEmpty()){
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);

                Files.copy(file.getInputStream(), filePath);
                livro.setCapaUrl("/img/" + fileName);
            } catch (IOException e) {
                System.err.println("Erro de I/O ao tentar salvar imagem: " + e.getMessage());
                livro.setCapaUrl("/img/default.png");
            }
        }else{
            livro.setCapaUrl(null);
        }
    }

    private void processarUploadOuManterUrl(Livro livro, MultipartFile file){
        if (file != null && !file.isEmpty()){
            processarUpload(livro, file);
        }
    }

}
