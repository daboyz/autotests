package testCases;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObjects.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.junit.*;
import ru.stqa.selenium.factory.WebDriverPool;

public class TestCreateAndLaunchFarm extends Watchman {

    private static WebDriver driver;
    private static String BASE_URL = "";
    private static String USER_LOGIN = "";
    private static String USER_PASSWORD = "";
    private static String USER_INCORRECT_PASSWORD = "";
    private static String LOCAL_USER = "";
    private String GENERATED_FARM_NAME = "";

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

    @Before
    public void logIn() {
        Capabilities firefox = DesiredCapabilities.firefox();
        driver = WebDriverPool.DEFAULT.getDriver(firefox);

        driver.get(BASE_URL);

        System.out.println(driver.getTitle());

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

        PageLogin pageLogin = new PageLogin(driver);

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "This is not the login page"
            );
        }

        pageLogin.loginUser(USER_LOGIN, USER_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard")) {
            System.out.println("Successful login did not result in redirect to Environment Dashboard"); //Sometimes exception sometimes println
        }

    }

    /**
     * Successful create and launch Farm test
     */
    @Test //(description = "Create and launch Farm successfully")
    public void createAndLaunchFarmSuccess() {

        PageDashboard pageDashboard =  new PageDashboard(driver);
        pageDashboard.switchToFarms();

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/farms")) {
            System.out.println("Clicking Farms tab did not result in redirect to Farms page");
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

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        GENERATED_FARM_NAME = dateFormat.format(date);

        pageFarmDesigner.createAndLaunchNewTestFarm(GENERATED_FARM_NAME);

        pageFarms.checkForFarmLaunchConfirmation();

        pageFarms.stopFarm(GENERATED_FARM_NAME);

        /*
        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/farms")) {
            throw new IllegalStateException(
                    "This is not the Farms page"
            );
        }
        */
    }

    @AfterClass
    public static void closeBrowser(){
        WebDriverPool.DEFAULT.dismissAll();
    }
}