package org.example.Controllers;

import org.example.Entidades.DuplicateIdException;
import org.example.Entidades.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do ProdutoController")
class ProdutoControllerTest {

    private ProdutoController controller;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        controller = new ProdutoController();
        produto1 = new Produto("Teclado", 150.00, 30);
        produto2 = new Produto("Monitor", 900.00, 10);
    }

    @Test
    @DisplayName("Deve criar produto e atribuir ID sequencial")
    void deveCriarProdutoEAtribuirIdSequencial() throws DuplicateIdException {
        assertTrue(controller.criar(produto1));
        assertEquals(1L, produto1.getId());

        assertTrue(controller.criar(produto2));
        assertEquals(2L, produto2.getId());
    }

    @Test
    @DisplayName("Deve buscar produto por ID existente")
    void deveBuscarProdutoPorIdExistente() throws DuplicateIdException {
        controller.criar(produto1);

        Produto encontrado = controller.buscar(1L);

        assertNotNull(encontrado);
        assertEquals("Teclado", encontrado.getNome());
        assertEquals(150.00, encontrado.getValor());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar ID inexistente")
    void deveRetornarNullAoBuscarIdInexistente() {
        assertNull(controller.buscar(99L));
    }

    @Test
    @DisplayName("Deve editar produto existente")
    void deveEditarProdutoExistente() throws DuplicateIdException {
        controller.criar(produto1);

        Produto atualizado = new Produto("Teclado Gamer", 299.99, 15);
        boolean resultado = controller.editar(1L, atualizado);

        assertTrue(resultado);
        Produto buscado = controller.buscar(1L);
        assertEquals("Teclado Gamer", buscado.getNome());
        assertEquals(299.99, buscado.getValor());
        assertEquals(15, buscado.getEstoque());
        assertEquals(1L, buscado.getId()); // ID deve ser preservado
    }

    @Test
    @DisplayName("Deve retornar false ao editar ID inexistente")
    void deveRetornarFalseAoEditarIdInexistente() {
        assertFalse(controller.editar(99L, produto1));
    }

    @Test
    @DisplayName("Deve excluir produto existente")
    void deveExcluirProdutoExistente() throws DuplicateIdException {
        controller.criar(produto1);
        controller.criar(produto2);

        boolean resultado = controller.excluir(1L);

        assertTrue(resultado);
        assertNull(controller.buscar(1L));
        assertNotNull(controller.buscar(2L)); // Outro produto não deve ser afetado
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
    @DisplayName("Deve lançar DuplicateIdException ao criar produto com ID já existente")
    void deveLancarExcecaoAoCriarProdutoComIdDuplicado() throws DuplicateIdException {
        controller.criar(produto1); // recebe ID 1L via auto-increment

        Produto duplicado = new Produto("Duplicado", 1.0, 1);
        duplicado.setId(1L); // força o mesmo ID

        DuplicateIdException excecao = assertThrows(
                DuplicateIdException.class,
                () -> controller.criar(duplicado)
        );
        assertEquals(1L, excecao.getId());
        assertTrue(excecao.getMessage().contains("1"));
    }

    @Test
    @DisplayName("Não deve lançar exceção ao criar produtos com IDs distintos")
    void naoDeveLancarExcecaoComIdsDiferentes() {
        assertDoesNotThrow(() -> {
            controller.criar(produto1);
            controller.criar(produto2);
        });
    }
}
