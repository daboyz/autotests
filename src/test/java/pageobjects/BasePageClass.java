package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base Page class
 */
public class BasePageClass {

    protected WebDriver driver;

    /**
     * Constructor with WebElements and local driver variable initialization
     */
    public BasePageClass(WebDriver driver) {
        PageFactory.initElements(driver, this);

        this.driver = driver;
    }

}