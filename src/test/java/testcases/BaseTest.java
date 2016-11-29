package testcases;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import pageobjects.PageLogin;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base Test class
 */
public abstract class BaseTest {

    protected static String baseUrl = "";

    protected static String userLogin = "";

    protected static String userPassword = "";

    protected static String userIncorrectPassword = "";

    protected static String marionetteDir = "";

    protected static WebDriver driver;

    protected DateFormat dateFormat;

    protected static File f;

    /**
     * Authenticates tests in the system
     */
    public void authTests() {
        PageLogin pageLogin =  new PageLogin(driver);
        pageLogin.loginUser(userLogin, userPassword);

        if (!driver.getCurrentUrl().contains(baseUrl + "/#/dashboard")) {
            throw new IllegalStateException("Login did not result in redirect to Environment Dashboard");
        }
    }

    /**
     * Initiates log file, reads test config parameters and sets Gecko driver property
     * @Throws IOException
     */
    @BeforeSuite
    public static void setUpSuite() throws IOException {
        Path path = FileSystems.getDefault().getPath("test_results.htm");

        try {
            Files.deleteIfExists(path);
        } catch (NoSuchFileException e) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException e) {
            System.err.format("%s not empty%n", path);
        } catch (IOException e) {     // File permission errors caught here
            System.err.println(e);
        }

        f = new File("test_results.htm");

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(input);
            baseUrl = prop.getProperty("BASE_URL");
            userLogin = prop.getProperty("USER_LOGIN");
            userPassword = prop.getProperty("USER_PASSWORD");
            userIncorrectPassword = prop.getProperty("USER_INCORRECT_PASSWORD");
            marionetteDir = prop.getProperty("MARIONETTE_DIR");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.setProperty("webdriver.gecko.driver", marionetteDir + "geckodriver");
        System.setProperty("webdriver.chrome.driver", marionetteDir + "chromedriver");
    }

    /**
     * Sets default date format for unique test data
     */
    @BeforeClass
    public void setUpClass() {
        dateFormat = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
    }

    /**
     * Detects browser type
     */
    @Parameters("browser")
    @BeforeTest
    public void setUpTest(String browser) {
        switch (browser) {
            case "firefox":
                driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.firefox());
                break;
            case "chrome":
                driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.chrome());
                break;
            case "safari":
                driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.safari());
                break;
            case "ie":
                driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.internetExplorer());
                break;
            default:
                throw new IllegalStateException("Browser type was not provided");
        }
    }

    /**
     * Gets WebDriver instance from WebDriver Factory, clears cookies and navigates to base url
     */
    @BeforeMethod
    public void setUpTest() {
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);

        if (!driver.getCurrentUrl().contains(baseUrl)) {
            throw new IllegalStateException("Login page did not load");
        }

        System.out.println(driver.getTitle());      // Navigation breadcrumb
    }

    /**
     * Closes browser after test suite completion
     */
    @AfterSuite
    public static void tearDownSuite() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}