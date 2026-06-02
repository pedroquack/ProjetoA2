package org.example.Controllers;

import org.example.Entidades.Pedido;

public class PedidoController extends Controller<Pedido> {
    @Override
    public String getCabecalho() {
        String cabecalho = String.format("| %3s | %20s | %10s | %10s |", "ID", "CLIENTE", "APROVADO", "ENTREGUE");
        return cabecalho;
    }

}
