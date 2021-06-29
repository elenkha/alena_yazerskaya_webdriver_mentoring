package bring_it_on.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bring_it_on.pages.HomePage;
import bring_it_on.pages.PasteResultPage;


public class pasteUbuntuCom
 {

    private WebDriver driver;
    private static final String POSTER = "Alena Yazerskaya";
    private static final String SYNTAX = "Bash";
    private static final String EXPIRATION = "week";
    private static final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Bla-bla")
    public void commonSearchTermResultsAreNotEmpty() {

        PasteResultPage resultPage = new HomePage(driver)
                .openPage()
                .doPaste(POSTER,SYNTAX,EXPIRATION,CODE);

        Assert.assertTrue(resultPage.getTitle().matches("(.*)Paste from " + POSTER +" at(.*)"));
        Assert.assertTrue(resultPage.getHighlightsCunt() > 0);
        Assert.assertEquals(resultPage.getCode(), CODE);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
