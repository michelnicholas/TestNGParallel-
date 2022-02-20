import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNgParallelTests {

    private WebDriver driver;


    @BeforeMethod
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    void cleanUp(){
        driver.quit();
    }


    @Test
    void calcTest(){
        driver.get("http://google.com");

        WebElement element = driver.findElement(By.xpath("//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
        element.sendKeys("sqrt 16");
        element.submit();

        WebElement result = driver.findElement(By.cssSelector("#cwos"));
        Assert.assertEquals(result.getText(),"4");

    }

    @Test
    void searchTest(){
        driver.get("http://google.com");

        WebElement element = driver.findElement(By.xpath("//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
        element.sendKeys("Selenium WebDriver");
        element.submit();

        WebElement result = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/input[1]"));
        Assert.assertTrue(driver.getTitle().startsWith("Selenium WebDriver"));
    }




}
