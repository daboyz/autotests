package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;

public class TestCreateAndLaunchFarm extends Watchman {

    private WebDriver driver;
    private String BASE_URL = "";
    private String USER_LOGIN = "";
    private String USER_PASSWORD = "";
    private String LOCAL_USER = "";

    public TestCreateAndLaunchFarm() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(input);
            this.BASE_URL = prop.getProperty("BASE_URL");
            this.USER_LOGIN = prop.getProperty("USER_LOGIN");
            this.USER_PASSWORD = prop.getProperty("USER_PASSWORD");
            this.LOCAL_USER = prop.getProperty("LOCAL_USER");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.setProperty("webdriver.gecko.driver", "/Users/" + LOCAL_USER + "/Downloads/geckodriver");
    }

    /**
     * Successful create and launch Farm test
     */
    @Test //(description = "Create and launch Farm successfully")
    public void createAndLaunchFarmSuccess() {
        driver = new FirefoxDriver();   //Driver factory needs to be implemented
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        //Login start
        PageLogin pageLogin =  new PageLogin(driver); //The driver should be given by driver factory not login test

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "This is not the login page"
            );
        }

        pageLogin.loginUser(USER_LOGIN, USER_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard")) {
            System.out.println("Successful login did not result in redirect to Environment Dashboard"); //Sometimes exception sometimes println
        }
        //Login end

        PageDashboard pageDashboard =  new PageDashboard(driver);
        pageDashboard.switchToFarms();
        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/farms")) {
            System.out.println("Clicking Farms tab did not result in redirect to Farms page");
        }

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        PageFarms pageFarms =  new PageFarms(driver);

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/farms")) {
            throw new IllegalStateException(
                    "This is not the Farms page"
            );
        }

        pageFarms.startCreateFarm();

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/farms/designer")) {
            System.out.println("Clicking New Farm did not result in opening Farm Designer"); //Sometimes exception sometimes println
        }

        PageFarmDesigner pageFarmDesigner =  new PageFarmDesigner(driver);
        pageFarmDesigner.createAndLaunchNewTestFarm();



        /*
        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/farms")) {
            throw new IllegalStateException(
                    "This is not the Farms page"
            );
        }
        */
        //The rest of create and launch tests
    }
}