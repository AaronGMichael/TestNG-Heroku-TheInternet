import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import org.testng.annotations.Listeners;
@Listeners(IListenerClass.class)

public class DisappearingElements {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/qbuser/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        String baseUrl = "http://the-internet.herokuapp.com/disappearing_elements";
        driver.get(baseUrl);
    }

    @Test
    public void disappearingElements() throws InterruptedException{


        List<WebElement> countOfLi = driver.findElements(By.cssSelector("div > ul > li"));
        Thread.sleep(2000);
        Assert.assertEquals(countOfLi.size(),5,"Gallery was not present");
    }
    @AfterClass
    public void destruct(){
        driver.close();
    }
}
