import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
@Listeners(IListenerClass.class)
public class Slider {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/qbuser/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        String baseUrl = "http://the-internet.herokuapp.com/horizontal_slider";
        driver.get(baseUrl);
    }


    @Test
    public void slider(){
        WebElement slider = driver.findElement(By.cssSelector("input[type=\"range\""));
        WebElement sliderVal = driver.findElement(By.cssSelector("#range"));
        boolean sliderWorks =  true;
        float value;
        float val=0;
        for (int i = 1; i <= 10; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            value=Float.parseFloat(sliderVal.getText());
            val += 0.5;
            if(value==val){
                continue;
            }
            else{
                sliderWorks = false;
            }
        }
        for (int i = 1; i <= 10 ; i++) {
            slider.sendKeys(Keys.ARROW_LEFT);
            value=Float.parseFloat(sliderVal.getText());;
            val -= 0.5;
            if(value==val){
                continue;
            }
            else{
                sliderWorks = false;
            }
        }
        Assert.assertTrue(sliderWorks,"Slider Not Working Correctly");

    }

    @AfterClass
    public void destruct(){
        driver.close();
    }
}
