package testCases;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class Watchman {

    static File f;
    static BufferedWriter bw;

    @BeforeClass
    public static void setUp() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        f = new File("test_results.htm");
        bw = new BufferedWriter(new FileWriter(f, true));
        bw.write("<html><body>");
        bw.write("<h1>Test Case Status: " + dateFormat.format(date) + "</h1>");
    }

    @AfterClass
    public static void tearDown() throws IOException
    {
        bw.write("</body></html>");
        bw.close();
        //Desktop.getDesktop().browse(f.toURI());
    }

    @Rule public TestRule watchman = new TestWatcher() {

        @Override public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override protected void succeeded(Description description) {
            try {
                bw.write(description.getDisplayName() + " " + "passed!");
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