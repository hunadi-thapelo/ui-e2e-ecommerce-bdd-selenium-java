package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartSummary {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void testSetUp() {

        //then an alert dialog displays
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void goToCartSummary() {

        driver.get("https://shop.qaautomationlabs.com/index.php"); //
        driver.findElement(By.id("email")).sendKeys("demo@demo.com");
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.id("loginBtn")).click();
        wait.until(ExpectedConditions.urlContains("/shop.php"));

        By shopNowButton = By.cssSelector("a.btn.btn-primary[href='womens-wear.php']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(shopNowButton));
        driver.findElement(shopNowButton).click();

        //"product-list"
        WebElement productList = driver.findElement(By.id("product-list"));
        List<WebElement> allListedProducts = productList.findElements(By.cssSelector(".product-item"));
        String targetProductItem = "Plazo Set";


        for (WebElement productItem : allListedProducts) {

            WebElement productElement = productItem.findElement(By.cssSelector("a.h6"));
            String productName = productElement.getText().trim();

            if(productName.equalsIgnoreCase(targetProductItem)) {
                productItem.findElement(
                        By.cssSelector("button.addToCart")).click();
                break;
            }
        }

        //click on Cart icon
        driver.findElement(By.id("cartdesk")).click();


    }

}
