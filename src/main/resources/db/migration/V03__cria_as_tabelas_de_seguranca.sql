BEGIN;

CREATE TABLE public.usuario
(
    codigo bigserial NOT NULL,
    nome text,
    nome_usuario text,
    senha text,
    ativo boolean,
    PRIMARY KEY (codigo)
);

CREATE TABLE public.papel
(
    codigo bigserial NOT NULL,
    nome text,
    PRIMARY KEY (codigo)
);

CREATE TABLE public.usuario_papel
(
    codigo_usuario bigint NOT NULL,
    codigo_papel bigint NOT NULL
);

ALTER TABLE public.usuario_papel
    ADD FOREIGN KEY (codigo_usuario)
    REFERENCES public.usuario (codigo)
    NOT VALID;


ALTER TABLE public.usuario_papel
    ADD FOREIGN KEY (codigo_papel)
    REFERENCES public.papel (codigo)
    NOT VALID;

INSERT INTO usuario (nome, nome_usuario, senha, ativo) VALUES
('Ana','ana', '$argon2d$v=19$m=12,t=3,p=1$YnExemR3OXRqYjcwMDAwMA$TILnWYiJoCOfeCNAIIQtDA', true),
('João','joao', '$argon2d$v=19$m=12,t=3,p=1$YnExemR3OXRqYjcwMDAwMA$TILnWYiJoCOfeCNAIIQtDA', true),
('Jorge','jorge', '$argon2d$v=19$m=12,t=3,p=1$YnExemR3OXRqYjcwMDAwMA$TILnWYiJoCOfeCNAIIQtDA', true),
('Sheila','sheila', '$argon2d$v=19$m=12,t=3,p=1$YnExemR3OXRqYjcwMDAwMA$TILnWYiJoCOfeCNAIIQtDA', true),
('Grosbilda','grosbilda', '$argon2d$v=19$m=12,t=3,p=1$YnExemR3OXRqYjcwMDAwMA$TILnWYiJoCOfeCNAIIQtDA', true),
('Estrobilobaldo','estrobilobaldo', '$argon2d$v=19$m=12,t=3,p=1$YnExemR3OXRqYjcwMDAwMA$TILnWYiJoCOfeCNAIIQtDA', true);

INSERT INTO papel (codigo, nome) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_SECRETARIA'),
(3, 'ROLE_PROFESSOR');

INSERT INTO usuario_papel (codigo_usuario, codigo_papel) VALUES
(1, 2),
(1, 3),
(2, 2),
(3, 3),
(4, 2),
(5, 3),
(6, 1);

END;