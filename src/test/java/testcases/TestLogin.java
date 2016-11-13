package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.PageLogin;

/**
 * Page login tests
 */
public class TestLogin extends BaseTestClass {

    /**
     * Successfully logs in
     */
    @Test (description = "Successful user login test")
    public void loginUserSuccess() {

        PageLogin pageLogin =  new PageLogin(driver);
        pageLogin.loginUser(userLogin, userPassword);

        /**
         * Verifies browser is redirected to Dashboard page
         */
        Assert.assertTrue(driver.getCurrentUrl().contains(baseUrl + "/#/dashboard"));
    }

    /**
     * Tries a failed login attempt
     */
    @Test (description = "Login with incorrect password test")
    public void loginUserFailure() {

        PageLogin pageLogin =  new PageLogin(driver);
        pageLogin.loginUser(userLogin, userIncorrectPassword);

        /**
         * Verifies "Incorrect login or user" message appears and browser is not redirected to Dashboard page
         */
        Assert.assertTrue(!driver.getCurrentUrl().contains(baseUrl + "/#/dashboard") && pageLogin.loginError.isDisplayed());
    }

}