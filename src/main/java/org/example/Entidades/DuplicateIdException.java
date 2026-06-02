package org.example.Entidades;

public class DuplicateIdException extends Exception {

    private final Long id;

    public DuplicateIdException(Long id) {
        super("Já existe uma entidade com o ID: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
