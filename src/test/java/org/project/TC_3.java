package org.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org. openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;


public class TC_3{

    public static String URL = "https://www.lambdatest.com/selenium-playground/";
    @Test
    public void test_TC_3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li//a[contains(text(),'Input Form Submit')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        WebElement nameTB = driver.findElement(By.cssSelector("#name"));
        String validMsg = nameTB.getAttribute("validationMessage");
        Assert.assertEquals("Please fill out this field.", validMsg);

        //Enter Details into Form
        nameTB.sendKeys("Hima");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("himabindu.bandi20@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("345675");
        driver.findElement(By.xpath("//input[@placeholder='Company']")).sendKeys("xyz");
        driver.findElement(By.xpath("//input[@placeholder='Website']")).sendKeys("lambda");
        Select sel = new Select(driver.findElement(By.cssSelector("[name='country']")));
        sel.selectByVisibleText("United States");
        driver.findElement(By.xpath("//input[@placeholder='City']")).sendKeys("LA");
        driver.findElement(By.xpath("//input[@placeholder='City']")).sendKeys("LA");
        driver.findElement(By.xpath("//input[@placeholder='Address 1']")).sendKeys("asd");
        driver.findElement(By.xpath("//input[@placeholder='Address 2']")).sendKeys("wer");
        driver.findElement(By.xpath("//input[@placeholder='State']")).sendKeys("SA");
        driver.findElement(By.xpath("//input[@placeholder='Zip code']")).sendKeys("76543");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
        WebElement successMsg = driver.findElement(By.xpath("//p[contains(@class, 'success-msg')]"));
        Assert.assertEquals("Thanks for contacting us, we will get back to you shortly.", successMsg.getText());
        Thread.sleep(2000);
        driver.quit();

    }

}