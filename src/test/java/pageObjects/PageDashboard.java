package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageDashboard extends BasePageClass {

    /**
     * Farms tab
     * */
    @FindBy(xpath = "//span[text()='Farms']")
    private WebElement farmsTab;

    public PageDashboard(WebDriver driver) {
         super(driver);
    }

    /**
     * Switch to Farms
     */
    public void switchToFarms() {

        farmsTab.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(driver.getTitle());

    }
}