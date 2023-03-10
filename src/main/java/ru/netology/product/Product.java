package ru.netology.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.netology.jsonData.*;

public class Product {
    private final Set<String> products = new HashSet<>();
    private final String categoryName;
    private int sum;

    private final List<JsonData> addedData = new ArrayList<>();

    public Product(String categoryName){
        this.categoryName = categoryName;
    }

    public boolean isProductExistsInCategory(String productName){
        return products.contains(productName);
    }

    public void addProduct(String productName){
        products.add(productName);
    }

    public boolean trackSum(String productName, int cost, String date){
        if(isProductExistsInCategory(productName)){
            sum += cost;

            JsonData jsonProductData = new JsonData();
            jsonProductData.title = productName;
            jsonProductData.sum = sum;
            jsonProductData.date = date;

            addedData.add(jsonProductData);
            return true;
        }

        return false;
    }

    public void trackSum(JsonData jsonProductData){
        if(isProductExistsInCategory(jsonProductData.title)){
            sum += jsonProductData.sum;

            addedData.add(jsonProductData);
        }

    }

    public Set<String> getProducts() {
        return products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getSum() {
        return sum;
    }
}