package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Successfully stop Farm test
 */
public class StopFarmSuccessTest extends PageFarmsBaseTest {

    /**
     * Stops Farm with given name
     */
    @Test(description = "Stop Farm test")
    public void stopFarmSuccess() {
        authTests();
        switchToFarms();
        startCreateFarm();
        setupAndLaunchNewEC2Farm();

        stopFarm();

        /**
         * Verifies "Farm stopped successfully" message appears and browser remains on Farms page
         */
        Assert.assertTrue(farmStopSuccessMessageIsDisplayed() && driver.getCurrentUrl().contains(baseUrl + "/#/farms"));
    }

}