package org.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;


public class TC_2{

    public static String URL = "https://www.lambdatest.com/selenium-playground/";
    @Test
    public void test_TC_2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li//a[contains(text(),'Slider')]")).click();

        Actions ac = new Actions(driver);
        String actSlider = "//div[@id='slider3']//div//input[@value='15']";
        WebElement as = driver.findElement((By.xpath(actSlider)));
        ac.dragAndDropBy(as, 212, 15).build().perform();

        String expSlider = "//div[@id='slider3']//div//output";
        WebElement es = driver.findElement((By.xpath(expSlider)));
        Assert.assertEquals(es.getText(), "95","Slider haven't moved");
        Thread.sleep(2000);
        driver.quit();

    }

}