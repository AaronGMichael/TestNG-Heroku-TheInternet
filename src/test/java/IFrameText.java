import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
@Listeners(IListenerClass.class)
public class IFrameText {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/qbuser/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        String baseUrl = "http://the-internet.herokuapp.com/iframe";
        driver.get(baseUrl);

    }

    @Test
    public void iFrameText() throws InterruptedException{
        Thread.sleep(500);
        driver.switchTo().frame("mce_0_ifr");
        Thread.sleep(500);
        WebElement innerText = driver.findElement(By.cssSelector("body>p"));
        Thread.sleep(500);
        String oldText = innerText.getText();
        innerText.sendKeys("This is some new Text");
        Thread.sleep(500);
        String newText = driver.findElement(By.cssSelector("body>p")).getText();
        Assert.assertNotEquals(oldText,newText,"Text was Not Changed!");
    }

    @AfterClass
    public void destruct(){
        driver.close();
    }
}
