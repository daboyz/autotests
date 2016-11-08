package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageFarms extends BasePageClass {

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
    public WebElement farmLaunchSuccessMessage;

    public PageFarms(WebDriver driver) {
        super(driver);
    }

    /**
     * Open Farm Designer for a new farm
     */
    public void startCreateFarm() {

        newFarmButton.click();

        try {
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
    public void launchFarm(String farmName) {

        driver.findElement(By.xpath("//div[text()='test-farm-" + farmName + "']/../../..//div[@data-qtip='Launch']")).click();

        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        driver.findElement(By.xpath("(//span[text()='Launch'])[2]")).click();

    }

    /**
     * Stop Farm
     */
    public void stopFarm(String farmName) {

        driver.findElement(By.xpath("//div[text()='test-farm-" + farmName + "']/../../..//div[@data-qtip='Terminate']")).click();

        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        driver.findElement(By.xpath("(//span[text()='Terminate'])[2]")).click();

    }
}
