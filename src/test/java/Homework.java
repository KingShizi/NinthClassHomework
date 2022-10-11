
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Homework {

    private static WebDriver driver;
    public static final String CHROMEDRIVER_PATH = "/Users/shirazelbaz/Downloads/chromedriver";

    @BeforeClass
    public void beforerun(){
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    public void test01_calc(){
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        WebElement seven = driver.findElement(By.id("seven"));
        System.out.println(seven.getSize());
        WebElement six = driver.findElement(By.id("six"));
        System.out.println(six.isDisplayed());
        String num = "42";
        WebElement multiply = driver.findElement(By.id("multiply"));
        WebElement equal = driver.findElement(By.id("equal"));
        seven.click();
        multiply.click();
        six.click();
        equal.click();
        Assert.assertEquals(driver.findElement(By.id("screen")).getText(), num);
    }

    @Test
    public void test02_assertUrl(){
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        String url = "https://dgotlieb.github.io/WebCalculator/";
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void test03_assertWebsiteTitle(){
        String websiteTitle = driver.getTitle();
        driver.navigate().refresh();
        Assert.assertEquals(websiteTitle, driver.getTitle());

    }

    @Test
    public void test04_noExtentonsOnBrowserOption(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        ChromeDriver driver2 = new ChromeDriver(options);
        driver2.get("https://www.youtube.com/");

    }

    @Test
    public void test05_screenShotAndDoubleClick(){
        driver.get("https://dgotlieb.github.io/Actions/");
        WebElement box = driver.findElement(By.id("div1"));
        File screenShotFile = box.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShotFile, new File("element-screenshot.png")); }
        catch (IOException e) {
            e.printStackTrace();
        }
        Actions doubleClick = new Actions(driver);
        WebElement doubleClickElement = driver.findElement(By.cssSelector("p[ondblclick=\"doubleClickFunction()\"]"));
        doubleClick.doubleClick(doubleClickElement);
        doubleClick.perform();

        String textAfter = driver.findElement(By.id("demo")).getText();
        Assert.assertEquals("You double clicked", textAfter);

    }

    @Test
    public void test06_mouseHover(){
        driver.get("https://dgotlieb.github.io/Actions/");
        Actions hover = new Actions(driver);
        WebElement X = driver.findElement(By.id("close"));
        hover.moveToElement(X).perform();
    }

    @Test
    public void test07_selectTwoFromList(){
        driver.get("https://dgotlieb.github.io/Actions/");
        Actions selectMulti = new Actions(driver);
        List<WebElement> list = driver.findElements(By.name("kind"));
        selectMulti.clickAndHold(list.get(0)).clickAndHold(list.get(1)).click();
        selectMulti.build().perform();

    }

    @Test
    public void test08_uploadFileAndScrollDown(){
        driver.get("https://dgotlieb.github.io/Actions/");
        driver.findElement(By.name("pic")).sendKeys("/Users/shirazelbaz/Downloads/chromedriver");
//        Scroll to element
        WebElement clickButton = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickButton);
//        scroll down
//        JavascriptExecutor scrollDown = (JavascriptExecutor) driver;
//        scrollDown.executeScript("javascript:window.scrollBy(1100,800)");

    }

    @Test
    public void test09_selectOptionAndSelect(){
        driver.get("https://dgotlieb.github.io/Controllers/");
        List<WebElement> radioButtonList = driver.findElements(By.name("group1"));
        for (WebElement webElement : radioButtonList) {
            if (webElement.getAttribute("value").equals("Cheese")) {
                webElement.click();
            }
            System.out.println(webElement.getAttribute("value"));

        }
        Select dropBox = new Select(driver.findElement(By.name("dropdownmenu")));
        dropBox.selectByValue("Milk");
        for (int i = 0; i < dropBox.getOptions().size(); i++) {
            System.out.println(dropBox.getOptions().get(i).getText());
        }
    }

    @Test
    public void test10_heightAndWidth(){
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driver.findElement(By.id("two")).getRect().getHeight());
        System.out.println(driver.findElement(By.id("six")).getRect().getWidth());
    }

    @Test
    public void test11_challengeOne(){
        driver.get("https://www.themarker.com/");
        int count = 0;
        String bodyText = driver.getPageSource();
        // search for the String within the text
        while (bodyText.contains("news")){

            // when match is found, increment the count
            count++;

            // continue searching from where you left off
            bodyText = bodyText.substring(bodyText.indexOf("news") + "news".length());
        }
        System.out.println(count);

    }

    @Test
    public void test12_printPage(){
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        ((JavascriptExecutor) driver).executeScript("print()");
    }




    @AfterClass
    public void after() {
        //driver.quit();
    }
}
