package cloud_google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CloudGoogle {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test (description = "Cloud Google")
    public void cloudGoogleComEstimation() {

        driver.get("http://cloud.google.com");

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='q']")));
        WebElement inputSearch = driver.findElement(By.xpath("//input[@name='q']"));
        inputSearch.click();
        inputSearch.sendKeys("Google Cloud Platform Pricing Calculator");
        inputSearch.submit();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='gsc-thumbnail-inside']//a/b[text()='Google Cloud Platform Pricing Calculator']")));
        driver.findElement(By.xpath("//div[@class='gsc-thumbnail-inside']//a/b[text()='Google Cloud Platform Pricing Calculator']")).click();

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//devsite-iframe/iframe")));
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("myFrame")));

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-tab-item//div[text()='Compute Engine']/../../..")));
        driver.findElement(By.xpath("//md-tab-item//div[text()='Compute Engine']/../../..")).click();

        driver.findElement(By.xpath("//input[@ng-model='listingCtrl.computeServer.quantity' and @name='quantity']")).sendKeys("4");

        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.os']")).click();
        driver.findElement(By.xpath("//md-option[@id='select_option_67']")).click();

        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.class']")).click();
        driver.findElement(By.xpath("//md-option[@id='select_option_79']")).click();

        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.series']")).click();
        new WebDriverWait(driver, 5)
                 .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@id='select_option_194']")));
        driver.findElement(By.xpath("//md-option[@id='select_option_194']")).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.instance' and contains(@aria-label, 'n1-standard')]")));
        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.instance']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")));
        driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")).click();

        driver.findElement(By.xpath("//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")).click();
        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.gpuCount']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@value='1' and contains(@ng-disabled,'minGPU')]")));
        driver.findElement(By.xpath("//md-option[@value='1' and contains(@ng-disabled,'minGPU')]")).click();

        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.gpuType']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@value='NVIDIA_TESLA_T4']")));
        driver.findElement(By.xpath("//md-option[@value='NVIDIA_TESLA_V100']")).click();

        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.ssd']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@ng-repeat]/div[contains(text(),'24x375')]")));
        driver.findElement(By.xpath("//md-option[@ng-repeat]/div[contains(text(),'2x375')]")).click();

        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.location']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option[contains(@ng-repeat,'full')]/div[contains(text(),'Montr')]")));
        driver.findElement(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option[contains(@ng-repeat,'full')]/div[contains(text(),'Frank')]")).click();

        driver.findElement(By.xpath("//md-select[@ng-model='listingCtrl.computeServer.cud']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option/div[contains(text(),'3 Year')]")));
        driver.findElement(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option/div[contains(text(),'1 Year')]")).click();

        driver.findElement(By.xpath("//button[@aria-label='Add to Estimate' and contains(@ng-click,'ComputeEngineForm')]")).click();

        //wait for estimation
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-list-item/div/b[contains(text(),'Estimated')]")));

        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'VM class:')]")).getText().matches("(.*)regular(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'Instance type:')]")).getText().matches("(.*)n1-standard-8(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'Region:')]")).getText().matches("(.*)Frankfurt(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'SSD space')]")).getText().matches("(.*)2x375 GiB(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div[contains(text(),'Commitment term:')]")).getText().matches("(.*)1 Year(.*)"));
        Assert.assertTrue(driver.findElement(By.xpath("//md-list-item/div/b[contains(text(),'Estimated')]")).getText().matches("(.*)1,082.77(.*)"));
    }

    @AfterMethod (alwaysRun = true)
    public void browserTearDown(){
        //driver.quit();
        driver=null;
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
