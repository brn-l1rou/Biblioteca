package com.projeto.livraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projeto.livraria.model.Categoria;
import com.projeto.livraria.service.CategoriaService;
import org.springframework.core.convert.converter.Converter;

@Component
public class CategoriaConverter implements Converter<String, Categoria>{
    
    @Autowired
    private CategoriaService categoriaService;

    @Override
    public Categoria convert(String idString){
        if (idString == null || idString.isEmpty()){
            return null;
        }

        try{
            Long id = Long.valueOf(idString);
            return categoriaService.obterCategoria(id);
        } catch(NumberFormatException e){
            return null;
        }
    }


}
