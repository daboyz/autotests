package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import pageObjects.PageLogin;
import org.openqa.selenium.WebDriver;

public class TestLogin extends Watchman {

    private WebDriver driver;
    private String BASE_URL = "";
    private String USER_LOGIN = "";
    private String USER_PASSWORD = "";
    private String USER_INCORRECT_PASSWORD = "";
    private String LOCAL_USER = "";

    public TestLogin() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(input);
            this.BASE_URL = prop.getProperty("BASE_URL");
            this.USER_LOGIN = prop.getProperty("USER_LOGIN");
            this.USER_PASSWORD = prop.getProperty("USER_PASSWORD");
            this.USER_INCORRECT_PASSWORD = prop.getProperty("USER_INCORRECT_PASSWORD");
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
     * Successful login test
     */
    //@Test //(description = "Successful user login")
    public void loginUserSuccess() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        PageLogin pageLogin =  new PageLogin(driver);

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "This is not the login page"
            );
        }

        pageLogin.loginUser(USER_LOGIN, USER_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard")) {
            System.out.println("Successful login did not result in redirect to Environment Dashboard");
        }
        //loginPage.checkErrorMessage(errorMessage);
    }

    /**
     * Login with incorrect password test
     */
    //@Test //(description = "Failed user login")
    public void loginUserFailure() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        PageLogin pageLogin =  new PageLogin(driver);

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "This is not the login page"
            );
        }

        pageLogin.loginUser(USER_LOGIN, USER_INCORRECT_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL) || !pageLogin.loginError.isDisplayed()) {
            System.out.println("Login with incorrect password did not result in error message or redirected elsewhere");
        }
        //loginPage.checkErrorMessage(errorMessage);
    }
}