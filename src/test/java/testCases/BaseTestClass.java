package testCases;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObjects.PageLogin;
import ru.stqa.selenium.factory.WebDriverPool;

public class BaseTestClass {

    static File f;
    static BufferedWriter bw;

    public WebDriver driver;
    public String BASE_URL = "";
    public String USER_LOGIN = "";
    public String USER_PASSWORD = "";
    public String USER_INCORRECT_PASSWORD = "";

    public BaseTestClass() {

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

        driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.firefox());
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);

        System.out.println(driver.getTitle());

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void logIn() {

        PageLogin pageLogin = new PageLogin(driver);

        if (!driver.getCurrentUrl().contains(BASE_URL)) {
            throw new IllegalStateException(
                    "This is not the login page"
            );
        }

        pageLogin.loginUser(USER_LOGIN, USER_PASSWORD);

        if (!driver.getCurrentUrl().contains(BASE_URL + "/#/dashboard")) {
            System.out.println("Successful login did not result in redirect to Environment Dashboard"); //Sometimes exception sometimes println
        }
    }

    public void checkMessageTest() {



    }

    @BeforeClass
    public static void setUp() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();

        f = new File("test_results.htm");
        bw = new BufferedWriter(new FileWriter(f, true));
        bw.write("<html><body>");
        bw.write("<h1>Test Case Status: " + dateFormat.format(date) + "</h1>");
    }

    /**
     * Test finalization (close browser)
     */
    @AfterClass
    public static void closeBrowser(){
        WebDriverPool.DEFAULT.dismissAll();
    }

    @AfterClass
    public static void tearDown() throws IOException
    {
        bw.write("</body></html>");
        bw.close();
    }

    @Rule public TestRule watchman = new TestWatcher() {

        @Override public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override protected void succeeded(Description description) {
            try {
                bw.write(description.getDisplayName() + " " + "passed");
                bw.write("<br/>");
            }
            catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }

        @Override protected void failed(Throwable e, Description description) {
            try {
                bw.write(description.getDisplayName() + " " + e.getClass().getSimpleName());
                bw.write("<br/>");
            }
            catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    };
}