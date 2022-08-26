import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import org.testng.annotations.Listeners;
@Listeners(IListenerClass.class)
public class NewTab {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/qbuser/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        String baseUrl = "http://the-internet.herokuapp.com/windows";
        driver.get(baseUrl);
    }

    @Test
    public void newTab(){
        WebElement link = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
        link.click();
        boolean newTab = false;
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        if(driver.getTitle().equals("New Window")){
            newTab=true;
        }
        driver.close();
        driver.switchTo().window(tabs2.get(0));
        Assert.assertTrue(newTab,"New Tab not opened");
    }
    @AfterClass
    public void destruct(){
        driver.close();
    }
}
