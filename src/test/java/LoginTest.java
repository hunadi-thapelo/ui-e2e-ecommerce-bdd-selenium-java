import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

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

        driver.quit(); //end the session // recommended
    }

}
