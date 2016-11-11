package testcases;

import org.testng.Assert;
import pageobjects.PageDashboard;
import pageobjects.PageFarmDesigner;
import pageobjects.PageFarms;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.Test;

/**
 * Page Farms tests
 */
public class TestFarms extends BaseTestClass {

    /**
     * Creates Farm with Ubuntu 1404 base role, cleans up after confirmation
     */
    @Test (description = "Create and launch EC2 Farm test")
    public void createAndLaunchFarmSuccess() {

        super.authTests();

        PageDashboard pageDashboard =  new PageDashboard(driver);
        pageDashboard.switchToFarms();

        PageFarms pageFarms =  new PageFarms(driver);
        pageFarms.startCreateFarm();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PageFarmDesigner pageFarmDesigner =  new PageFarmDesigner(driver);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String GENERATED_FARM_NAME = dateFormat.format(date);

        pageFarmDesigner.createAndLaunchNewTestFarm(GENERATED_FARM_NAME);

        Assert.assertTrue(pageFarms.farmLaunchSuccessMessage.isDisplayed() && driver.getCurrentUrl().contains(baseUrl + "/#/farms"));

        pageFarms.stopFarm(GENERATED_FARM_NAME);
    }

}