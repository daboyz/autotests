package testcases;

import pageobjects.PageDashboard;
import pageobjects.PageFarmDesigner;
import pageobjects.PageFarms;
import java.util.Date;

/**
 * Base Page Farms test
 */
public class PageFarmsBaseTest extends BaseTest {

    String generatedFarmName = "";

    PageDashboard pageDashboard;

    PageFarms pageFarms;

    PageFarmDesigner pageFarmDesigner;

    public void switchToFarms() {
        pageDashboard = new PageDashboard(driver);
        pageDashboard.switchToFarms();
    }

    public void startCreateFarm() {
        pageFarms = new PageFarms(driver);
        pageFarms.startCreateFarm();
    }

    public void setupAndLaunchNewEC2Farm() {
        pageFarmDesigner = new PageFarmDesigner(driver);
        generatedFarmName = "test-farm-" + dateFormat.format(new Date());
        pageFarmDesigner.setupAndLaunchNewEC2Farm(generatedFarmName);
    }

    public boolean farmLaunchSuccessMessageIsDisplayed() {
        return pageFarms.farmLaunchSuccessMessage.isDisplayed();
    }

    public boolean farmStopSuccessMessageIsDisplayed() {
        return pageFarms.farmStopSuccessMessage.isDisplayed();
    }

    public void stopFarm() {
        pageFarms.stopFarm(generatedFarmName);
    }

}