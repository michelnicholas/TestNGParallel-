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

public class TestNGParallelTestsDataProvider {

    private WebDriver driver;

    @BeforeMethod
    void setDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    void closeDriver(){
        driver.quit();
    }

    @DataProvider(name = "browsers" , parallel = true)
    Object[][] testBrowsers(){
        return new Object[][]{
                {"Selenium WebDriver"} ,
                {"TestNG"} ,
        };
    }



    @Test(dataProvider = "browsers")
    void searchTest(String searchText){
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(searchText);
        element.submit();

        Assert.assertTrue(driver.getTitle().startsWith(searchText));

        System.out.println(searchText + ":" + Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
    }



}
