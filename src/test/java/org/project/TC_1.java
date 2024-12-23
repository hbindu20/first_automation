package org.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class TC_1 {

    public static String URL = "https://www.lambdatest.com/selenium-playground/";
    @Test
    public void test_TC_1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.findElement(By.xpath("//li//a[contains(text(),'Simple')]")).click();
        String presentUrl = driver.getCurrentUrl();
        Assert.assertTrue(presentUrl.contains("simple-form-demo"), "wrong url");

        //Enter text into text message
        String expText = "Welcome to LambdaTest";
        driver.findElement(By.cssSelector("#user-message")).sendKeys(expText);
        Thread.sleep(3000);
        //Click on Get checked value
        driver.findElement(By.cssSelector("#showInput")).click();
        String actText = "//label[contains(text(),'Message')]/following-sibling::p";
        WebElement ap = driver.findElement(By.xpath(actText));
        Assert.assertEquals(expText, ap.getText(), expText + "is not matching " + ap.getText());
        Thread.sleep(5000);
        driver.quit();





    }

}