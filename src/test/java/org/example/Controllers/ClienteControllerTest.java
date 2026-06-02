package org.example.Controllers;

import org.example.Entidades.Cliente;
import org.example.Entidades.DuplicateIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do ClienteController")
class ClienteControllerTest {

    private ClienteController controller;
    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        controller = new ClienteController();
        cliente1 = new Cliente("Carlos Mendes", "Mendes ME", "111.111.111-11");
        cliente2 = new Cliente("Fernanda Costa", "Costa Ltda", "22.222.222/0001-22");
    }

    @Test
    @DisplayName("Deve criar cliente e atribuir ID sequencial")
    void deveCriarClienteEAtribuirIdSequencial() throws DuplicateIdException {
        assertTrue(controller.criar(cliente1));
        assertEquals(1L, cliente1.getId());

        assertTrue(controller.criar(cliente2));
        assertEquals(2L, cliente2.getId());
    }

    @Test
    @DisplayName("Deve buscar cliente por ID existente")
    void deveBuscarClientePorIdExistente() throws DuplicateIdException {
        controller.criar(cliente1);

        Cliente encontrado = controller.buscar(1L);

        assertNotNull(encontrado);
        assertEquals("Carlos Mendes", encontrado.getNome());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar ID inexistente")
    void deveRetornarNullAoBuscarIdInexistente() throws DuplicateIdException {
        controller.criar(cliente1);

        assertNull(controller.buscar(99L));
    }

    @Test
    @DisplayName("Deve editar cliente existente")
    void deveEditarClienteExistente() throws DuplicateIdException {
        controller.criar(cliente1);

        Cliente atualizado = new Cliente("Carlos M. Editado", "Mendes Ltda", "111.111.111-11");
        boolean resultado = controller.editar(1L, atualizado);

        assertTrue(resultado);
        Cliente buscado = controller.buscar(1L);
        assertEquals("Carlos M. Editado", buscado.getNome());
        assertEquals("Mendes Ltda", buscado.getRazao());
        assertEquals(1L, buscado.getId()); // ID deve ser mantido
    }

    @Test
    @DisplayName("Deve retornar false ao editar ID inexistente")
    void deveRetornarFalseAoEditarIdInexistente() {
        boolean resultado = controller.editar(99L, cliente1);
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve excluir cliente existente")
    void deveExcluirClienteExistente() throws DuplicateIdException {
        controller.criar(cliente1);

        boolean resultado = controller.excluir(1L);

        assertTrue(resultado);
        assertNull(controller.buscar(1L));
    }

    @Test
    @DisplayName("Deve retornar false ao excluir ID inexistente")
    void deveRetornarFalseAoExcluirIdInexistente() {
        boolean resultado = controller.excluir(99L);
        assertFalse(resultado);
    }

    @Test
    @DisplayName("IDs devem ser reiniciados a cada instância do controller")
    void idDeveIniciarEm1ParaCadaInstancia() throws DuplicateIdException {
        ClienteController outro = new ClienteController();
        Cliente c = new Cliente("Novo", "Novo ME", "000.000.000-00");
        outro.criar(c);
        assertEquals(1L, c.getId());
    }

    @Test
    @DisplayName("getCabecalho não deve ser nulo nem vazio")
    void getCabecalhoNaoDeveSerNuloNemVazio() {
        String cabecalho = controller.getCabecalho();
        assertNotNull(cabecalho);
        assertFalse(cabecalho.isBlank());
    }

    @Test
    @DisplayName("Deve lançar DuplicateIdException ao criar cliente com ID já existente")
    void deveLancarExcecaoAoCriarClienteComIdDuplicado() throws DuplicateIdException {
        controller.criar(cliente1); // recebe ID 1L via auto-increment

        Cliente duplicado = new Cliente("Duplicado", "Dup ME", "999.999.999-99");
        duplicado.setId(1L); // força o mesmo ID

        DuplicateIdException excecao = assertThrows(
                DuplicateIdException.class,
                () -> controller.criar(duplicado)
        );
        assertEquals(1L, excecao.getId());
        assertTrue(excecao.getMessage().contains("1"));
    }

    @Test
    @DisplayName("Não deve lançar exceção ao criar clientes com IDs distintos")
    void naoDeveLancarExcecaoComIdsDiferentes() {
        assertDoesNotThrow(() -> {
            controller.criar(cliente1);
            controller.criar(cliente2);
        });
    }
}
