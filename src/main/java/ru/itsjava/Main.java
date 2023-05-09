package ru.itsjava;

import ru.itsjava.services.MenuService;
import ru.itsjava.services.MenuServiceImpl;

public class Main {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    public static void main(String[] args) {
        MenuService menuService = new MenuServiceImpl();
        menuService.printMenu();
        menuService.menu();
    }
}