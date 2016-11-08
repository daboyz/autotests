package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageClass {

    protected WebDriver driver;

    public BasePageClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}