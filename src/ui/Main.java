package ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import model.*;
import service.FilmeService;
import service.PessoaService;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    static void main() {
        Scanner sc = new Scanner(System.in);
        FilmeService filmeService = new FilmeService();
        PessoaService pessoaService = new PessoaService();
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao = -1;

        System.out.println("\nBem-vindo(a) ao Catálogo de Filmes SerMaisFlix!");

        while (opcao != 0) {
            System.out.println("""

                    --- Menu Principal ---
                    1. Cadastrar Filme
                    2. Cadastrar Ator/Atriz
                    3. Cadastrar Diretor(a)
                    4. Associar Ator/Atriz ao Filme
                    5. Associar Diretor(a) ao Filme
                    6. Buscar por Filme
                    0. Sair
                    """);
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Novo Filme ---");
                    System.out.print("Digite o nome do filme: ");
                    String nome = sc.nextLine();

                    LocalDate dataLancamento = null;
                    while (dataLancamento == null) {
                        System.out.print("Digite a data de lançamento (dd/MM/yyyy): ");
                        String data = sc.nextLine();
                        try {
                            dataLancamento = LocalDate.parse(data, formatadorData);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido! Utilize o formato dd/MM/yyyy.");
                        }
                    }
                    System.out.print("Digite o orçamento do filme: ");
                    double orcamento = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Digite uma breve descrição do filme: ");
                    String descricao = sc.nextLine();
                    Filme novoFilme = new Filme(nome, dataLancamento, orcamento, descricao);
                    filmeService.cadastrarFilme(novoFilme);

                }
                case 2 -> {
                    System.out.println("\n--- Cadastro do(a) Ator/Atriz ---");

                    System.out.print("Digite o nome do(a) ator/atriz: ");
                    String nomeAtor = sc.nextLine();

                    LocalDate dataNascimentoAtor = null;
                    while (dataNascimentoAtor == null) {
                        System.out.print("Digite a data de nascimento do(a) ator/atriz (dd/MM/yyyy): ");
                        String dataNasceAtor = sc.nextLine();
                        try {
                            dataNascimentoAtor = LocalDate.parse(dataNasceAtor, formatadorData);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido! Utilize o formato dd/MM/yyyy.");
                        }
                    }

                    System.out.print("Digite a nacionalidade do(a) ator/atriz: ");
                    String nacionalidadeAtor = sc.nextLine();

                    System.out.print("Digite o cachê do(a) ator/atriz: ");
                    double cacheAtor = sc.nextDouble();
                    sc.nextLine();

                    Ator novoAtor = new Ator(nomeAtor, dataNascimentoAtor, nacionalidadeAtor, cacheAtor);
                    pessoaService.cadastrarAtor(novoAtor);
                }

                case 3 -> {
                    System.out.println("\n--- Cadastro do(a) Diretor(a) ---");

                    System.out.print("Digite o nome do(a) diretor(a) : ");
                    String nomeDiretor = sc.nextLine();

                    LocalDate dataNascimentoDiretor = null;
                    while (dataNascimentoDiretor == null) {
                        System.out.print("Digite a data de nascimento do(a) diretor(a) (dd/MM/yyyy): ");
                        String dataNasceDiretor = sc.nextLine();
                        try {
                            dataNascimentoDiretor = LocalDate.parse(dataNasceDiretor, formatadorData);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido! Utilize o formato dd/MM/yyyy.");
                        }
                    }

                    System.out.print("Digite a nacionalidade do(a) diretor(a): ");
                    String nacionalidadeDiretor = sc.nextLine();

                    System.out.print("Digite o estilo do(a) diretor(a): ");
                    String estiloDiretor = sc.nextLine();

                    Diretor novoDiretor = new Diretor(nomeDiretor, dataNascimentoDiretor, nacionalidadeDiretor, estiloDiretor);
                    pessoaService.cadastrarDiretor(novoDiretor);
                }

                case 4 -> {
                    System.out.println("\n--- Associar Ator/Atriz ao Filme ---");

                    System.out.print("Digite o nome do(a) ator/atriz: ");
                    String nomeDoAtor = sc.nextLine();

                    System.out.print("Digite o nome do filme: ");
                    String nomeDoFilme = sc.nextLine();


                    Ator ator = pessoaService.buscarAtorPorNome(nomeDoAtor);
                    Filme filme = filmeService.buscarFilmePorNome(nomeDoFilme);

                    if (ator != null && filme != null) {
                        filmeService.associarAtorAoFilme(filme, ator);
                    } else {
                        System.out.println("Não houve associação entre ator/atriz e filme, pois ou o(a) ator/atriz ou o filme não foi encontrado(a).");
                    }
                }

                case 5 -> {
                    System.out.println("\n--- Associar Diretor(a) ao Filme ---");

                    System.out.print("Digite o nome do(a) diretor(a): ");
                    String nomeDoDiretor = sc.nextLine();

                    System.out.print("Digite o nome do filme: ");
                    String nomeDoFilme = sc.nextLine();

                    Diretor diretor = pessoaService.buscarDiretorPorNome(nomeDoDiretor);
                    Filme filme = filmeService.buscarFilmePorNome(nomeDoFilme);

                    if (diretor != null && filme != null) {
                        filmeService.associarDiretorAoFilme(filme, diretor);
                    } else {
                        System.out.println("Não houve associação entre diretor(a) e filme, pois ou o(a) diretor(a) ou o filme não foi encontrado(a).");
                    }
                }

                case 6 -> {
                    System.out.println("\n--- Buscar por filme ---");

                    System.out.print("Digite o nome do filme: ");
                    String nomeDoFilme = sc.nextLine();

                }

                case 0 -> System.out.println("\nEncerrando o programa... Até mais!");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
        sc.close();
    }
}
