package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Farm Designer Page
 */
public class PageFarmDesigner extends BasePageClass {

    /**
     * Farm Name Field
     */
    @FindBy(xpath = "//input[contains(@class,'x-form-field x-form-text x-form-text-default') and @name='name']")
    private WebElement fieldFarmName;

    /**
     * Project field
     * */
    @FindBy(xpath = "(//span[text()='Project']/../..//input[@name='projectId'])[2]")
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
    @FindBy(xpath = "//input[@name='availabilityZone']")
    public WebElement cloudAvailZoneCombo;

    /**
     * EC2 us-east-1d avail zone
     */
    @FindBy(xpath = "//span[text()='us-east-1d']")
    public WebElement cloudAvailZoneUseast1d;

    /**
     * Add role to farm button
     */
    @FindBy(xpath = "//span[text()='Add to farm']")
    public WebElement addToFarm;

    /**
     * Save and launch farm button
     */
    @FindBy(xpath = "//span[text()='Save & launch']")
    public WebElement saveAndLaunch;

    /**
     * Super constructor
     */
    public PageFarmDesigner(WebDriver driver) {
        super(driver);
    }

    /**
     * Creates and launches a new farm
     */
    public void createAndLaunchNewEC2Farm(String testFarmName) {
        fieldFarmName.sendKeys("test-farm-" + testFarmName);

        fieldProject.click();
        fieldProject.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        addFarmRoleControl.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PageFactory.initElements(driver, addFarmRoleControl);
        baseCategory.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PageFactory.initElements(driver, selectRole);
        selectRole.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PageFactory.initElements(driver, cloudAvailZoneCombo);
        cloudAvailZoneCombo.click();

        PageFactory.initElements(driver, cloudAvailZoneUseast1d);
        cloudAvailZoneUseast1d.click();

        cloudAvailZoneCombo.click(); // WTF

        PageFactory.initElements(driver, addToFarm);
        addToFarm.click();

        try {
            Thread.sleep(2000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PageFactory.initElements(driver, saveAndLaunch);
        saveAndLaunch.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(driver.getTitle());
    }

}
