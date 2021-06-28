package hurt_me_plenty.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private WebDriver driver;
    private String searchTerm;

    private String searchResultLocator = "//div[@class='gsc-thumbnail-inside']//a/b[text()='%s']";

    public SearchResultsPage(WebDriver driver, String searchTerm) {
        this.searchTerm = searchTerm;
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(buildLocatorForSearchResult())));
        driver.findElement(By.xpath(buildLocatorForSearchResult())).click();
    }

    public CalculatorPage toCalculatorPage() {
        return new CalculatorPage(driver);
    }

    private String buildLocatorForSearchResult() {
        String locatorForSearch = String.format(searchResultLocator, searchTerm);
        return locatorForSearch;
    }
}
