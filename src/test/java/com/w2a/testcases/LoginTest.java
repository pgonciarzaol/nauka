package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.Tools;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    private Tools tool = new Tools();
    @Test
    public void loginAsBankManager() {
        click("bmlBtnCss");
        tool.addDelay(10);
        log.debug("inside login test");
        Assert.assertTrue(tool.isElementPresent(By.cssSelector(OR.getProperty("addCustBtnCss"))));
        log.debug("Login successfully executed");
        Reporter.log("Login successfully executed");
        Reporter.log("<a href='/home/piotr/Documents/nauka/src/screenshot_1.png'>Screenshot</a>");
    }
}
