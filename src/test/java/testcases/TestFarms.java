package testcases;

import org.testng.Assert;
import pageobjects.PageDashboard;
import pageobjects.PageFarmDesigner;
import pageobjects.PageFarms;
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
    public void createAndLaunchEC2FarmSuccess() {
        super.authTests();

        PageDashboard pageDashboard =  new PageDashboard(driver);
        pageDashboard.switchToFarms();

        PageFarms pageFarms =  new PageFarms(driver);
        pageFarms.startCreateFarm();

        PageFarmDesigner pageFarmDesigner =  new PageFarmDesigner(driver);
        String generatedFarmName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        pageFarmDesigner.setupAndLaunchNewEC2Farm(generatedFarmName);

        Assert.assertTrue(pageFarms.farmLaunchSuccessMessage.isDisplayed() && driver.getCurrentUrl().contains(baseUrl + "/#/farms"));

        pageFarms.stopFarm(generatedFarmName);
    }

}