package service;

import model.Ator;
import model.Diretor;
import model.Filme;
import java.util.ArrayList;
import java.util.List;

public class FilmeService {

    //Listas que guardarão os filmes:

    private List<Filme> filmes = new ArrayList<>();

    //método de cadastro de filmes:

    public void cadastrarFilme(Filme filme){
        filmes.add(filme);
        System.out.println("Filme " + filme.getNome() + " cadastrado com sucesso!");
    }

    //método de busca de filme:

    public Filme buscarFilmePorNome(String nomeFilme){
        for (Filme filmeAtual : filmes){
            if (filmeAtual.getNome().equalsIgnoreCase(nomeFilme)){
                return filmeAtual;
            }
        }
        System.out.println("Filme não encontrado!");
        return null;
    }

    //métodos para associar Diretores e Atores aos filmes:

    public void associarDiretorAoFilme(Filme filme, Diretor diretor){
        filme.definirDiretor(diretor);
        System.out.println("Diretor/Diretora " + diretor.getNome() + " associado(a) ao filme " + filme.getNome());
    }

    public void associarAtorAoFilme(Filme filme, Ator ator){
        filme.adicionarAtor(ator);
        System.out.println("Ator/Atriz " + ator.getNome() + " adicionado(a) ao elenco de " + filme.getNome());
    }
}
