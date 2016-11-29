package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.PageLogin;

/**
 * Successfully logs in
 */
public class LoginUserSuccess extends PageLoginTest {

    @Test (description = "Successful user login test")
    public void loginUserSuccess() {
        pageLogin =  new PageLogin(driver);
        pageLogin.loginUser(userLogin, userPassword);

        /**
         * Verifies browser is redirected to Dashboard page
         */
        Assert.assertTrue(driver.getCurrentUrl().contains(baseUrl + "/#/dashboard"));
    }

}