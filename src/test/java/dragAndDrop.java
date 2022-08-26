import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
@Listeners(IListenerClass.class)
public class dragAndDrop {
    WebDriver driver;
    @BeforeClass
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver", "/home/qbuser/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        String baseUrl = "http://the-internet.herokuapp.com/drag_and_drop";
        driver.get(baseUrl);
    }


    @Test
    public void dragnDrop() throws  InterruptedException{
        WebElement fromDiv = driver.findElement(By.cssSelector("#column-a"));
        String fromDivHeader = driver.findElement(By.cssSelector("#column-a>header")).getText();
        WebElement toDiv = driver.findElement(By.cssSelector("#column-b"));
        Actions act=new Actions(driver);
//        act.dragAndDrop(fromDiv, toDiv).build().perform();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                + "var dropEvent = createEvent('drop');\n"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                + "var dragEndEvent = createEvent('dragend');\n"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                + "simulateHTML5DragAndDrop(source,destination);", fromDiv, toDiv);
        Thread.sleep(3000);
        String fromDivHeader2 = driver.findElement(By.cssSelector("#column-a>header")).getText();
        Assert.assertNotEquals(fromDivHeader2,fromDivHeader,"Item was not replaced");
    }

    @AfterClass
    public void destruct(){
        driver.close();
    }
}
