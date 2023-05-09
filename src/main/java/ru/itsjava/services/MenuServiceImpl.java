package ru.itsjava.services;

import lombok.SneakyThrows;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    @SneakyThrows
    @Override
    public void menu() {
        while (true) {
            System.out.println("Введите номер меню!");
            Scanner scanner = new Scanner(System.in);
            int menuNum = scanner.nextInt();

            if (menuNum == 1) {
                ClientService clientService = new ClientServiceImpl();
                clientService.start(1);
            } else if (menuNum == 2) {
                ClientService clientService = new ClientServiceImpl();
                clientService.start(2);
            } else {
                System.out.println("До свидания!");
                break;
            }
        }
    }

    @Override
    public void printMenu() {
        System.out.println("Команды меню:");
        System.out.println("1 - Авторизация");
        System.out.println("2 - Регистрация");
        System.out.println("Прочие команды - выход из меню.");
    }
}
