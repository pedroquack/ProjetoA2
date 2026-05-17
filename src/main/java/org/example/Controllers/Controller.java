package org.example.Controllers;

import org.example.Entidades.Entidade;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller<T extends Entidade> {
    private List<T> entidades = new ArrayList<T>();
    private Long contador_ids = 1L;

    public Boolean criar(T entidade) {
        entidade.setId(contador_ids);
        entidades.add(entidade);
        contador_ids++;
        return true;
    }

    public Boolean editar(Long id, T entidade) {
        for (Integer i = 0; i < entidades.size(); i++) {
            Entidade atual = entidades.get(i);
            if (atual.getId().equals(id)) {
                entidade.setId(atual.getId());
                this.entidades.set(i, entidade);
                return true;
            }
        }
        return false;

    }

    public void exibir(Long id) {
        System.out.println(getCabecalho());
        for (Integer i = 0; i < entidades.size(); i++) {
            Entidade atual = entidades.get(i);
            if (atual.getId().equals(id)) {
                System.out.println(atual.toString());
                return;
            }
        }

    }

    public void listar() {
        System.out.println(getCabecalho());
        for (Integer i = 0; i < entidades.size(); i++) {
            Entidade atual = entidades.get(i);
            System.out.println(atual.toString());
        }

    }

    public Boolean excluir(Long id) {
        for (int i = 0; i < entidades.size(); i++) {
            Entidade atual = entidades.get(i);
            if (atual.getId().equals(id)) {
                System.out.println(i);
                entidades.remove(i);
                return true;
            }
        }
        return false;
    }

    public T buscar(Long id) {
        for (T entidade : entidades) {
            if (entidade.getId().equals(id)) {
                return entidade;
            }
        }
        return null;
    }

    public abstract String getCabecalho();

}
