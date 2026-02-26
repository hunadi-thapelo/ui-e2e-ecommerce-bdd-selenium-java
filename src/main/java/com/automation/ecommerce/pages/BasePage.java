package com.automation.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); //This call sets the WebElement fields
    }

    protected void clearAndType(WebElement element, String text) { //added here because this is a common WebDriver actions
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys();
    }
}
