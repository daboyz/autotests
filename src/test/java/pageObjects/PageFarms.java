package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFarms {

    private WebDriver driver;

    /**
     * New Farm button
     */
    @FindBy(xpath = "id('button-1300-btnInnerEl')")
    private WebElement newFarmButton;

    /**
     * Launch farm button
     * */
    //Get real xpath
    @FindBy(xpath = "")
    private WebElement launchFarmButton;

    /**
     * Stop Farm button
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

    public PageFarms(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Open Farm Designer for a new farm
     */
    public void startCreateFarm() {
        System.out.println(driver.getTitle());

        newFarmButton.click();

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Launch Farm
     */
    public void launchFarm(String FarmName) {
        System.out.println(driver.getTitle());

        //Methods to launch a farm that is passed as input

    }

    /**
     * Stop Farm
     */
    public void stopFarm(String FarmName) {
        System.out.println(driver.getTitle());

        //Method that accepts farm name and stops it

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
