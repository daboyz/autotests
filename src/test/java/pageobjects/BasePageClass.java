package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base Page class
 */
public abstract class BasePageClass {

    /**
     * Operation WebDriver
     */
    protected WebDriver driver;

    /**
     * Processing progress bar
     */
    @FindBy(xpath = "//div[text()='Processing ...']")
    protected WebElement processingBar;

    /**
     * Loading progress bar
     */
    @FindBy(xpath = "//div[text()='Loading ...']")
    protected WebElement loadingBar;

    /**
     * Loading page progress bar
     */
    @FindBy(xpath = "//div[text()='Loading page ...']")
    protected WebElement loadingPageBar;

    /**
     * Constructor with WebElements, local driver field initialization and page load wait
     */
    public BasePageClass(WebDriver driver, String st) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

        /**
         * Waits for provided element to disappear, no wait if nothing matched
         */
        switch (st) {
            case "loadingPageBar":
                waitForInvisibility(loadingPageBar);
                break;
            default:
                break;
        }

        System.out.println(driver.getTitle());  //Navigation breadcrumb
    }

    /**
     * Returns WebElement XPath as String
     * @return String
     */
    public String getElementXPath(WebElement el) {
        String st = el.toString();

        return st.substring(st.lastIndexOf("xpath: ") + 7, st.lastIndexOf("]"));
    }

    /**
     * Holds test until element is invisible
     */
    public void waitForInvisibility(WebElement el) {
        String st = getElementXPath(el);

        WebDriverWait waitToAppear = new WebDriverWait(driver, 5);
        waitToAppear.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(st)));

        WebDriverWait waitToDisappear = new WebDriverWait(driver, 10);
        waitToDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(st)));
    }

    /**
     * Holds test until element is visible
     */
    public void waitForVisibility(WebElement el) {
        String st = getElementXPath(el);

        WebDriverWait waitToAppear = new WebDriverWait(driver, 10);
        waitToAppear.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(st)));
    }

}