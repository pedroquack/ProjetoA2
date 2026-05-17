package org.example.Menus;

import org.example.Controllers.ClienteController;
import org.example.Controllers.PedidoController;
import org.example.Controllers.ProdutoController;
import org.example.Entidades.Cliente;
import org.example.Entidades.Menu;
import org.example.Entidades.Pedido;
import org.example.Entidades.Produto;

import java.util.Scanner;

public class PedidoMenu extends Menu {

    private final PedidoController pedidoController;
    private final ClienteController clienteController;
    private final ProdutoController produtoController;

    public PedidoMenu(PedidoController pedidoController,
                      ClienteController clienteController,
                      ProdutoController produtoController,
                      Scanner scanner) {
        super(scanner);
        this.pedidoController = pedidoController;
        this.clienteController = clienteController;
        this.produtoController = produtoController;
    }

    @Override
    protected void exibirOpcoes() {
        System.out.println("\n======= PEDIDOS =======");
        System.out.println("1. Listar");
        System.out.println("2. Criar");
        System.out.println("3. Exibir por ID");
        System.out.println("4. Editar produtos do pedido");
        System.out.println("5. Aprovar pedido");
        System.out.println("6. Marcar como entregue");
        System.out.println("7. Excluir");
        System.out.println("0. Voltar");
        System.out.println("=======================");
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
                case 4 -> editarProdutos();
                case 5 -> aprovar();
                case 6 -> marcarEntregue();
                case 7 -> excluir();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void listar() {
        System.out.println("\n--- Lista de Pedidos ---");
        pedidoController.listar();
        pausar();
    }

    private void criar() {
        System.out.println("\n--- Novo Pedido ---");

        System.out.println("\nClientes disponíveis:");
        clienteController.listar();
        Long idCliente = lerLong("ID do cliente: ");
        Cliente cliente = clienteController.buscar(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado. Pedido cancelado.");
            return;
        }

        Pedido pedido = new Pedido(cliente);

        System.out.println("\nProdutos disponíveis:");
        produtoController.listar();
        System.out.println("Adicione produtos ao pedido (0 para finalizar):");

        while (true) {
            Long idProduto = lerLong("ID do produto (0 para finalizar): ");
            if (idProduto == 0) break;
            Produto produto = produtoController.buscar(idProduto);
            if (produto == null) {
                System.out.println("Produto não encontrado. Tente novamente.");
                continue;
            }
            pedido.addProduto(produto);
            System.out.println("Produto '" + produto.getNome() + "' adicionado.");
        }

        if (pedido.getProdutos().isEmpty()) {
            System.out.println("Pedido deve ter ao menos um produto. Operação cancelada.");
            return;
        }

        pedidoController.criar(pedido);
        System.out.printf("Pedido criado. Total: R$ %.2f%n", pedido.getTotalPedido());
        pausar();
    }

    private void exibir() {
        Long id = lerLong("ID do pedido: ");
        if (pedidoController.buscar(id) == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }
        pedidoController.exibir(id);
        pausar();
    }

    private void editarProdutos() {
        Long id = lerLong("ID do pedido a editar: ");
        Pedido pedido = pedidoController.buscar(id);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- Editar Produtos do Pedido #" + id + " ---");
            System.out.println("Produtos atuais:");
            pedido.getProdutos().forEach(p ->
                    System.out.printf("  [%d] %s - R$ %.2f%n", p.getId(), p.getNome(), p.getValor()));
            System.out.println("1. Adicionar produto");
            System.out.println("2. Remover produto");
            System.out.println("0. Voltar");
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\nProdutos disponíveis:");
                    produtoController.listar();
                    Long idProduto = lerLong("ID do produto a adicionar: ");
                    Produto produto = produtoController.buscar(idProduto);
                    if (produto == null) {
                        System.out.println("Produto não encontrado.");
                    } else {
                        pedido.addProduto(produto);
                        System.out.println("Produto adicionado.");
                    }
                }
                case 2 -> {
                    Long idProduto = lerLong("ID do produto a remover: ");
                    boolean removido = pedido.removeProduto(idProduto);
                    System.out.println(removido ? "Produto removido." : "Produto não encontrado no pedido.");
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void aprovar() {
        Long id = lerLong("ID do pedido a aprovar: ");
        Pedido pedido = pedidoController.buscar(id);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }
        if (pedido.getAprovado()) {
            System.out.println("Pedido já está aprovado.");
            return;
        }
        pedido.aprovarPedido();
        System.out.println("Pedido aprovado com sucesso.");
        pausar();
    }

    private void marcarEntregue() {
        Long id = lerLong("ID do pedido a marcar como entregue: ");
        Pedido pedido = pedidoController.buscar(id);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }
        if (!pedido.getAprovado()) {
            System.out.println("Pedido precisa ser aprovado antes de ser marcado como entregue.");
            return;
        }
        if (pedido.getEntregue()) {
            System.out.println("Pedido já foi marcado como entregue.");
            return;
        }
        pedido.marcarComoEntregue();
        System.out.println("Pedido marcado como entregue.");
        pausar();
    }

    private void excluir() {
        Long id = lerLong("ID do pedido a excluir: ");
        if (pedidoController.buscar(id) == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }
        pedidoController.excluir(id);
        System.out.println("Pedido excluído com sucesso.");
        pausar();
    }
}
