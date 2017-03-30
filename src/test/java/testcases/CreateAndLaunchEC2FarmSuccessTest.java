package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Successfully create and launch EC2 Farms test
 */
public class CreateAndLaunchEC2FarmSuccessTest extends PageFarmsBaseTest {

    /**
     * Creates Farm with Ubuntu 1404 base role, cleans up after confirmation
     */
    @Test(description = "Create and launch EC2 Farm test")
    public void createAndLaunchEC2FarmSuccess() {
        authTests();

        switchToFarms();

        startCreateFarm();

        setupAndLaunchNewEC2Farm();

        /**
         * Verifies "Farm saved and launched successfully" message appears and browser is redirected to Farms page
         */
        Assert.assertTrue(farmLaunchSuccessMessageIsDisplayed() && driver.getCurrentUrl().contains(baseUrl + "/#/farms"));

        stopFarm(); // Cleanup
    }

}