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

public class TestLogin extends BaseTestClass {

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

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard")) {   //Move to base class
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
}