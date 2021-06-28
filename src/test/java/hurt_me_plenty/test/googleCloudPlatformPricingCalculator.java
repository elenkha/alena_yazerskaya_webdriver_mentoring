package hurt_me_plenty.test;

import hurt_me_plenty.pagefactory.CalculatorPage;
import hurt_me_plenty.pagefactory.HomePage;
import hurt_me_plenty.pagefactory.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import hurt_me_plenty.pagefactory.HomePage;

public class googleCloudPlatformPricingCalculator
 {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Bla-bla")
    public void commonSearchTermResultsAreNotEmpty() {

        CalculatorPage calculatorPage = new HomePage(driver)
                .openPage()
                .searchForTerms("Google Cloud Platform Pricing Calculator")
                .toCalculatorPage()
                .toComputeEngine()
                .setQuantity(4)
                .setOs()
                .setMachineClass()
                .setMachineSeries()
                .setMachineType()
                .setGpu()
                .setGpuType()
                .setSsd()
                .setLocation()
                .setCud()
                .runCalculation();

        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'VM class:')]")).getText().matches("(.*)regular(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'Instance type:')]")).getText().matches("(.*)n1-standard-8(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'Region:')]")).getText().matches("(.*)Frankfurt(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'SSD space')]")).getText().matches("(.*)2x375 GiB(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'Commitment term:')]")).getText().matches("(.*)1 Year(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div/b[contains(text(),'Estimated')]")).getText().matches("(.*)1,082.77(.*)"));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        //driver.quit();
        driver = null;
    }
}
