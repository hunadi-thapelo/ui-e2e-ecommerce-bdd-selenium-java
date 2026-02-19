import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://shop.qaautomationlabs.com/index.php"); //
        driver.manage().window().maximize();
        String title = driver.getTitle();
        System.out.println(title); //QA AUTOMATIONLAB
        Assert.assertEquals(title, "QA AUTOMATIONLAB");
        System.out.println("Application is launched!");

        driver.findElement(By.id("email")).sendKeys("demo@demo.com");
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.id("loginBtn")).click();


        //loading that happens when navigating to shop home page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/shop.php"));


        //then an alert dialog displays
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);


        By categoryCard = new ByChained(By.className("offer-text"), By.className("mb-3")); //finding the category name


        List<WebElement> allFourCards = driver.findElements(categoryCard); //finding all category names and storing in a List of webelements type variable
        List<String> getCategories = allFourCards.stream().map(e -> e.getText().trim()).toList(); // this is to get the String for assertion purposes

        List<String> expectedCategories = List.of(
                "Men Fashion",
                "Women Fashion",
                "Kids Fashion",
                "Electronics"
        );

        int numberOfCategories = allFourCards.size();
        Assert.assertEquals(numberOfCategories, 4);
        Assert.assertTrue(getCategories.containsAll(expectedCategories), "Categories mismatch"); //order match does not matter used containsall,

        System.out.println("These are  the category names " + getCategories);

        String categoryName = "Women Fashion";

        for(WebElement deptCategory : allFourCards) {

            String name = deptCategory.getText(); //findElement(getCategories).getText().trim();

            if(name.equalsIgnoreCase(categoryName)) {

                WebElement shopNowButton = driver.findElement(By.cssSelector("a.btn.btn-primary[href='womens-wear.php']"));
                shopNowButton.click();
            }
        }

    }

}
