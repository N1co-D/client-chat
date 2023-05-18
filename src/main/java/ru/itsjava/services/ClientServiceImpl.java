package ru.itsjava.services;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    private final String serverAddress;
    private final int serverPort;

    public ClientServiceImpl(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @SneakyThrows
    @Override
    public void start(int menuCommand) {
        Socket socket = new Socket(serverAddress, serverPort);
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter clientWriter = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        if (menuCommand == 1) {
            // Авторизация
            System.out.println("Введите свой логин:");
            String login = consoleReader.readLine();

            System.out.println("Введите свой пароль:");
            String password = consoleReader.readLine();

            clientWriter.println("!autho!" + login + ":" + password);
            clientWriter.flush();
            System.out.println("Процесс авторизации завершен!");
        } else if (menuCommand == 2) {
            // Регистрация
            System.out.println("Введите новый логин:");
            String login = consoleReader.readLine();

            System.out.println("Введите новый пароль:");
            String password = consoleReader.readLine();

            clientWriter.println("!reg!" + login + ":" + password);
            clientWriter.flush();
            System.out.println("Процесс регистрации завершен! При ошибке используйте другой логин.");
        } else {
            System.exit(0);
        }

        while ((userInput = consoleReader.readLine()) != null) {
            if (userInput.equalsIgnoreCase("!exit!")) {
                break;
            } else if (userInput.equalsIgnoreCase("!history!")) {
                getChatHistory(serverReader);
            } else {
                clientWriter.println(userInput);
            }
        }
    }


    private void getChatHistory(BufferedReader serverReader) throws IOException {
        String line;
        while ((line = serverReader.readLine()) != null) {
            System.out.println(line);
        }
    }


//public class ClientServiceImpl implements ClientService {
//    public final static int PORT = 8081;
//    public final static String HOST = "localhost";
//
//    @SneakyThrows
//    @Override
//    public void start(int menuCommand) {
//        Socket socket = new Socket(HOST, PORT);
//
//        if (socket.isConnected()) {
//            new Thread(new SocketRunnable(socket)).start();
//
//            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
//            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);
//
//            if (menuCommand == 1) {
//                System.out.println("Введите свой логин:");
//                String login = messageInputService.getMessage();
//
//                System.out.println("Введите свой пароль:");
//                String password = messageInputService.getMessage();
//
//                //!autho!login:password
//                serverWriter.println("!autho!" + login + ":" + password);
//                serverWriter.flush();
//                System.out.println("Процесс авторизации завершен!");
//            } else if (menuCommand == 2) {
//                System.out.println("Введите новый логин:");
//                String login = messageInputService.getMessage();
//
//                System.out.println("Введите новый пароль:");
//                String password = messageInputService.getMessage();
//
//                //!reg!login:password
//                serverWriter.println("!reg!" + login + ":" + password);
//                serverWriter.flush();
//                System.out.println("Процесс регистрации завершен! При ошибке используйте другой логин.");
//            } else {
//                System.exit(0);
//            }
//
//            while (true) {
//                String consoleMessage = messageInputService.getMessage();
//                if (consoleMessage.equals("Exit")) {
//                    System.exit(0);
//                }
//
//                serverWriter.println(consoleMessage);
//                serverWriter.flush();
//            }
//        }
//    }

//    public String incorrectData() throws IOException {
//        Socket socket = new Socket(HOST, PORT);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String messageFromServer = bufferedReader.readLine();
//        return messageFromServer;
//    }
}


