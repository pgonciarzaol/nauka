package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.Tools;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = Tools.class, dataProvider = "dp")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException {

		
		if(!(Tools.isTestRunnable("openAccountTest", excel))){
			
			throw new SkipException("Skipping the test "+"openAccountTest".toUpperCase()+ "as the Run mode is NO");
		}
		
		click("mngBtnCss");
		click("openAccountCss");
		select("customerCss", data.get("customer"));
		select("currencyCss", data.get("currency"));
		click("processCss");
		Thread.sleep(2000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();

	}

}
