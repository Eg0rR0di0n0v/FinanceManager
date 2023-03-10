package ru.netology.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Client {
    public static void main(String... args) {
        while (true) {
            try (Socket clientSocket = new Socket("localhost", 8989);
                 PrintWriter writerSocket = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader readerSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                String currentCommand = reader.readLine();
                String[] subSplitCommand = currentCommand.split(" ");

                if (currentCommand.equalsIgnoreCase("end") ||
                        currentCommand.equalsIgnoreCase("exit") ||
                        currentCommand.equalsIgnoreCase("quit")) {
                    System.out.println("stop");
                    break;}
                else if (subSplitCommand.length > 1 && subSplitCommand[0].equalsIgnoreCase("add")) {
                    String currentDate = getCurrentDate();
                    int sum = Integer.parseInt(subSplitCommand[2]);

                    writerSocket.println("{\"title\": \"" + subSplitCommand[1] + "\", \"date\": \"" + currentDate + "\", \"sum\": " + sum + "}");
                    System.out.println("app on server.");

                    System.out.println("answer on server:");
                    System.out.println(readerSocket.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private static String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}


