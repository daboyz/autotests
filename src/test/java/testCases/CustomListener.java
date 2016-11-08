package testCases;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static testCases.BaseTestClass.bw;

public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult tr) {

        try {
            bw.write(tr.getName() + " passed.");
            bw.write("<br/>");
        }
        catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            bw.write(tr.getName() + " failed.");
            bw.write("<br/>");
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        try {
            bw.write(tr.getName() + " was skipped.");
            bw.write("<br/>");
        }
        catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

}
