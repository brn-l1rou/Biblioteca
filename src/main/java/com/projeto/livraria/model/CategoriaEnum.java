package com.projeto.livraria.model;

public enum CategoriaEnum {
    FICCAO("Ficção"),
    ROMANCE("Romance"),
    ACADEMICO("Acadêmico"),
    BIOGRAFIA("Biografia"),
    FANTASIA("Fantasia");

    private final String descricao;

    CategoriaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    
}
