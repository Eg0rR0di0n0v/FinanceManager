package ru.netology;

import com.google.gson.Gson;
import ru.netology.jsonData.*;
import ru.netology.product.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            ProductsTracker productsTracker = new ProductsTracker();
            System.out.println("Server started!");
            while (true) {
                System.out.println("Server waiting connect client");
                try (
                        Socket socket = serverSocket.accept();
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    System.out.println("Connect client " + socket.getPort());
                    String inString = reader.readLine();


                    Gson gson = new Gson();
                    JsonData jsonProductData = gson.fromJson(inString, JsonData.class);
                    productsTracker.addNewProduct(jsonProductData);


                    String outJsonData = productsTracker.getJsonSumForCategoryByProductName("");

                    System.out.println("Create json for client: ");
                    System.out.println(outJsonData);

                    writer.println(outJsonData);

                } catch (IOException e) {
                    System.out.println("Не могу стартовать сервер");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}