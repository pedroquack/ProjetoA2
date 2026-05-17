package org.example.Menus;

import org.example.Controllers.ClienteController;
import org.example.Entidades.Cliente;
import org.example.Entidades.Menu;

import java.util.Scanner;

public class ClienteMenu extends Menu {

    private final ClienteController controller;

    public ClienteMenu(ClienteController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    @Override
    protected void exibirOpcoes() {
        System.out.println("\n======= CLIENTES =======");
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
        System.out.println("\n--- Lista de Clientes ---");
        controller.listar();
    }

    private void criar() {
        System.out.println("\n--- Novo Cliente ---");
        String nome = lerTexto("Nome: ");
        String razao = lerTexto("Razão Social: ");
        String cnpjCpf = lerTexto("CPF / CNPJ: ");
        controller.criar(new Cliente(nome, razao, cnpjCpf));
        System.out.println("Cliente criado com sucesso.");
    }

    private void exibir() {
        Long id = lerLong("ID do cliente: ");
        if (controller.buscar(id) == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        controller.exibir(id);
    }

    private void editar() {
        Long id = lerLong("ID do cliente a editar: ");
        if (controller.buscar(id) == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        System.out.println("Informe os novos dados (Enter para manter vazio):");
        String nome = lerTexto("Novo nome: ");
        String razao = lerTexto("Nova razão social: ");
        String cnpjCpf = lerTexto("Novo CPF / CNPJ: ");
        controller.editar(id, new Cliente(nome, razao, cnpjCpf));
        System.out.println("Cliente atualizado com sucesso.");
    }

    private void excluir() {
        Long id = lerLong("ID do cliente a excluir: ");
        if (controller.buscar(id) == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        controller.excluir(id);
        System.out.println("Cliente excluído com sucesso.");
    }
}
