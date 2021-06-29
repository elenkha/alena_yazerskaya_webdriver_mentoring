package bring_it_on.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String HOMEPAGE_URL = "http://paste.ubuntu.com";
    private static final String XPATH_POSTER_INPUT = "//input[@name='poster']";
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_POSTER_INPUT)));
        return this;
    }

    public PasteResultPage doPaste(String poster, String syntax, String expiration, String code) {
        driver.findElement(By.xpath(XPATH_POSTER_INPUT)).sendKeys(poster);
        driver.findElement(By.id("id_syntax")).click();
        driver.findElement(By.id("id_syntax")).sendKeys(syntax);
        driver.findElement(By.id("id_expiration")).click();
        driver.findElement(By.xpath("//select[@id='id_expiration']/option[@value='"+ expiration + "']")).click();
        driver.findElement(By.id("id_content")).sendKeys(code);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        return new PasteResultPage(driver);
    }

}
