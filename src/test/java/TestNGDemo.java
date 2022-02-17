import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDemo {

    public static WebDriver driver;

    @BeforeMethod
    void setUpMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://google.com");
    }

    @AfterMethod
    void tearDownMethod(){
        driver.quit();
    }


    @Test(dataProvider = "calc-data")
    void calcTest(String input,String expected){
        WebElement element = driver.findElement(By.xpath("//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
        element.sendKeys(input);
        element.submit();

        WebElement result = driver.findElement(By.cssSelector("#cwos"));
        Assert.assertEquals(result.getText(), expected);
    }


    // using data provider to parse in data thru two array objects
    @DataProvider(name = "calc-data")
    Object[][] testData() {
        return new Object[][]{
        {"2 + 3", "5"},
        {"sqrt 16", "4"},
        {"3 - 2", "1"}
    };
    }






    }

