package org.project;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.net.URL;


public class LambdaTestForMultipleTestCases {
    public RemoteWebDriver driver = null;
    private String username = "himabindu.bandi20";
    private String accessKey = "95pBacU70Lht4Ltd0ta1sD8VyoGn6BSAO1cmFJuWo1lZPRSoma";
    private String hub = "@hub.lambdatest.com/wd/hub";
    DesiredCapabilities caps = new DesiredCapabilities();
    public static String TEST_URL = "https://www.lambdatest.com/selenium-playground/";


    @Parameters(value ={ "Browser", "browserVersion", "platformName", "Name" })
    @BeforeMethod
    public void setUp(String browserName, String browserVersion, String platform, String name)  throws MalformedURLException
    {
        caps.setCapability("browserName", browserName );
        caps.setCapability("browserVersion", browserVersion);
        caps.setCapability("platformName", platform);
        HashMap<String,Object> ItOptions = new HashMap<>();
        ItOptions.put("username", username);
        ItOptions.put("accessKey", accessKey);
        ItOptions.put("visual", true);
        ItOptions.put("video", true);
        ItOptions.put("network", true);
        ItOptions.put("build", "Selenium101_Hima");
        ItOptions.put("project", "Selenium101_Hima");
        ItOptions.put("name", name + ":" + browserName);
        ItOptions.put("console", "true");
        ItOptions.put("w3c", true);
        ItOptions.put("plugin", "java.testNG");
        caps.setCapability("LT:Options", ItOptions);
        try
        {
            driver = new RemoteWebDriver(new URL("https://"+username+":"+accessKey+hub), caps);
        }catch(MalformedURLException exe)
        {
            exe.printStackTrace();
        }
       }


    @Test(timeOut = 20000)
    //simple form entry demo
    public void test_TC_1() {
        driver.navigate().to(TEST_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li//a[contains(text(),'Simple')]")).click();
        String presentUrl = driver.getCurrentUrl();
        Assert.assertTrue(presentUrl.contains("simple-form-demo"), "wrong url");

        //Enter text into text message
        String expText = "Welcome to LambdaTest";
        driver.findElement(By.cssSelector("#user-message")).sendKeys(expText);
        //Click on Get checked value
        driver.findElement(By.cssSelector("#showInput")).click();
        String actText = "//label[contains(text(),'Message')]/following-sibling::p";
        WebElement ap = driver.findElement(By.xpath(actText));
        Assert.assertEquals(expText, ap.getText(), expText + "is not matching " + ap.getText());

    }

    @Test(timeOut = 20000)
    //Slider demo
    public void test_TC_2(){
        driver.navigate().to(TEST_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li//a[contains(text(),'Slider')]")).click();

        Actions ac = new Actions(driver);
        String actSlider = "//div[@id='slider3']";
        WebElement as = driver.findElement((By.xpath(actSlider)));
        ac.dragAndDropBy(as, 195, 0).perform();

        WebElement es = driver.findElement((By.id("rangeSuccess")));
        Assert.assertEquals(es.getText(), "95", "Slider haven't moved");

    }

    @Test()
    //Complete form filling demo
    public void test_TC_3() throws InterruptedException {
        driver.navigate().to(TEST_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li//a[contains(text(),'Input Form Submit')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        WebElement nameTB = driver.findElement(By.cssSelector("#name"));
        String validMsg = nameTB.getAttribute("validationMessage");
        System.out.println("Actual error message displayed" + validMsg);

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
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown()
    {
    driver.quit();
    }
}