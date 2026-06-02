package org.example.Entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da entidade Produto")
class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Caneta Azul", 2.50, 100);
    }

    @Test
    @DisplayName("Deve criar produto com dados corretos")
    void deveCriarProdutoComDadosCorretos() {
        assertEquals("Caneta Azul", produto.getNome());
        assertEquals(2.50, produto.getValor());
        assertEquals(100, produto.getEstoque());
    }

    @Test
    @DisplayName("ID deve ser nulo antes de ser persistido")
    void idDeveSerNuloAntesDeSerPersistido() {
        assertNull(produto.getId());
    }

    @Test
    @DisplayName("Deve definir e recuperar o ID corretamente")
    void deveDefinirERecuperarIdCorretamente() {
        produto.setId(5L);
        assertEquals(5L, produto.getId());
    }

    @Test
    @DisplayName("Deve atualizar nome")
    void deveAtualizarNome() {
        produto.setNome("Caneta Vermelha");
        assertEquals("Caneta Vermelha", produto.getNome());
    }

    @Test
    @DisplayName("Deve atualizar valor")
    void deveAtualizarValor() {
        produto.setValor(3.99);
        assertEquals(3.99, produto.getValor());
    }

    @Test
    @DisplayName("Deve atualizar estoque")
    void deveAtualizarEstoque() {
        produto.setEstoque(50);
        assertEquals(50, produto.getEstoque());
    }

    @Test
    @DisplayName("toString deve conter os dados do produto")
    void toStringDeveConterDadosDoProduto() {
        produto.setId(1L);
        String resultado = produto.toString();
        assertTrue(resultado.contains("Caneta Azul"));
        assertTrue(resultado.contains("2.5"));
        assertTrue(resultado.contains("100"));
    }
}
