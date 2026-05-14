package org.example.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Pedido  implements Entidade {
    private Long id;
    private Cliente cliente;
    private List<Produto> produtos = new ArrayList<Produto>();
    private Boolean aprovado = false;
    private Boolean entregue = false;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(Boolean entregue) {
        this.entregue = entregue;
    }

    public Double getTotalPedido(){
        return  0.0;
    }

    public Boolean addProduto(Long id){
       return true;
    }

    public void aprovarPedido(){
        this.aprovado=true;
    }

    public void marcarComoEntregue(){
        this.entregue=true;
    }
}
