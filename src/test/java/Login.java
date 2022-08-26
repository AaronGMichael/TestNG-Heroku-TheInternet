import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners(IListenerClass.class)
public class Login {

    WebDriver driver;


    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/qbuser/ChromeDriver/chromedriver");
        driver = new ChromeDriver();

    }
    @DataProvider(name = "loginData")
    public Object[][] createLoginData() {
        return new Object[][] {
                { "tomsmith", "SuperSecretPassword!"},
                { "tom smith", "SuperSecretPassword!"},
        };
    }

    @Test(dataProvider = "loginData")
    public void login(String inpUser, String inpPass)throws InterruptedException{
        String baseUrl = "http://the-internet.herokuapp.com/login";
        driver.get(baseUrl);
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys(inpUser);
        password.sendKeys(inpPass);
        WebElement button = driver.findElement(By.cssSelector("button"));
        button.click();
        WebElement flashMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2"));
        Thread.sleep(1000);
        String message = flashMessage.getText();
        Assert.assertTrue(message.equals("Secure Area")||warningShown(),"Login Functionality Works!");

    }
    public boolean warningShown(){
        String classes = driver.findElement(By.id("flash")).getAttribute("class");
            boolean warning = false;
            for (String c : classes.split(" ")) {
                if (c.equals("error")) {
                    warning=true;
                }
            }
            return warning;
    }

    @AfterClass
    public void destruct(){
        driver.close();
    }
}
