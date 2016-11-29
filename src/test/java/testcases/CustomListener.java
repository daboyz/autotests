package testcases;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom TestNG test listener
 */
public class CustomListener extends TestListenerAdapter {

    protected Date date;

    protected BufferedWriter bw;

    /**
     * Sets up test log file, test timestamp value and starts writing HTML log
     */
    @Override
    public void onStart(ITestContext testContext) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        date = new Date();

        try {
            bw = new BufferedWriter(new FileWriter(BaseTest.f, true));
            bw.write("<html><head>\n<style>\ntable,th,td{\n border:1px solid black; padding: 8px;\n}\n</style></head><body>");
            bw.write("<table style=\"width:70%\"> <tr> <th colspan=\"2\">" + testContext.getName() + " run results on " + dateFormat.format(date) + "</th> </tr> <tr> <th>Tests</th> <th>Status</th> </tr>");
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Records test as passed
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        try {
            bw.write("<td>" + tr.getMethod().getDescription() + "</td> <td><font color = \"green\">Passed</font></td> </tr>");
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Records test as failed
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            bw.write("<td>" + tr.getMethod().getDescription() + "</td> <td><font color = \"red\">Failed</font ></td> </tr>");
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
    }

    /**
     * Records test as skipped
     */
    @Override
    public void onTestSkipped(ITestResult tr) {
        try {
            bw.write("<td>" + tr.getMethod().getDescription() + "</td> <td><font color = \"orange\">Skipped</font> </td> </tr>");
        }
        catch (Exception e3) {
            System.out.println(e3.getMessage());
        }
    }

    /**
     * Closes log
     */
    @Override
    public void onFinish(ITestContext testContext) {
        try {
            bw.write("<br/> </body> </html>");
            bw.close();
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

}