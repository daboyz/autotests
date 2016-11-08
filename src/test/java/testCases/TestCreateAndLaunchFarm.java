package testCases;

import org.testng.Assert;
import pageObjects.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.*;

public class TestCreateAndLaunchFarm extends BaseTestClass {

    private String GENERATED_FARM_NAME = "";
    private PageFarms pageFarms;

    @Test (description = "Successfully create and launch Farm test")
    public void createAndLaunchFarmSuccess() {

        super.authTests();

        PageDashboard pageDashboard =  new PageDashboard(driver);

        pageDashboard.switchToFarms();

        pageFarms =  new PageFarms(driver);

        pageFarms.startCreateFarm();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        PageFarmDesigner pageFarmDesigner =  new PageFarmDesigner(driver);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        GENERATED_FARM_NAME = dateFormat.format(date);

        pageFarmDesigner.createAndLaunchNewTestFarm(GENERATED_FARM_NAME);

        Assert.assertTrue(pageFarms.farmLaunchSuccessMessage.isDisplayed() && driver.getCurrentUrl().contains(BASE_URL + "/#/farms"));

        pageFarms.stopFarm(GENERATED_FARM_NAME);
    }

}