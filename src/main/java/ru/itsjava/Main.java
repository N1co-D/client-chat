package ru.itsjava;

import ru.itsjava.services.ClientService;
import ru.itsjava.services.ClientServiceImpl;

public class Main {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    public static void main(String[] args) {
        ClientService clientService = new ClientServiceImpl();
        clientService.start();
    }
}