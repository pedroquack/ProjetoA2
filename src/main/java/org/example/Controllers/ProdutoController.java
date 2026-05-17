package org.example.Controllers;

import org.example.Entidades.Produto;

public class ProdutoController extends Controller<Produto> {
    @Override
    public String getCabecalho() {
        String cabecalho = String.format("| %3s | %20s | %12s | %6s |", "ID", "NOME", "VALOR", "ESTOQUE");
        return cabecalho;
    }

}
