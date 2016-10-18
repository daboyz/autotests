package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageFarmDesigner {

    private WebDriver driver;

    /**
     * Farm Name Field
     */
    @FindBy(xpath = "id('textfield-1323-inputEl')")
    //@FindBy(xpath = "//input[@name='name' and @role='textbox']")
    private WebElement fieldFarmName;

    /**
     * Project field
     * */
    @FindBy(xpath = "id('combobox-1336-inputEl')")
    private WebElement fieldProject;

    /**
     * Add farm role control
     */
    @FindBy(xpath = "//span[text()='Add farm role']")
    private WebElement addFarmRoleControl;

    /**
     * Base category
     */
    @FindBy(xpath = "//span[text()='Base']")
    private WebElement baseCategory;

    /**
     * Select base-ubuntu1404 role
     */
    @FindBy(xpath = "//span[text()='base-ubuntu1404']")
    public WebElement selectRole;

    /**
     * Cloud avail zones dropdown
     */
    @FindBy(xpath = "id('comboradio-1608-trigger-picker')")
    //@FindBy(xpath = "//input[@name='availabilityZone']/../div[contains(@id,'trigger-picker')]")
    public WebElement cloudAvailZoneCombo;

    /**
     * EC2 us-east-1d avail zone
     */
    @FindBy(xpath = "//span[text()='us-east-1d']")
    public WebElement cloudAvailZoneUseast1;

    /**
     * Add role to farm button
     */
    @FindBy(xpath = "//span[text()='Add to farm']")
    //@FindBy(xpath = "id('button-1626-btnInnerEl')")
    public WebElement addToFarm;

    /**
     * Save and launch farm button
     */
    @FindBy(xpath = "//span[text()='Save & launch']")
    public WebElement saveAndLaunch;

    public PageFarmDesigner(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Create and launch a new farm
     */
    public void createAndLaunchNewTestFarm() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        fieldFarmName.sendKeys("test-farm-" + dateFormat.format(date));

        fieldProject.click();
        fieldProject.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        addFarmRoleControl.click();

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        PageFactory.initElements(driver, addFarmRoleControl);
        baseCategory.click();

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        PageFactory.initElements(driver, selectRole);
        selectRole.click();

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        PageFactory.initElements(driver, cloudAvailZoneCombo);
        cloudAvailZoneCombo.click();

        PageFactory.initElements(driver, cloudAvailZoneUseast1);
        cloudAvailZoneUseast1.click();

        cloudAvailZoneCombo.click(); //Check if necessary

        PageFactory.initElements(driver, addToFarm);
        addToFarm.click();

        try{
            Thread.sleep(2000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        PageFactory.initElements(driver, saveAndLaunch);
        saveAndLaunch.click();

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
