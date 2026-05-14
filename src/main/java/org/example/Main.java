package org.example;

import org.example.Controllers.ProdutoController;
import org.example.Entidades.Produto;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        ProdutoController produtos = new ProdutoController();
        Produto pneu = new Produto("18560R15",359.90,12);
        Boolean produtocriado = produtos.criar(pneu);
        if(produtocriado){
            System.out.println("Produto criado");
        }
        Produto pneu2 = new Produto ("185 65 R15", 399.99, 12);

        produtos.criar(pneu2);

        Boolean produtoexcluido =  produtos.excluir(0L);

        System.out.println(produtoexcluido);
        produtos.listar();
    }
}
