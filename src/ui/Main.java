package ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import model.*;
import service.FilmeService;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FilmeService filmeService = new FilmeService();
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao = -1;

        System.out.println("\nBem-vindo ao Catálogo de Filmes SerMaisFlix!");

        while (opcao != 0) {
            System.out.println("""

                    --- Menu Principal ---
                    1. Cadastrar Filme
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
                case 0 -> System.out.println("\nEncerrando o programa... Até mais!");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
        sc.close();
    }
}
