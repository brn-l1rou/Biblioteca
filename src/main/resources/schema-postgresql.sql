-- Garante que as tabelas sejam removidas antes de recriá-las (para facilitar o desenvolvimento)
DROP TABLE IF EXISTS livro;
DROP TABLE IF EXISTS categoria;

CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    ano_publicacao INTEGER NOT NULL,
    estoque INTEGER NOT NULL,
    capa_url VARCHAR(255),
    categoria_id INTEGER NOT NULL REFERENCES categoria(id) 
);
