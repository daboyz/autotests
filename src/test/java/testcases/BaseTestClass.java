package testcases;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageobjects.PageLogin;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base Test class
 */
public class BaseTestClass {

    protected static File f;

    protected static BufferedWriter bw;

    protected static String baseUrl = "";

    protected static String userLogin = "";

    protected static String userPassword = "";

    protected static String userIncorrectPassword = "";

    protected WebDriver driver;

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
     * Reads test parameters and sets Gecko driver property
     */
    @BeforeClass
    public static void setUp() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();

        f = new File("test_results.htm");
        bw = new BufferedWriter(new FileWriter(f, true));
        bw.write("<html><body>");
        bw.write("<h1>Test Case Status: " + dateFormat.format(date) + "</h1>");

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

        System.setProperty("webdriver.gecko.driver", "/Library/geckodriver/geckodriver");
    }

    /**
     * Gets WebDriver instance from WebDriver Factory and navigates to base url
     */
    @BeforeMethod
    public void startBrowser(){
        driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.firefox());
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (!driver.getCurrentUrl().contains(baseUrl)) {
            throw new IllegalStateException("Login page did not load");
        }

        System.out.println(driver.getTitle());
    }

    /**
     * Closes browser and custom logfile
     */
    @AfterClass
    public static void tearDown() throws IOException {
        WebDriverPool.DEFAULT.dismissAll();

        bw.write("</body></html>");
        bw.close();
    }

}