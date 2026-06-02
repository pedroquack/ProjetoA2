package org.example;

import org.example.Menus.AppMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new AppMenu(scanner).executar();
        scanner.close();
    }
}
