package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageDashboard {

    private WebDriver driver;

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
         PageFactory.initElements(driver, this);
         this.driver = driver;
    }

    /**
     * Switch to Farms
     */
    public void switchToFarms() {

        farmsTab.click();

        try{
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(driver.getTitle());

    }

    /**
     * Check for error message
     * @return {bool}
     */
    public boolean checkNavErrorMessage() {
        //boolean status;
        Assert.assertTrue("Error message should be present",
                navigationError.isDisplayed());
        boolean status = navigationError.isDisplayed();
        if (!status) return status;

        else {
            Assert.assertTrue("Error message should contain information about login or password error",
                    navigationError.getText().contains("Incorrect login or password"));
            status = navigationError.getText().contains("Incorrect login or password");
            return status;
        }

    }
}