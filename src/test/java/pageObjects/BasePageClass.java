package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePageClass {

    protected WebDriver driver;

    public BasePageClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Check for error message
     * @return {bool}
     */
    public boolean checkMessage(WebElement message, String content) {  //Maybe move to base test calss?

        PageFactory.initElements(driver, message);
        Assert.assertTrue("Banner message should be present",
                message.isDisplayed());
        boolean status = message.isDisplayed();
        if (!status) return status;

        else {
            Assert.assertTrue("Banner message should contain " + content,
                    message.getText().contains(content));
            status = message.getText().contains(content);
            return status;
        }

    }

}