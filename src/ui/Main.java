package ui;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import service.PessoaService;
import service.FilmeService;
import model.Filme;
import model.Ator;
import model.Diretor;


public class Main{
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        FilmeService filmeService = new FilmeService();
        PessoaService pessoaService = new PessoaService();

        boolean sistemaRodando = true;

        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("=========================================");
        System.out.println("       🎬 BEM-VINDO AO SER_MAIS_FLIX 🎬    ");
        System.out.println("=========================================");

        while (sistemaRodando){

            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Cadastrar Filme");
            System.out.println("2 - Cadastrar Ator/Atriz)");
            System.out.println("3 - Cadastrar Diretor/Diretora)");
            System.out.println("4 - Buscar Filme por Nome");
            System.out.println("5 - Associar ator/atriz, diretor(a) a um filme");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1: {
                    System.out.println("\n--- CADASTRAR NOVO FILME ---");

                    System.out.print("Nome do Filme: ");
                    String nome = scanner.nextLine();

                    System.out.print("Data de Lançamento (dd/MM/yyyy): ");
                    String dataTexto = scanner.nextLine();
                    LocalDate dataLancamento = LocalDate.parse(dataTexto, formatadorData);

                    System.out.print("Orçamento (Ex: 150000.50): ");
                    double orcamento = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Breve descrição: ");
                    String descricao = scanner.nextLine();

                    Filme novoFilme = new Filme(nome, dataLancamento, orcamento, descricao);

                    filmeService.cadastrarFilme(novoFilme);

                    break;
                }
                    case 2: {
                        System.out.println("\n--- CADASTRAR ATOR/ATRIZ ---");

                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        LocalDate dataNascimento = null;
                        while (dataNascimento == null){
                            System.out.print("Data de nascimento (dd/MM/yyyy): ");
                            String dataNascimentoTexto = scanner.nextLine();
                            try{
                                dataNascimento = LocalDate.parse(dataNascimentoTexto, formatadorData);
                            } catch(DateTimeParseException e){
                                System.out.println("(Formato inválido! Por favor, utilize o formato dd/MM/yyyy.");
                            }
                        }

                        System.out.print("Nacionalidade: ");
                        String nacionalidade = scanner.nextLine();

                        System.out.print("Cachê: R$ ");
                        double cache = scanner.nextDouble();
                        scanner.nextLine();

                        Ator novoAtor = new Ator(nome, dataNascimento, nacionalidade, cache);

                        pessoaService.cadastrarAtor(novoAtor);

                        break;
                    }
                case 3: {
                    System.out.println("\n--- CADASTRAR DIRETOR(A) ---");

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    LocalDate dataNascimento = null;
                    while (dataNascimento == null){
                        System.out.print("Data de nascimento (dd/MM/yyyy): ");
                        String dataNascimentoTexto = scanner.nextLine();
                        try{
                            dataNascimento = LocalDate.parse(dataNascimentoTexto, formatadorData);
                        } catch(DateTimeParseException e){
                            System.out.println("(Formato inválido! Por favor, utilize o formato dd/MM/yyyy.");
                        }
                    }

                    System.out.print("Nacionalidade: ");
                    String nacionalidade = scanner.nextLine();

                    System.out.print("Estilo:  ");
                    String estilo = scanner.nextLine();

                    Diretor novoDiretor = new Diretor(nome, dataNascimento, nacionalidade, estilo);

                    pessoaService.cadastrarDiretor(novoDiretor);

                    break;
                }
                case 4: {
                    System.out.println("\n--- BUSCAR FILME ---");
                    System.out.print("Digite o nome do filme que deseja buscar: ");
                    String nomeBusca = scanner.nextLine();

                    Filme filmeEncontrado = filmeService.buscarFilmePorNome(nomeBusca);

                    if (filmeEncontrado != null) {
                        System.out.println("\n🎬 FICHA TÉCNICA: " + filmeEncontrado.getNome().toUpperCase());
                        System.out.println("Lançamento: " + filmeEncontrado.getDataLancamento());
                        System.out.println("Orçamento: R$ " + filmeEncontrado.getOrcamento());
                        System.out.println("Descrição: " + filmeEncontrado.getDescricao());


                        System.out.print("Direção: ");
                        if (filmeEncontrado.getDiretor() != null) {
                            System.out.println(filmeEncontrado.getDiretor().getNome());
                        } else {
                            System.out.println("Ainda não definido.");
                        }

                        System.out.println("Elenco:");
                        if (filmeEncontrado.getAtores().isEmpty()) {
                            System.out.println("  - Nenhum ator/atriz associado(a) ainda.");
                        } else {

                            for (Ator atorDaLista : filmeEncontrado.getAtores()) {
                                System.out.println("  - " + atorDaLista.getNome());
                            }
                        }
                        System.out.println("-----------------------------------------");
                    }

                    break;
                }

                case 5: {
                    System.out.println("\n--- ASSOCIAR ELENCO/DIREÇÃO ---");

                    System.out.print("Digite o nome do Filme: ");
                    String nomeFilme = scanner.nextLine();
                    Filme filmeEncontrado = filmeService.buscarFilmePorNome(nomeFilme);

                    if (filmeEncontrado == null) {
                        break;
                    }

                    System.out.println("\nQuem você deseja associar a este filme?");
                    System.out.println("1 - Ator/Atriz");
                    System.out.println("2 - Diretor/Diretora");
                    System.out.print("Opção: ");
                    int tipoPessoa = scanner.nextInt();
                    scanner.nextLine(); // Limpando o Enter fantasma!

                    if (tipoPessoa == 1) {
                        System.out.print("Digite o NOME do Ator/Atriz: ");
                        String nomeAtor = scanner.nextLine(); // Pede o nome em vez do ID
                        Ator atorEncontrado = pessoaService.buscarAtorPorNome(nomeAtor);

                        if (atorEncontrado != null) {
                            filmeService.associarAtorAoFilme(filmeEncontrado, atorEncontrado);
                        }
                    } else if (tipoPessoa == 2) {
                        System.out.print("Digite o NOME do Diretor/Diretora: ");
                        String nomeDiretor = scanner.nextLine(); // Pede o nome em vez do ID
                        Diretor diretorEncontrado = pessoaService.buscarDiretorPorNome(nomeDiretor);

                        if (diretorEncontrado != null) {
                            filmeService.associarDiretorAoFilme(filmeEncontrado, diretorEncontrado);
                        }
                    } else {
                        System.out.println("⚠️ Opção de associação inválida!");
                    }

                    break;
                }
                case 0:
                    System.out.println("\nEncerrando o SerMaisFlix... Até logo! 🍿");
                    sistemaRodando = false;
                    break;


                default:
                    System.out.println("\n⚠️ Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}







