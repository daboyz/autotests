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
     * Super constructor
     */
    public PageFarms(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Farm Designer for a new farm creation
     */
    public void startCreateFarm() {
        newFarmButton.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(driver.getTitle());
    }

    /**
     * Launches Farm by name
     */
    public void launchFarm(String farmName) {
        driver.findElement(By.xpath("//div[text()='test-farm-" + farmName + "']/../../..//div[@data-qtip='Launch']")).click();

        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("(//span[text()='Launch'])[2]")).click();
    }

    /**
     * Stops Farm by name
     */
    public void stopFarm(String farmName) {
        driver.findElement(By.xpath("//div[text()='test-farm-" + farmName + "']/../../..//div[@data-qtip='Terminate']")).click();

        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("(//span[text()='Terminate'])[2]")).click();
    }

}
