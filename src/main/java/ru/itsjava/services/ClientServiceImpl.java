package ru.itsjava.services;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    @SneakyThrows
    @Override
    public void start(int menuCommand) {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);

            if (menuCommand == 1) {
                System.out.println("Введите свой логин:");
                String login = messageInputService.getMessage();

                System.out.println("Введите свой пароль:");
                String password = messageInputService.getMessage();

                //!autho!login:password
                serverWriter.println("!autho!" + login + ":" + password);
                serverWriter.flush();
                System.out.println("Процесс авторизации завершен. Возникновение ошибки с отправкой сообщений означает отсутствие авторизации, пройдите регистрацию!  ");
            } else if (menuCommand == 2){
                System.out.println("Введите новый логин:");
                String login = messageInputService.getMessage();

                System.out.println("Введите новый пароль:");
                String password = messageInputService.getMessage();

                //!regist!login:password
                serverWriter.println("!regist!" + login + ":" + password);
                serverWriter.flush();
                System.out.println("Процесс регистрации завершен!");
            } else {
                System.exit(0);
            }

            while (true) {
                String consoleMessage = messageInputService.getMessage();
                if (consoleMessage.equals("Exit")) {
                    System.exit(0);
                }

                serverWriter.println(consoleMessage);
                serverWriter.flush();
            }
        }
    }
}
