package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Farms page
 */
public class PageFarms extends BasePageClass {

    /**
     * New Farm button
     */
    @FindBy(xpath = "//span[text()='New farm']")
    private WebElement newFarmButton;

    /**
     * Farm launch success message
     */
    @FindBy(xpath = "//div[text()='Farm successfully saved and launched']")
    public WebElement farmLaunchSuccessMessage;

    /**
     * Popup Terminate Farm Button
     */
    @FindBy(xpath = "(//span[text()='Terminate'])[2]")
    private WebElement popupTerminateFarmButton;

    /**
     * Super constructor
     */
    public PageFarms(WebDriver driver) {
        super(driver, "loadingPageBar");
    }

    /**
     * Starts Farm Designer for a new farm creation
     */
    public void startCreateFarm() {
        newFarmButton.click();
    }

    /**
     * Stops Farm by name
     */
    public void stopFarm(String farmName) {
        driver.findElement(By.xpath("//div[text()='" + farmName + "']/../../..//div[@data-qtip='Terminate']")).click();

        waitForInvisibility(processingBar);
        popupTerminateFarmButton.click();
    }

}
