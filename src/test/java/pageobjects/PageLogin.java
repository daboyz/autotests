package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Login page
 */
public class PageLogin extends BasePage {

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
     * Login error message
     */
    @FindBy(xpath = "//div[contains(., 'Incorrect login or password')]")
    public WebElement loginError;

    /**
     * Super constructor
     */
    public PageLogin(WebDriver driver) {
        super(driver, "");
    }

    /**
     * Logs user in
     */
    public void loginUser(String login, String password) {
        waitForInvisibility(loadingBg);

        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();

        waitForInvisibility(processingBar);
    }

}