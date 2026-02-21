import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginTest {

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
    public void validLoginAndVerifyShopDepartments() {

        driver.get("https://shop.qaautomationlabs.com/index.php"); //
        Assert.assertEquals(driver.getTitle(), "QA AUTOMATIONLAB", "Title name mismatch");

        driver.findElement(By.id("email")).sendKeys("demo@demo.com");
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.id("loginBtn")).click();


        //loading that happens when navigating to shop home page
        wait.until(ExpectedConditions.urlContains("/shop.php"));


        //test case: verify that all 4 category cards are displayed and validate the category names

        //finding all category names and storing in a List of webElements type variable
        //Using List to allow order of insertion and duplicates because we want all categories that are displayed

//        List<WebElement> shopDepartments = driver.findElements(By.cssSelector("div.product-offer"));
//        System.out.println("Shop departments details: " +shopDepartments);
//
//        List<String> getDepartmentNames = shopDepartments.stream().map(e -> e.getText().trim()).toList();
//        // this is to get the String for assertion purposes
//        System.out.println("Get departments names: " +getDepartmentNames);


        List<WebElement> shopDepartmentList = driver.findElements(By.cssSelector("div.product-offer"));
        List<String> actualShopDepartments = new ArrayList<>();

        List<String> expectedShopDepartments = List.of(
                "Men Fashion",
                "Women Fashion",
                "Kids Fashion",
                "Electronics"
        );


        for (WebElement name : shopDepartmentList) {
            String getDepartmentName = name.findElement(By.cssSelector("div.offer-text > h3")).getText().trim();
            actualShopDepartments.add(getDepartmentName);

        }

        Assert.assertEqualsNoOrder(
                actualShopDepartments.toArray(),
                expectedShopDepartments.toArray(),
                "Department names mismatch."
        );

        Assert.assertEquals(actualShopDepartments.size(), 4, "Count of departments mismatch"); // assert number of categories
        Assert.assertTrue(actualShopDepartments.containsAll(expectedShopDepartments), "Categories mismatch"); //order match does not matter used containsall,

        driver.quit();
    }
    @Test
    public void navigateToWomenFashion() {
        driver.get("https://shop.qaautomationlabs.com/index.php"); //
        Assert.assertEquals(driver.getTitle(), "QA AUTOMATIONLAB", "Title name mismatch");

        driver.findElement(By.id("email")).sendKeys("demo@demo.com");
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.id("loginBtn")).click();
        wait.until(ExpectedConditions.urlContains("/shop.php"));

        List<WebElement> shopDepartmentList = driver.findElements(By.cssSelector("div.product-offer"));
        String targetCategory = "Women Fashion";


        for (WebElement name : shopDepartmentList) {
            String getDepartmentName = name.findElement(By.cssSelector("div.offer-text > h3")).getText().trim();
            if(getDepartmentName.equalsIgnoreCase(targetCategory)) {
                WebElement shopNowButton = driver.findElement(
                        By.cssSelector("a.btn.btn-primary[href='womens-wear.php']"));
                shopNowButton.click();
                break;
            }
        }

        driver.quit();
    }

}
