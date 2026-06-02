package org.example.Menus;

import org.example.Controllers.ClienteController;
import org.example.Controllers.PedidoController;
import org.example.Controllers.ProdutoController;
import org.example.Entidades.Menu;

import java.util.Scanner;

public class AppMenu extends Menu {

    private final ClienteMenu clienteMenu;
    private final ProdutoMenu produtoMenu;
    private final PedidoMenu pedidoMenu;

    public AppMenu(Scanner scanner) {
        super(scanner);
        ClienteController clienteController = new ClienteController();
        ProdutoController produtoController = new ProdutoController();
        PedidoController pedidoController = new PedidoController();

        this.clienteMenu = new ClienteMenu(clienteController, scanner);
        this.produtoMenu = new ProdutoMenu(produtoController, scanner);
        this.pedidoMenu = new PedidoMenu(pedidoController, clienteController, produtoController, scanner);
    }

    @Override
    protected void exibirOpcoes() {
        System.out.println("\n========= VENDAS =========");
        System.out.println("1. Clientes");
        System.out.println("2. Produtos");
        System.out.println("3. Pedidos");
        System.out.println("0. Sair");
        System.out.println("==========================");
    }

    @Override
    public void executar() {
        int opcao;
        do {
            exibirOpcoes();
            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> clienteMenu.executar();
                case 2 -> produtoMenu.executar();
                case 3 -> pedidoMenu.executar();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
