package pageObjects;

//import org.junit.Assert;
import java.lang.InterruptedException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin {

    private WebDriver driver;

    /**
     * Login field
     */
    @FindBy(xpath = "id('textfield-user-login-inputEl')")
    private WebElement loginField;

    /**
     * Password field
     */
    @FindBy(xpath = "id('textfield-user-password-inputEl')")
    private WebElement passwordField;


    /**
     * Login button
     */
    @FindBy(xpath = "id('button-1030-btnEl')")
    private WebElement loginButton;

    /**
     * Error message
     */
    @FindBy(xpath = "id('tooltip-1035-body')")
    public WebElement loginError;

    public PageLogin(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * User login
     */
    public void loginUser(String LOGIN, String PASSWORD) {

        System.out.println(driver.getTitle());

        loginField.sendKeys(LOGIN);
        passwordField.sendKeys(PASSWORD);
        loginButton.sendKeys(Keys.ENTER);

        PageFactory.initElements(driver, this);
        try{
            Thread.sleep(3000);
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Check for error message
     * @return {@link LoginPage}
     */
    /*public LoginPage checkErrorMessage(String errorMessage) {
        Assert.assertTrue("Error message should be present",
                loginError.isDisplayed());
        Assert.assertTrue("Error message should contain " + errorMessage,
                loginError.getText().contains(errorMessage));
        return this;
    }*/
}