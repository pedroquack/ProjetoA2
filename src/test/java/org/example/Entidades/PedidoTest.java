package org.example.Entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da entidade Pedido")
class PedidoTest {

    private Cliente cliente;
    private Produto produto1;
    private Produto produto2;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana Lima", "Lima EIRELI", "111.222.333-44");
        cliente.setId(1L);

        produto1 = new Produto("Notebook", 3500.00, 10);
        produto1.setId(1L);

        produto2 = new Produto("Mouse", 80.00, 50);
        produto2.setId(2L);

        pedido = new Pedido(cliente);
    }

    @Test
    @DisplayName("Deve criar pedido com cliente correto")
    void deveCriarPedidoComClienteCorreto() {
        assertEquals(cliente, pedido.getCliente());
    }

    @Test
    @DisplayName("Pedido novo não deve estar aprovado")
    void pedidoNovoNaoDeveEstarAprovado() {
        assertFalse(pedido.getAprovado());
    }

    @Test
    @DisplayName("Pedido novo não deve estar entregue")
    void pedidoNovoNaoDeveEstarEntregue() {
        assertFalse(pedido.getEntregue());
    }

    @Test
    @DisplayName("Lista de produtos deve estar vazia no início")
    void listaDeProdutosDeveEstarVaziaNoInicio() {
        assertTrue(pedido.getProdutos().isEmpty());
    }

    @Test
    @DisplayName("Deve adicionar produto ao pedido")
    void deveAdicionarProdutoAoPedido() {
        pedido.addProduto(produto1);
        assertEquals(1, pedido.getProdutos().size());
        assertTrue(pedido.getProdutos().contains(produto1));
    }

    @Test
    @DisplayName("Deve remover produto existente do pedido")
    void deveRemoverProdutoExistenteNoPedido() {
        pedido.addProduto(produto1);
        pedido.addProduto(produto2);

        boolean removido = pedido.removeProduto(produto1.getId());

        assertTrue(removido);
        assertEquals(1, pedido.getProdutos().size());
        assertFalse(pedido.getProdutos().contains(produto1));
    }

    @Test
    @DisplayName("Deve retornar false ao tentar remover produto inexistente")
    void deveRetornarFalseAoRemoverProdutoInexistente() {
        pedido.addProduto(produto1);

        boolean removido = pedido.removeProduto(999L);

        assertFalse(removido);
        assertEquals(1, pedido.getProdutos().size());
    }

    @Test
    @DisplayName("Deve calcular total do pedido corretamente")
    void deveCalcularTotalDoPedidoCorretamente() {
        pedido.addProduto(produto1);
        pedido.addProduto(produto2);

        assertEquals(3580.00, pedido.getTotalPedido(), 0.001);
    }

    @Test
    @DisplayName("Total de pedido vazio deve ser zero")
    void totalDePedidoVazioDeveSerZero() {
        assertEquals(0.0, pedido.getTotalPedido(), 0.001);
    }

    @Test
    @DisplayName("Deve aprovar pedido")
    void deveAprovarPedido() {
        pedido.aprovarPedido();
        assertTrue(pedido.getAprovado());
    }

    @Test
    @DisplayName("Deve marcar pedido como entregue")
    void deveMarcarPedidoComoEntregue() {
        pedido.marcarComoEntregue();
        assertTrue(pedido.getEntregue());
    }

    @Test
    @DisplayName("Deve definir e recuperar ID")
    void deveDefinirERecuperarId() {
        pedido.setId(42L);
        assertEquals(42L, pedido.getId());
    }

    @Test
    @DisplayName("Deve substituir lista de produtos")
    void deveSubstituirListaDeProdutos() {
        pedido.addProduto(produto1);

        pedido.setProdutos(List.of(produto2));

        assertEquals(1, pedido.getProdutos().size());
        assertEquals(produto2, pedido.getProdutos().get(0));
    }

    @Test
    @DisplayName("toString deve conter nome do cliente")
    void toStringDeveConterNomeDoCliente() {
        pedido.setId(1L);
        String resultado = pedido.toString();
        assertTrue(resultado.contains("Ana Lima"));
    }

    @Test
    @DisplayName("toString deve indicar pedido não aprovado")
    void toStringDeveIndicarPedidoNaoAprovado() {
        pedido.setId(1L);
        String resultado = pedido.toString();
        assertTrue(resultado.contains("Não"));
    }

    @Test
    @DisplayName("toString deve indicar pedido aprovado e entregue")
    void toStringDeveIndicarPedidoAprovadoEEntregue() {
        pedido.setId(1L);
        pedido.aprovarPedido();
        pedido.marcarComoEntregue();
        String resultado = pedido.toString();
        assertTrue(resultado.contains("Sim"));
    }
}
