package model;

import java.time.LocalDate;

public abstract class Pessoa {

        //atributos:

        private Long id;
        private String nome;
        private LocalDate dataNascimento;
        private String nacionalidade;

        //construtor:

        public Pessoa(Long id, String nome, LocalDate dataNascimento, String nacionalidade){
            this.id = id;
            this.nome = nome;
            this.dataNascimento = dataNascimento;
            this.nacionalidade = nacionalidade;
        }

        //getters and setters:

        public long getId(){
            return id;
        }

        public void setId(Long id){
            this.id = id;
        }

        public String getNome(){
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        //métodos:


    }

