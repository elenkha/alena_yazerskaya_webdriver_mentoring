package bring_it_on.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasteResultPage {

    private WebDriver driver;
    private String searchTerm;

    public PasteResultPage(WebDriver driver) {
        this.searchTerm = searchTerm;
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Paste from')]")));
    }

    public String getTitle() {
        String title = driver.findElement(By.xpath("//h1[contains(text(),'Paste from')]")).getText();
        return title;
    }

    public String getCode() {
        String code = driver.findElement(By.xpath("//td[@class='code']/div/pre")).getText();
        System.out.println(code);
        System.out.println();
        return code;
    }

    public Integer getHighlightsCunt() {
        Integer count = driver.findElements(By.xpath("//td[@class='code']/div/pre/span[@class]")).size();
        System.out.println("Count of highlight tags (<span class=\"*\">) in code: " + count);
        System.out.println();
        return count;
    }
}
