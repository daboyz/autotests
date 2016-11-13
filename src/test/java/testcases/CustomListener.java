package testcases;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Custom TestNG test listener
 */
public class CustomListener extends TestListenerAdapter {

    /**
     * Records test as passed
     */
    @Override
    public void onTestSuccess(ITestResult tr) {

        try {
            BaseTestClass.bw.write(tr.getMethod().getDescription() + " <font color = \"green\">passed</font>.");
            BaseTestClass.bw.write("<br/>");
        }
        catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Records test as failed
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            BaseTestClass.bw.write(tr.getMethod().getDescription() + " <font color = \"red\">failed</font>.");
            BaseTestClass.bw.write("<br/>");
        }
        catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
    }

    /**
     * Records test as skipped
     */
    @Override
    public void onTestSkipped(ITestResult tr) {
        try {
            BaseTestClass.bw.write(tr.getMethod().getDescription() + " <font color = \"orange\">was skipped</font>.");
            BaseTestClass.bw.write("<br/>");
        }
        catch (Exception e3) {
            System.out.println(e3.getMessage());
        }
    }

}
