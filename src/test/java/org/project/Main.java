package org.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    @Test
    public void openUrl() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://lambdatest.github.io/sample-todo-app/");
        Thread.sleep(5000);

        String pageTitle = driver.getTitle();
        System.out.println("page title" + pageTitle);
        driver.quit();

    }

}