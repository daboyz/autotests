package testcases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageobjects.PageLogin;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base Test class
 */
public abstract class BaseTestClass {

    protected static File f;

    protected static BufferedWriter bw;

    protected static String baseUrl = "";

    protected static String userLogin = "";

    protected static String userPassword = "";

    protected static String userIncorrectPassword = "";

    protected WebDriver driver;

    protected static DateFormat dateFormat;

    protected static Date date;

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
     * Initiates logging, sets date format for log and sets Gecko driver property
     */
    @BeforeSuite
    public static void setUpSuite() throws IOException {
        dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        date = new Date();

        Path path = FileSystems.getDefault().getPath("test_results.htm");

        try {
            Files.deleteIfExists(path);
        }
        catch (NoSuchFileException e) {
            System.err.format("%s: no such" + " file or directory%n", path);
        }
        catch (DirectoryNotEmptyException e) {
            System.err.format("%s not empty%n", path);
        }
        catch (IOException e) {     // File permission errors caught here
            System.err.println(e);
        }

        f = new File("test_results.htm");
        bw = new BufferedWriter(new FileWriter(f, true));

        bw.write("<html><body>");
        bw.write("<h1>Test suite run results on " + dateFormat.format(date) + "</h1>");

        System.setProperty("webdriver.gecko.driver", "/Library/geckodriver/geckodriver");
    }

    /**
     * Sets date format back to non-spaced for generated names and writes class name to log
     */
    @BeforeTest
    public void setUpTest() throws IOException {
        dateFormat = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");

        bw.write("<h2>Test class " + this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1) + "</h2>");
    }

    /**
     * Reads test parameters
     */
    @BeforeClass
    public static void setUpClass() throws IOException {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(input);
            baseUrl = prop.getProperty("BASE_URL");
            userLogin = prop.getProperty("USER_LOGIN");
            userPassword = prop.getProperty("USER_PASSWORD");
            userIncorrectPassword = prop.getProperty("USER_INCORRECT_PASSWORD");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets WebDriver instance from WebDriver Factory, clears cookies and navigates to base url
     */
    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.firefox());
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);

        if (!driver.getCurrentUrl().contains(baseUrl)) {
            throw new IllegalStateException("Login page did not load");
        }

        System.out.println(driver.getTitle());      //Navigation breadcrumb
    }

    /**
     * Writes vertical space break between suites
     */
    @AfterTest
    public static void tearDownTest() throws IOException {
        bw.write("<br/>");
    }

    /**
     * Closes browser and custom logfile after test suite completion
     */
    @AfterSuite
    public static void tearDownSuite() throws IOException {
        WebDriverPool.DEFAULT.dismissAll();

        bw.write("</body></html>");
        bw.close();
    }

}