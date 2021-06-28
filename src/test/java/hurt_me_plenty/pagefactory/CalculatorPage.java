package hurt_me_plenty.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CalculatorPage {

    private WebDriver driver;

    //use dynamic locator with "contains", split and iteration over the list of search terms
    private String calcParamLocator = "//md-select[@ng-model='listingCtrl.computeServer.%s";

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CalculatorPage toComputeEngine(){
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//devsite-iframe/iframe")));
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("myFrame")));

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-tab-item//div[text()='Compute Engine']/../../..")));
        driver.findElement(By.xpath("//md-tab-item//div[text()='Compute Engine']/../../..")).click();
        return this;
    }

    public CalculatorPage setQuantity(int quantity) {
        driver.findElement(By.xpath("//input[@ng-model='listingCtrl.computeServer.quantity' and @name='quantity']")).sendKeys(String.valueOf(quantity));
        return this;
    }

    public CalculatorPage setOs() {
        driver.findElement(By.xpath(buildLocatorForCalcParam("os"))).click();
        driver.findElement(By.xpath("//md-option[@id='select_option_67']")).click();
        return this;
    }

    public CalculatorPage setMachineClass(){
        driver.findElement(By.xpath(buildLocatorForCalcParam("class"))).click();
        driver.findElement(By.xpath("//md-option[@id='select_option_79']")).click();
        return this;
    }

    public CalculatorPage setMachineSeries() {
        driver.findElement(By.xpath(buildLocatorForCalcParam("series"))).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@id='select_option_197']")));
        driver.findElement(By.xpath("//md-option[@id='select_option_194']")).click();
        return this;
    }

    public CalculatorPage setMachineType() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(buildLocatorForCalcParam("instance", "contains(@aria-label, 'n1-standard')"))));
        driver.findElement(By.xpath(buildLocatorForCalcParam("instance"))).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-HIGHCPU-96']")));
        driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")).click();
        return this;
    }

    public CalculatorPage setGpu() {
        driver.findElement(By.xpath("//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")).click();
        driver.findElement(By.xpath(buildLocatorForCalcParam("gpuCount"))).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@value='1' and contains(@ng-disabled,'minGPU')]")));
        driver.findElement(By.xpath("//md-option[@value='1' and contains(@ng-disabled,'minGPU')]")).click();
        return  this;
    }

    public CalculatorPage setGpuType() {
        driver.findElement(By.xpath(buildLocatorForCalcParam("gpuType"))).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@value='NVIDIA_TESLA_T4']")));
        driver.findElement(By.xpath("//md-option[@value='NVIDIA_TESLA_V100']")).click();
        return this;
    }

    public CalculatorPage setSsd() {
        driver.findElement(By.xpath(buildLocatorForCalcParam("ssd"))).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option[@ng-repeat]/div[contains(text(),'24x375')]")));
        driver.findElement(By.xpath("//md-option[@ng-repeat]/div[contains(text(),'2x375')]")).click();
        return this;
    }

    public CalculatorPage setLocation() {
        driver.findElement(By.xpath(buildLocatorForCalcParam("location"))).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option[contains(@ng-repeat,'full')]/div[contains(text(),'Montr')]")));
        driver.findElement(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option[contains(@ng-repeat,'full')]/div[contains(text(),'Frank')]")).click();
        return this;
    }

    public CalculatorPage setCud() {
        driver.findElement(By.xpath(buildLocatorForCalcParam("cud"))).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option/div[contains(text(),'3 Year')]")));
        driver.findElement(By.xpath("//div[@aria-hidden='false']/md-select-menu//md-option/div[contains(text(),'1 Year')]")).click();
        return this;
    }

    public CalculatorPage runCalculation() {
        driver.findElement(By.xpath("//button[@aria-label='Add to Estimate' and contains(@ng-click,'ComputeEngineForm')]")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-list-item/div/b[contains(text(),'Estimated')]")));
        return this;
    }

    private String buildLocatorForCalcParam(String param) {
        String locatorForParam = String.format(calcParamLocator + "']", param);
        return locatorForParam;
    }

    private String buildLocatorForCalcParam(String param, String addon) {
        String locatorForParam = String.format(calcParamLocator + "' and %s]", param, addon);
        return locatorForParam;
    }

}
