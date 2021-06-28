package hurt_me_plenty.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String HOMEPAGE_URL = "http://cloud.google.com";
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='q']")));
        return this;
    }

    public SearchResultsPage searchForTerms(String term) {
        searchInput.sendKeys(term);
        searchInput.submit();
        return new SearchResultsPage(driver, term);
    }

}
