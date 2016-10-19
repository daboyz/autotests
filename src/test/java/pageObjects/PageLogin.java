package pageObjects;

import org.junit.Assert;
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
    @FindBy(xpath = "//input[@name='scalrLogin']")
    private WebElement loginField;

    /**
     * Password field
     */
    @FindBy(xpath = "//input[@name='scalrPass']")
    private WebElement passwordField;


    /**
     * Login button
     */
    @FindBy(xpath = "(//span[text()='Login'])[2]")
    private WebElement loginButton;

    /**
     * Error message
     */
    @FindBy(xpath = "//div[contains(., 'Incorrect login or password')]")
    private WebElement loginError;

    public PageLogin(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * User login
     */
    public void loginUser(String LOGIN, String PASSWORD) {

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

        PageFactory.initElements(driver, loginError);
        System.out.println(driver.getTitle());
    }

    /**
     * Check for error message
     * @return {bool}
     */
    public boolean checkLoginErrorMessage() {
        //boolean status;
        Assert.assertTrue("Error message should be present",
                loginError.isDisplayed());
        boolean status = loginError.isDisplayed();
        if (status = false) return status;

            else {
                Assert.assertTrue("Error message should contain information about login or password error",
                        loginError.getText().contains("Incorrect login or password"));
                status = loginError.getText().contains("Incorrect login or password");
                return status;
            }

    }
}