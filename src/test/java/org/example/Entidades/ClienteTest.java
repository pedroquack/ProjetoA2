package org.example.Entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da entidade Cliente")
class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("João Silva", "Silva ME", "123.456.789-00");
    }

    @Test
    @DisplayName("Deve criar cliente com os dados corretos")
    void deveCriarClienteComDadosCorretos() {
        assertEquals("João Silva", cliente.getNome());
        assertEquals("Silva ME", cliente.getRazao());
        assertEquals("123.456.789-00", cliente.getCnpj_cpf());
    }

    @Test
    @DisplayName("ID deve ser nulo antes de ser persistido")
    void idDeveSerNuloAntesDeSerPersistido() {
        assertNull(cliente.getId());
    }

    @Test
    @DisplayName("Deve definir e recuperar o ID corretamente")
    void deveDefinirERecuperarIdCorretamente() {
        cliente.setId(10L);
        assertEquals(10L, cliente.getId());
    }

    @Test
    @DisplayName("Deve atualizar nome")
    void deveAtualizarNome() {
        cliente.setNome("Maria Souza");
        assertEquals("Maria Souza", cliente.getNome());
    }

    @Test
    @DisplayName("Deve atualizar razão social")
    void deveAtualizarRazaoSocial() {
        cliente.setRazao("Souza Ltda");
        assertEquals("Souza Ltda", cliente.getRazao());
    }

    @Test
    @DisplayName("Deve atualizar CPF/CNPJ")
    void deveAtualizarCpfCnpj() {
        cliente.setCnpj_cpf("00.000.000/0001-00");
        assertEquals("00.000.000/0001-00", cliente.getCnpj_cpf());
    }

    @Test
    @DisplayName("toString deve conter os dados do cliente")
    void toStringDeveConterDadosDoCliente() {
        cliente.setId(1L);
        String resultado = cliente.toString();
        assertTrue(resultado.contains("João Silva"));
        assertTrue(resultado.contains("Silva ME"));
        assertTrue(resultado.contains("123.456.789-00"));
    }
}
