package pageObjects;

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
     * @return {@link LoginPage}
     */
    /*public LoginPage checkErrorMessage(String errorMessage) {
        Assert.assertTrue("Error message should be present",
                loginError.isDisplayed());
        Assert.assertTrue("Error message should contain " + errorMessage,
                loginError.getText().contains(errorMessage));
        return this;
    }*/
}