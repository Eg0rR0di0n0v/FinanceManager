import ru.netology.jsonData.JsonData;
import ru.netology.product.ProductsTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProductTests {
    public static ProductsTracker productsTracker;

    @BeforeEach
    public void InitBeforeTest() {
        productsTracker = new ProductsTracker();
    }

    @Test
    public void testGetCategoryWithHighestSum() {
        String currentDate = getCurrentDate();

        Gson gson = new Gson();
        JsonData jsonProductData = gson.fromJson("{\"title\": \"тапки\", \"date\": \"" + currentDate + "\", \"sum\": 500}", JsonData.class);
        productsTracker.addNewProduct(jsonProductData);

        jsonProductData = gson.fromJson("{\"title\": \"тапки\", \"date\": \"" + currentDate + "\", \"sum\": 600}", JsonData.class);
        productsTracker.addNewProduct(jsonProductData);

        jsonProductData = gson.fromJson("{\"title\": \"мыло\", \"date\": \"" + currentDate + "\", \"sum\": 100}", JsonData.class);
        productsTracker.addNewProduct(jsonProductData);

        Assertions.assertEquals(1100, productsTracker.getCategoryWithHighestSum().getSum());
    }

    @Test
    public void testGetJsonSumForCategoryByProductName() {
        String currentDate = getCurrentDate();

        Gson gson = new Gson();
        JsonData jsonProductData = gson.fromJson("{\"title\": \"тапки\", \"date\": \"" + currentDate + "\", \"sum\": 500}", JsonData.class);
        productsTracker.addNewProduct(jsonProductData);

        jsonProductData = gson.fromJson("{\"title\": \"тапки\", \"date\": \"" + currentDate + "\", \"sum\": 600}", JsonData.class);
        productsTracker.addNewProduct(jsonProductData);

        jsonProductData = gson.fromJson("{\"title\": \"мыло\", \"date\": \"" + currentDate + "\", \"sum\": 100}", JsonData.class);
        productsTracker.addNewProduct(jsonProductData);

        Assertions.assertEquals("{" +
                "  \"maxCategory\": {" +
                "    \"category\": \"одежда\"," +
                "    \"sum\": \"1100\"" +
                "  }" +
                "}", productsTracker.getJsonSumForCategoryByProductName(""));
    }

    private static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}