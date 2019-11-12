package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.Tools;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class AddCustomerTest extends TestBase {
    Tools tool = new Tools();

    @Test(dataProviderClass = com.w2a.utilities.Tools.class, dataProvider = "dp")
    public void addCustomer(Hashtable<String, String> data) throws InterruptedException {

        if (!data.get("runmode").equals("Y")) {

            throw new SkipException("Skipping the test case as the Run mode for data set is NO");
        }
        if (tool.isElementPresent("mngBtnCss")) {
            click("mngBtnCss");
            click("addCustBtnCss");
        }
        type("firstNameCss", data.get("firstname"));
        type("lastNameXpath", data.get("lastname"));
        type("postCodeCss", data.get("postcode"));
        click("addBtnCss");
        Thread.sleep(2000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
        alert.accept();

        Thread.sleep(2000);

    }

}
