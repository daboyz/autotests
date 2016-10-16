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
    //Refactor xpath
    @FindBy(xpath = "") //Get real xpath
    private WebElement farmsTab;

    /**
     * Error message
     */
    //Refactor xpath
    @FindBy(xpath = "") //Get real xpath
    public WebElement navigationError;

    public PageDashboard(WebDriver driver) {
         PageFactory.initElements(driver, this);
         this.driver = driver;
    }

    /**
     * Switch to Farms
     */
    public void switchToElement() {
        System.out.println(driver.getTitle());

        //switch to tab method that accepts web elements (tabs) as input to switch to
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