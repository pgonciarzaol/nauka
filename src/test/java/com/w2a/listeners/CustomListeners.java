package com.w2a.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.Tools;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners extends TestBase implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        System.out.println(iTestResult.getName().toUpperCase());
    test = rep.startTest(iTestResult.getName().toUpperCase());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        test.log(LogStatus.PASS, iTestResult.getName().toUpperCase() + "PASS");
        rep.endTest(test);
        rep.flush();
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Tools.captureScreenshot();

        Reporter.log("Capturing screenshot");
        Reporter.log("<a target=\"_blank\"href=" + Tools.screenshotPath + ">Screenshot</a> ");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=" + Tools.screenshotPath + "><img src=" + Tools.screenshotPath + " height=200 width=200></img></a>");
        test.log(LogStatus.FAIL, iTestResult.getName().toUpperCase() + " FAIL with exception: " + iTestResult.getThrowable());
        test.log(LogStatus.FAIL, test.addScreenCapture(Tools.screenshotPath));
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
