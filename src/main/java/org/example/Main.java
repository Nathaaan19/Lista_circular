package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variáveis para armazenar os critérios de filtro
        String critDia = "";
        char critFinalPlaca = '\0'; // valor "nulo" para char

        // Menu de escolha do critério de filtro
        System.out.println("Escolha um critério de filtro:");
        System.out.println("1. Filtrar por dia da semana");
        System.out.println("2. Filtrar por final de placa");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do teclado

        // Leitura do critério de filtro conforme a escolha do usuário
        if (opcao == 1) {
            System.out.print("Digite o dia da semana para filtro (ex: Segunda-feira): ");
            critDia = scanner.nextLine();
        } else if (opcao == 2) {
            System.out.print("Digite o último dígito da placa para filtro (Escreva apenas um número): ");
            critFinalPlaca = scanner.nextLine().charAt(0); // Pega só o primeiro caractere digitado
        }

        // Carrega os veículos do arquivo, aplicando o filtro escolhido
        Veiculo veiculos = ArquivoVeiculo.carregarVeiculosDoArquivo("rodizio_de_veiculos.txt", critDia, critFinalPlaca);

        // Exibe os veículos afetados pela restrição
        System.out.println("\nVeículos afetados:");
        Veiculo.exibirVeiculos(veiculos);

        // Libera a lista da memória
        Veiculo.liberarLista(veiculos);

        System.out.println("Saindo do programa...");
        scanner.close(); // Fecha o scanner
    }
}
