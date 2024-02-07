package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest{
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    boolean checkProduct(ChromeDriver driver, String name, int quantity){
        boolean success = true;
        List<WebElement> productInfo = driver.findElements(By.tagName("td"));

        WebElement productName = productInfo.get(0);
        WebElement productQuantity = productInfo.get(1);

        success = success && (name.equals(productName.getText()));
        success = success && (String.valueOf(quantity).equals(productQuantity.getText()));
        return success;
    }
    void fillInput(ChromeDriver driver, String name, int quantity){
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(quantity));
        driver.findElement(By.className("btn")).click();
    }

    void createProduct(ChromeDriver driver, String name, int quantity){
        driver.get(String.format(baseUrl+"/product/create"));
        fillInput(driver, name, quantity);
    }
    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception{
        String name = "Sampo Keren";
        int quantity = 1234321;
        createProduct(driver, name, quantity);
        assertTrue(checkProduct(driver, name, quantity));
    }
}