package org.example.Entidades;

public class Cliente implements Entidade {

    private Long id;
    private String nome;
    private String razao_social;
    private String cnpj_cpf;

    public Cliente(String nome, String razao_social, String cnpj_cpf) {
        this.nome = nome;
        this.razao_social = razao_social;
        this.cnpj_cpf = cnpj_cpf;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazao() {
        return this.razao_social;
    }

    public void setRazao(String razao) {
        this.razao_social = razao;
    }

    public String getCnpj_cpf() {
        return this.cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

    @Override
    public String toString(){
        return String.format("| %3s | %20s | %30s | %15s |", this.id, this.nome, this.razao_social, this.cnpj_cpf);
    }
}
