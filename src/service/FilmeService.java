package service;

import java.util.ArrayList;
import java.util.List;
import model.Filme;

public class FilmeService {

    private final List<Filme> filmes;

    public FilmeService() {
        this.filmes = new ArrayList<>();
    }

    public void cadastrarFilme(Filme filme) {
        filmes.add(filme);
        System.out.println("\nFilme cadastrado com sucesso: " + filme.getNome());
    }
}
