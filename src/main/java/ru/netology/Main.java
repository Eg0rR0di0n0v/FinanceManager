package ru.netology;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8989)) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                 {
                    // обработка одного подключения
                        System.out.println("Подключен клиент " + socket.getPort());

                        Scanner scanner = new Scanner(System.in);
                        writer.println(scanner.nextLine());

                        writer.println("Hi from server");

                        System.out.println(reader.readLine());
                }
            } catch(IOException e){
                System.out.println("Не могу стартовать сервер");
                e.printStackTrace();
            }
        }
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}