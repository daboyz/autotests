package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageDashboard extends BasePageClass {

    /**
     * Farms tab
     * */
    @FindBy(xpath = "//span[text()='Farms']")
    private WebElement farmsTab;

    /**
     * Error message
     */
    @FindBy(xpath = "") //Get real xpath
    public WebElement navigationError;

    public PageDashboard(WebDriver driver) {
         super(driver);
    }

    /**
     * Switch to Farms
     */
    public void switchToFarms() {

        farmsTab.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(driver.getTitle());

    }
}