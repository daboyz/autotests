package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.PageLogin;

/**
 * Tries a failed login attempt
 */
public class LoginUserFailure extends PageLoginTest {

    @Test (description = "Login with incorrect password test")
    public void loginUserFailure() {
        pageLogin =  new PageLogin(driver);
        pageLogin.loginUser(userLogin, userIncorrectPassword);

        /**
         * Verifies "Incorrect login or user" message appears and browser is not redirected to Dashboard page
         */
        Assert.assertTrue(!driver.getCurrentUrl().contains(baseUrl + "/#/dashboard") && pageLogin.loginError.isDisplayed());
    }

}