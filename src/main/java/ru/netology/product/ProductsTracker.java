package ru.netology.product;

import ru.netology.jsonData.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class ProductsTracker {
        private final Map<String, Product> trackedProducts = new HashMap<>();
        private final Map<String, String> hashProducts = new HashMap<>();

    public ProductsTracker(){
            loadExistingCategories();
        }

        private void loadExistingCategories(){
            try {
                File myObj = new File("categories.tsv");


                if(!myObj.exists()){
                    myObj.createNewFile();

                    String template = "булка\tеда\n" +
                            "колбаса\tеда\n" +
                            "сухарики\tеда\n" +
                            "курица\tеда\n" +
                            "тапки\tодежда\n" +
                            "шапка\tодежда\n" +
                            "мыло\tбыт\n" +
                            "акции\tфинансы";
                    Files.writeString(Path.of(myObj.getPath()), template);
                    System.out.println("create file with category, Load app.");
                } else{
                    System.out.println("Find file with category, Load app.");
                }

                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    String[] splitData = data.split("\t");

                    if(splitData.length == 2){
                        Product currentCategory;
                        if(trackedProducts.containsKey(splitData[1])){
                            currentCategory = trackedProducts.get(splitData[1]);
                        } else {
                            currentCategory = new Product(splitData[1]);
                        }
                        currentCategory.addProduct(splitData[0]);
                        hashProducts.put(splitData[0], splitData[1]);
                        trackedProducts.put(splitData[1], currentCategory);
                    }
                    System.out.println(data);
                }
                myReader.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public Product getCategoryWithHighestSum(){
            int maxSum = -1;
            Product result = null;

            for (Map.Entry<String, Product> entry : trackedProducts.entrySet()){
                if (entry.getValue().getSum() > maxSum){
                    maxSum = entry.getValue().getSum();
                    result = entry.getValue();
                }
            }

            return result;
        }

        public void addNewProduct(JsonData product){

            if(hashProducts.containsKey(product.title)){
                Product prCat = trackedProducts.get(hashProducts.get(product.title));
                prCat.trackSum(product);
            } else {
                Product currentCategory;
                if(trackedProducts.containsKey("Another")){
                    currentCategory = trackedProducts.get("Another");
                } else {
                    currentCategory = new Product("Another");
                }
                currentCategory.addProduct(product.title);
                currentCategory.trackSum(product);
                hashProducts.put(product.title, "Another");
                trackedProducts.put("Another", currentCategory);
            }

            System.out.println("Point " + product.title + " load in category " + hashProducts.get(product.title) + " on sum: " + product.sum);
        }

        public String getJsonSumForCategoryByProductName(String productName){
            Product productsCategory = getCategoryWithHighestSum();

            return "{" +
                    "  \"maxCategory\": {" +
                    "    \"category\": \"" + productsCategory.getCategoryName() + "\"," +
                    "    \"sum\": \"" + productsCategory.getSum() + "\"" +
                    "  }" +
                    "}";
        }
    }
