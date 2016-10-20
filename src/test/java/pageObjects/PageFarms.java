package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFarms {

    private WebDriver driver;

    /**
     * New Farm button
     */
    @FindBy(xpath = "//span[text()='New farm']")
    private WebElement newFarmButton;

    /**
     * Launch farm button
     * */
    @FindBy(xpath = "")
    private WebElement launchFarmButton;

    /**
     * Stop Farm button
     */
    @FindBy(xpath = "")
    private WebElement stopFarmButton;

    /**
     * Error message
     */
    @FindBy(xpath = "")
    private WebElement farmLaunchError;

    /**
     * Farm launch success message
     */
    @FindBy(xpath = "//div[text()='Farm successfully saved and launched']")
    private WebElement farmLaunchSuccessMessage;

    public PageFarms(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Open Farm Designer for a new farm
     */
    public void startCreateFarm() {

        newFarmButton.click();

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(driver.getTitle());
    }

    /**
     * Launch Farm
     */
    public void launchFarm(String FarmName) {
        System.out.println(driver.getTitle());

    }

    /**
     * Stop Farm
     */
    public void stopFarm(String farmName) {

        driver.findElement(By.xpath("//div[text()='test-farm-" + farmName+ "']/../../..//div[@data-qtip='Terminate']")).click();

        try{
            Thread.sleep(1000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        driver.findElement(By.xpath("(//span[text()='Terminate'])[2]")).click();

    }

    /**
     * Check for Farm launch confirmation
     * */
    public void checkForFarmLaunchConfirmation() {
        PageFactory.initElements(driver, farmLaunchSuccessMessage);

        Assert.assertTrue("Launch message should be present",
                farmLaunchSuccessMessage.isDisplayed());
    }

    /**
     * Check for error message
     * @return {bool}
     */
    public boolean checkFarmLaunchErrorMessage() {
        //boolean status;
        Assert.assertTrue("Error message should be present",
                farmLaunchError.isDisplayed());
        boolean status = farmLaunchError.isDisplayed();
        if (!status) return status;

        else {
            Assert.assertTrue("Error message should contain information about login or password error",
                    farmLaunchError.getText().contains("Incorrect login or password"));
            status = farmLaunchError.getText().contains("Incorrect login or password");
            return status;
        }

    }

}
