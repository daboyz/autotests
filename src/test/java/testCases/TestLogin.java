package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//import org.openqa.selenium.Capabilities;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObjects.PageLogin;
import org.openqa.selenium.WebDriver;
import ru.stqa.selenium.factory.WebDriverPool;

public class TestLogin extends Watchman {

    private WebDriver driver;
    private String BASE_URL = "";
    private String USER_LOGIN = "";
    private String USER_PASSWORD = "";
    private String USER_INCORRECT_PASSWORD = "";

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
        System.setProperty("webdriver.gecko.driver", "/Library/geckodriver/geckodriver");
    }

    /**
     * Test preparation (opening login page)
     */
    @Before
    public void openLoginPage() {

        driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.firefox());
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);

        System.out.println(driver.getTitle());

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Successful login test
     */
    @Test //(description = "Successful user login")
    public void loginUserSuccess() {

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
    @Test //(description = "Failed user login")
    public void loginUserFailure() {

        PageLogin pageLogin =  new PageLogin(driver);

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "This is not the login page"
            );
        }

        pageLogin.loginUser(USER_LOGIN, USER_INCORRECT_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL) || !pageLogin.checkLoginErrorMessage()) {
            System.out.println("Login with incorrect password did not result in error message or redirected elsewhere");
        }
        //loginPage.checkErrorMessage(errorMessage);

    }

    /**
     * Test finalization (close browser)
     */
    @AfterClass
    public static void closeBrowser(){
        WebDriverPool.DEFAULT.dismissAll();
    }

}