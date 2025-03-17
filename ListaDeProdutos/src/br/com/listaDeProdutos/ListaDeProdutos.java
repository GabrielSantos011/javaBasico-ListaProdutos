package br.com.listaDeProdutos;

import br.com.listaDeProdutos.domain.Produto;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import br.com.listaDeProdutos.exception.ProdutoException;

public class ListaDeProdutos {

    private static final String[] CATEGORIAS = {"Eletrônicos", "Alimentos", "Roupas"};
    private static final List<Produto> produtos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 4);
    }

    private static void exibirMenu() {
        System.out.println("""
                           --- Menu ---
                           1. Adicionar Produto
                           2. Listar Produtos
                           3. Remover Produto
                           4. Sair
                           Escolha uma opção: """);
    }

    private static int lerOpcao() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.next(); // Limpa entrada inválida
            return -1;
        }
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> adicionarProduto();
            case 2 -> listarProdutos();
            case 3 -> removerProduto();
            case 4 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void adicionarProduto() {
        scanner.nextLine(); // Limpa buffer
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço do produto: ");
        double preco;
        try {
            preco = scanner.nextDouble();
            if (preco <= 0) {
                throw new ProdutoException("O preço do produto deve ser maior que zero.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.next();
            return;
        }

        System.out.println("Escolha a categoria:");
        for (int i = 0; i < CATEGORIAS.length; i++) {
            System.out.println((i + 1) + ". " + CATEGORIAS[i]);
        }
        System.out.print("Opção: ");
        int escolha;
        try {
            escolha = scanner.nextInt();
            if (escolha < 1 || escolha > CATEGORIAS.length) {
                throw new ProdutoException("Categoria inválida! Escolha uma opção válida.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.next();
            return;
        }

        produtos.add(new Produto(nome, preco, CATEGORIAS[escolha - 1]));

        System.out.println("Produto adicionado com sucesso!");
    }

    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\nLista de Produtos:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i + 1) + ". " + produtos.get(i));
        }
    }

    private static void removerProduto() {
        listarProdutos();
        if (produtos.isEmpty()) return;

        System.out.print("Digite o número do produto para remover: ");
        try {
            int index = scanner.nextInt() - 1;
            if (index < 0 || index >= produtos.size()) {
                throw new ProdutoException("Número inválido! Escolha um produto existente.");
            }
            produtos.remove(index);
            System.out.println("Produto removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.next();
        }
    }

}
