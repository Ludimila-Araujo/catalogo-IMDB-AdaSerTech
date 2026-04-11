package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filme {

    //atributos:

    private Long id;
    private String nome;
    private LocalDate dataLancamento;
    private double orcamento;
    private String descricao;
    private Diretor diretor;

    private List<Ator> atores = new ArrayList<>();

    //construtor:

    public Filme (Long id, String nome, LocalDate dataLancamento, double orcamento, String descricao) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.descricao = descricao;

    }

    //getters e setters:

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    //métodos: métodos visualizados dentor do diagrama UML

    public void definirDiretor(Diretor diretor){
        this.diretor = diretor;
    }

    public void adicionarAtor(Ator ator){
        this.atores.add(ator);
    }

    //método para evitar que um orçamento com valor negativo seja inserido no sistema

    public void setOrcamento(double orcamento) {
        if (orcamento < 0) {
            System.out.println("Erro: O orçamento do filme não pode ser negativo.");
        } else {
            this.orcamento = orcamento;
        }
    }
}
