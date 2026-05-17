package org.example.Entidades;

public class Produto implements Entidade {
    private Long id;
    private String nome;
    private Double valor;
    private Integer estoque;

    public Produto(String nome, Double valor, Integer estoque) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;

    }

    public Integer getEstoque() {
        return estoque;
    }

    @Override
    public String toString() {
        return String.format("| %3s | %20s | %12s | %6s |", this.id, this.nome, "R$ " + this.valor, this.estoque);
    }
}
