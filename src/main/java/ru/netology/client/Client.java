package ru.netology.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String... args) {


        try (Socket socket = new Socket("localhost",8989);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println(reader.readLine());
                writer.println(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

