package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.PageLogin;

public class TestLogin extends BaseTestClass {

    @Test (description = "Successful user login test")
    public void loginUserSuccess() {

        PageLogin pageLogin =  new PageLogin(driver);

        pageLogin.loginUser(USER_LOGIN, USER_PASSWORD);

        Assert.assertTrue(driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard"));
    }

    @Test (description = "Login with incorrect password test")
    public void loginUserFailure() {

        PageLogin pageLogin =  new PageLogin(driver);

        pageLogin.loginUser(USER_LOGIN, USER_INCORRECT_PASSWORD);

        Assert.assertTrue(!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard") && pageLogin.loginError.isDisplayed());
    }
}