package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.PageLogin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

public class TestCreateAndLaunchFarm {

    private WebDriver driver;
    private String BASE_URL = "";
    private String USER_LOGIN = "";
    private String USER_PASSWORD = "";
    private String LOCAL_USER = "";

    public TestCreateAndLaunchFarm() {
        //Use maven? Use current user?
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
    @Test
    public void createAndLaunchFarmSuccess() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);         //Login start
        PageLogin pageLogin =  new PageLogin(driver); //The driver should be given by driver factory not login test

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "This is not the login page"
            );
        }

        pageLogin.loginUser(USER_LOGIN, USER_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard")) {
            System.out.println("Login did not result in redirect to Dashboard after valid login");
        }
        //Login end
        //The rest of create and launch tests
    }
}