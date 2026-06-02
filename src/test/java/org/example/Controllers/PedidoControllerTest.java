package org.example.Controllers;

import org.example.Entidades.Cliente;
import org.example.Entidades.DuplicateIdException;
import org.example.Entidades.Pedido;
import org.example.Entidades.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do PedidoController")
class PedidoControllerTest {

    private PedidoController controller;
    private Pedido pedido1;
    private Pedido pedido2;
    private Cliente cliente;
    private Produto produto;

    @BeforeEach
    void setUp() {
        controller = new PedidoController();

        cliente = new Cliente("Roberto Alves", "Alves ME", "333.333.333-33");
        cliente.setId(1L);

        produto = new Produto("Caderno", 15.00, 200);
        produto.setId(1L);

        pedido1 = new Pedido(cliente);
        pedido1.addProduto(produto);

        pedido2 = new Pedido(cliente);
        pedido2.addProduto(produto);
    }

    @Test
    @DisplayName("Deve criar pedido e atribuir ID sequencial")
    void deveCriarPedidoEAtribuirIdSequencial() throws DuplicateIdException {
        assertTrue(controller.criar(pedido1));
        assertEquals(1L, pedido1.getId());

        assertTrue(controller.criar(pedido2));
        assertEquals(2L, pedido2.getId());
    }

    @Test
    @DisplayName("Deve buscar pedido por ID existente")
    void deveBuscarPedidoPorIdExistente() throws DuplicateIdException {
        controller.criar(pedido1);

        Pedido encontrado = controller.buscar(1L);

        assertNotNull(encontrado);
        assertEquals(cliente, encontrado.getCliente());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar ID inexistente")
    void deveRetornarNullAoBuscarIdInexistente() {
        assertNull(controller.buscar(99L));
    }

    @Test
    @DisplayName("Deve editar pedido existente")
    void deveEditarPedidoExistente() throws DuplicateIdException {
        controller.criar(pedido1);

        Cliente novoCliente = new Cliente("Novo Cliente", "NC Ltda", "444.444.444-44");
        novoCliente.setId(2L);
        Pedido pedidoAtualizado = new Pedido(novoCliente);
        pedidoAtualizado.addProduto(produto);

        boolean resultado = controller.editar(1L, pedidoAtualizado);

        assertTrue(resultado);
        Pedido buscado = controller.buscar(1L);
        assertEquals("Novo Cliente", buscado.getCliente().getNome());
        assertEquals(1L, buscado.getId()); // ID deve ser preservado
    }

    @Test
    @DisplayName("Deve retornar false ao editar ID inexistente")
    void deveRetornarFalseAoEditarIdInexistente() {
        assertFalse(controller.editar(99L, pedido1));
    }

    @Test
    @DisplayName("Deve excluir pedido existente")
    void deveExcluirPedidoExistente() throws DuplicateIdException {
        controller.criar(pedido1);
        controller.criar(pedido2);

        boolean resultado = controller.excluir(1L);

        assertTrue(resultado);
        assertNull(controller.buscar(1L));
        assertNotNull(controller.buscar(2L));
    }

    @Test
    @DisplayName("Deve retornar false ao excluir ID inexistente")
    void deveRetornarFalseAoExcluirIdInexistente() {
        assertFalse(controller.excluir(99L));
    }

    @Test
    @DisplayName("getCabecalho não deve ser nulo nem vazio")
    void getCabecalhoNaoDeveSerNuloNemVazio() {
        String cabecalho = controller.getCabecalho();
        assertNotNull(cabecalho);
        assertFalse(cabecalho.isBlank());
    }

    @Test
    @DisplayName("Deve lançar DuplicateIdException ao criar pedido com ID já existente")
    void deveLancarExcecaoAoCriarPedidoComIdDuplicado() throws DuplicateIdException {
        controller.criar(pedido1); // recebe ID 1L via auto-increment

        Pedido duplicado = new Pedido(cliente);
        duplicado.addProduto(produto);
        duplicado.setId(1L); // força o mesmo ID

        DuplicateIdException excecao = assertThrows(
                DuplicateIdException.class,
                () -> controller.criar(duplicado)
        );
        assertEquals(1L, excecao.getId());
        assertTrue(excecao.getMessage().contains("1"));
    }

    @Test
    @DisplayName("Não deve lançar exceção ao criar pedidos com IDs distintos")
    void naoDeveLancarExcecaoComIdsDiferentes() {
        assertDoesNotThrow(() -> {
            controller.criar(pedido1);
            controller.criar(pedido2);
        });
    }

    @Test
    @DisplayName("Pedido buscado deve manter estado de aprovação")
    void pedidoBuscadoDeveManterEstadoDeAprovacao() throws DuplicateIdException {
        controller.criar(pedido1);
        pedido1.aprovarPedido();

        Pedido buscado = controller.buscar(1L);
        assertTrue(buscado.getAprovado());
    }

    @Test
    @DisplayName("Pedido buscado deve manter estado de entrega")
    void pedidoBuscadoDeveManterEstadoDeEntrega() throws DuplicateIdException {
        controller.criar(pedido1);
        pedido1.marcarComoEntregue();

        Pedido buscado = controller.buscar(1L);
        assertTrue(buscado.getEntregue());
    }
}
