package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin extends BasePageClass{

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
    public WebElement loginError;

    public PageLogin(WebDriver driver) {
        super(driver);
    }

    /**
     * User login
     */
    public void loginUser(String LOGIN, String PASSWORD) {

        loginField.sendKeys(LOGIN);
        passwordField.sendKeys(PASSWORD);
        loginButton.sendKeys(Keys.ENTER);

        PageFactory.initElements(driver, this);

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(driver.getTitle());
    }

}