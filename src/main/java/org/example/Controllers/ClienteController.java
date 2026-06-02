package org.example.Controllers;

import org.example.Entidades.Cliente;

public class ClienteController extends Controller<Cliente> {
    @Override
    public String getCabecalho() {
        String cabecalho = String.format("| %3s | %20s | %30s | %15s |", "ID", "NOME", "RAZÃO SOCIAL", "CPF OU CNPJ");
        return cabecalho;
    }
}
