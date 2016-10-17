package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFarmDesigner {

    private WebDriver driver;

    /**
     * Farm Name Field
     */
    @FindBy(xpath = "id('button-1300-btnInnerEl')")
    private WebElement newFarmButton;

    /**
     * Project field
     * */
    //Get real xpath
    @FindBy(xpath = "")
    private WebElement launchFarmButton;

    /**
     * Add farm role control
     */
    //Get real xpath
    @FindBy(xpath = "")
    private WebElement stopFarmButton;

    /**
     * Error message
     */
    //Get real xpath
    @FindBy(xpath = "")
    public WebElement farmLaunchError;

    public PageFarmDesigner(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Create and launch a new farm
     */
    public void createAndLaunchNewTestFarm() {
        System.out.println(driver.getTitle());

        

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

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
