import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;

@Listeners(IListenerClass.class)

public class ChallengeDOM {
    WebDriver driver;
    String baseUrl;
    WebElement button1,button2,button3,deleteLink,editLink;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/qbuser/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://the-internet.herokuapp.com/challenging_dom";
        driver.get(baseUrl);

    }
    @Test
    public void button1(){
        ArrayList<WebElement> buttons= new ArrayList<WebElement>(driver.findElements(By.cssSelector(".large-2 > a")));
        button1 = buttons.get(0);
        String text1 = button1.getText();
        WebElement answer = driver.findElement(By.id("canvas"));
        button1.click();
        Assert.assertNotEquals(answer,driver.findElement(By.id("canvas")),"Answer Did Not Change");
    }
    @Test
    public void button2(){
        ArrayList<WebElement> buttons= new ArrayList<WebElement>(driver.findElements(By.cssSelector(".large-2 > a")));
        button2 = buttons.get(1);
        String text1 = button2.getText();
        WebElement answer = driver.findElement(By.id("canvas"));
        button2.click();
        Assert.assertNotEquals(answer,driver.findElement(By.id("canvas")),"Answer Did Not Change");
    }
    @Test
    public void button3(){
        ArrayList<WebElement> buttons= new ArrayList<WebElement>(driver.findElements(By.cssSelector(".large-2 > a")));
        button3 = buttons.get(2);
        String text1 = button3.getText();
        WebElement answer = driver.findElement(By.id("canvas"));
        button3.click();
        Assert.assertNotEquals(answer,driver.findElement(By.id("canvas")),"Answer Did Not Change");
    }

    @Test
    public void editButton(){
        editLink = driver.findElement(By.cssSelector("tr>td>a:nth-of-type(1)"));
        baseUrl = driver.getCurrentUrl();
        editLink.click();
        Assert.assertNotEquals(baseUrl,driver.getCurrentUrl(),"Link Did Not Change on Clicking Edit!");

    }

    @Test
    public void delButton(){
        deleteLink = driver.findElement(By.cssSelector("tr>td>a:nth-of-type(2)"));
        baseUrl = driver.getCurrentUrl();
        deleteLink.click();
        Assert.assertNotEquals(baseUrl,driver.getCurrentUrl(),"Link Did Not Change on Clicking Edit!");
    }

    @AfterClass
    public void destruct(){
        driver.close();
    }

}
