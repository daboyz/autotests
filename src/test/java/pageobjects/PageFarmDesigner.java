package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Farm Designer Page
 */
public class PageFarmDesigner extends BasePageClass {

    /**
     * Saving Farm bar
     */
    @FindBy(xpath = "//div[text()='Saving farm ...']")
    private WebElement savingFarmBar;

    /**
     * Farm Name Field
     */
    @FindBy(xpath = "(//input[@name='name'])[2]")
    private WebElement fieldFarmName;

    /**
     * Project field
     * */
    @FindBy(xpath = "(//input[@name='projectId'])[2]")
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
     * Base-ubuntu1404 role
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
     * Farm role tab
     */
    @FindBy(xpath = "//div[@title='base-ubuntu1404']")
    public WebElement farmRoleTab;

    /**
     * Save and launch farm button
     */
    @FindBy(xpath = "//span[text()='Save & launch']")
    public WebElement saveAndLaunch;

    /**
     * Role added successfully banner
     */
    @FindBy(xpath = "//div[text()='Role \"base-ubuntu1404\" added']")
    private WebElement bannerRoleAdded;

    /**
     * Super constructor
     */
    public PageFarmDesigner(WebDriver driver) {
        super(driver, "loadingPageBar");
    }

    /**
     * Creates and launches a new farm
     */
    public void setupAndLaunchNewEC2Farm(String testFarmName) {
        fieldFarmName.sendKeys("test-farm-" + testFarmName);

        fieldProject.click();
        fieldProject.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
/*
        addFarmRoleControl.click();

        waitForElementToBeInvisible(loadingBar);
        baseCategory.click();

        waitForElementToBeInvisible(loadingBar);
        selectRole.click();

        waitForElementToBeInvisible(loadingBar);
        cloudAvailZoneCombo.click();

        cloudAvailZoneUseast1d.click();

        cloudAvailZoneCombo.click(); // Close dropdown

        addToFarm.click();
        waitForElementToBeInvisible(loadingBar);
*/
        //waitForElementToBeVisible(bannerRoleAdded);
        saveAndLaunch.click();

        waitForElementToBeInvisible(savingFarmBar);
        System.out.println(driver.getTitle());
    }

}
