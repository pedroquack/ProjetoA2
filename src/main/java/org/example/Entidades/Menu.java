package org.example.Entidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu {

    protected Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    protected abstract void exibirOpcoes();

    public abstract void executar();

    protected int lerOpcao() {
        System.out.print("Opção: ");
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    protected String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    protected Double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    protected Integer lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    protected Long lerLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                long valor = scanner.nextLong();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
}
