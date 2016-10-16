package pageObjects;

//import org.junit.Assert;

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
    //Refactor xpath
    @FindBy(xpath = "/html/body[@id='ext-element-1']/div[@id='form-1014']/div[@id='form-1014-body']/div[@id='form-1014-outerCt']/div[@id='form-1014-innerCt']/fieldset[@id='fieldset-1015']/div[@id='fieldset-1015-body']/div[@id='fieldset-1015-outerCt']/div[@id='fieldset-1015-innerCt']/div[@id='textfield-1017']/div[@id='textfield-1017-bodyEl']/div[@id='textfield-1017-triggerWrap']/div[@id='textfield-1017-inputWrap']/input[@id='textfield-user-login-inputEl']")
    private WebElement loginField;

    /**
     * Password field
     */
    //Refactor xpath
    @FindBy(xpath = "/html/body[@id='ext-element-1']/div[@id='form-1014']/div[@id='form-1014-body']/div[@id='form-1014-outerCt']/div[@id='form-1014-innerCt']/fieldset[@id='fieldset-1015']/div[@id='fieldset-1015-body']/div[@id='fieldset-1015-outerCt']/div[@id='fieldset-1015-innerCt']/div[@id='textfield-1018']/div[@id='textfield-1018-bodyEl']/div[@id='textfield-1018-triggerWrap']/div[@id='textfield-1018-inputWrap']/input[@id='textfield-user-password-inputEl']")
    private WebElement passwordField;


    /**
     * Login button
     */
    //Refactor xpath
    @FindBy(xpath = "/html/body[@id='ext-element-1']/div[@id='form-1014']/div[@id='container-1028']/div[@id='container-1028-innerCt']/div[@id='container-1028-targetEl']/a[@id='button-1030']")
    private WebElement loginButton;

    /**
     * Error message
     */
    //Refactor xpath
    @FindBy(xpath = "/html/body[@id='ext-element-1']/div[@id='tooltip-1035']/div[@id='tooltip-1035-body']")
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