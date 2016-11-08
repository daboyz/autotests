package testCases;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.*;
import ru.stqa.selenium.factory.WebDriverPool;

public class BaseTestClass {

    static File f;
    public static BufferedWriter bw;

    public WebDriver driver;
    public static String BASE_URL = "";
    public static String USER_LOGIN = "";
    public static String USER_PASSWORD = "";
    public static String USER_INCORRECT_PASSWORD = "";

    /**
     * Service methods block
     */
    public void authTests() {

        PageLogin pageLogin =  new PageLogin(driver);

        pageLogin.loginUser(USER_LOGIN, USER_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard")) {
            throw new IllegalStateException(
                    "Login did not result in redirect to Environment Dashboard"
            );
        }
    }

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
            BASE_URL = prop.getProperty("BASE_URL");
            USER_LOGIN = prop.getProperty("USER_LOGIN");
            USER_PASSWORD = prop.getProperty("USER_PASSWORD");
            USER_INCORRECT_PASSWORD = prop.getProperty("USER_INCORRECT_PASSWORD");

        }
        catch (IOException ex) {
            ex.printStackTrace();

        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.setProperty("webdriver.gecko.driver", "/Library/geckodriver/geckodriver");

    }

    @BeforeMethod
    public void startBrowser(){
        driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.firefox());
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "Login page did not load"
            );
        }

        System.out.println(driver.getTitle());

    }

    /**
     * Test finalization (close browser and logfile)
     */
    @AfterClass
    public static void tearDown() throws IOException {
        WebDriverPool.DEFAULT.dismissAll();

        bw.write("</body></html>");
        bw.close();
    }

}