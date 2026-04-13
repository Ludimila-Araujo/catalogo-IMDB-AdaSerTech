package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filme {

    //atributos:

    private static Long idCounter = 1L;
    private final Long id;
    private String nome;
    private LocalDate dataLancamento;
    private double orcamento;
    private String descricao;
    private Diretor diretor;

    private List<Ator> atores = new ArrayList<>();

    //construtor:

    public Filme (String nome, LocalDate dataLancamento, double orcamento, String descricao) {
        this.id = idCounter++;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.descricao = descricao;

    }

    //getters e setters:

    public Long getId() {
        return id;
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
