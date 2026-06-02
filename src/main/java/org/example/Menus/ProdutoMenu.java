package org.example.Menus;

import org.example.Controllers.ProdutoController;
import org.example.Entidades.DuplicateIdException;
import org.example.Entidades.Menu;
import org.example.Entidades.Produto;

import java.util.Scanner;

public class ProdutoMenu extends Menu {

    private final ProdutoController controller;

    public ProdutoMenu(ProdutoController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    @Override
    protected void exibirOpcoes() {
        System.out.println("\n======= PRODUTOS =======");
        System.out.println("1. Listar");
        System.out.println("2. Criar");
        System.out.println("3. Exibir por ID");
        System.out.println("4. Editar");
        System.out.println("5. Excluir");
        System.out.println("0. Voltar");
        System.out.println("========================");
    }

    @Override
    public void executar() {
        int opcao;
        do {
            exibirOpcoes();
            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> listar();
                case 2 -> criar();
                case 3 -> exibir();
                case 4 -> editar();
                case 5 -> excluir();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void listar() {
        System.out.println("\n--- Lista de Produtos ---");
        controller.listar();
        pausar();
    }

    private void criar() {
        System.out.println("\n--- Novo Produto ---");
        String nome = lerTexto("Nome: ");
        Double valor = lerDouble("Valor (ex: 29.90): ");
        Integer estoque = lerInteiro("Estoque: ");
        try {
            controller.criar(new Produto(nome, valor, estoque));
            System.out.println("Produto criado com sucesso.");
        } catch (DuplicateIdException e) {
            System.out.println("Erro ao criar produto: " + e.getMessage());
        } finally {
            pausar();
        }
    }

    private void exibir() {
        Long id = lerLong("ID do produto: ");
        if (controller.buscar(id) == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        controller.exibir(id);
        pausar();
    }

    private void editar() {
        Long id = lerLong("ID do produto a editar: ");
        if (controller.buscar(id) == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        System.out.println("Informe os novos dados:");
        String nome = lerTexto("Novo nome: ");
        Double valor = lerDouble("Novo valor: ");
        Integer estoque = lerInteiro("Novo estoque: ");
        controller.editar(id, new Produto(nome, valor, estoque));
        System.out.println("Produto atualizado com sucesso.");
        pausar();
    }

    private void excluir() {
        Long id = lerLong("ID do produto a excluir: ");
        if (controller.buscar(id) == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        controller.excluir(id);
        System.out.println("Produto excluído com sucesso.");
        pausar();
    }
}
