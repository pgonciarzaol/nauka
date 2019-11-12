package com.w2a.utilities;

import com.w2a.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class Tools extends TestBase {
    public static String screenshotPath;

    public static void addDelaySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addDelay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    @DataProvider(name = "dp")
    public Object[][] getData(Method m) {

        String sheetName = m.getName();
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][1];

        Hashtable<String, String> table = null;

        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

            table = new Hashtable<String, String>();

            for (int colNum = 0; colNum < cols; colNum++) {

                // data[0][0]
                table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
                data[rowNum - 2][0] = table;
            }

        }

        return data;

    }

    public boolean isElementPresent(String propertyName) {
        boolean is = false;
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if (propertyName.endsWith("Css")) {
            try {
                driver.findElement(By.cssSelector(OR.getProperty(propertyName)));
                is = true;
            } catch (Exception e) {
            }
        } else if (propertyName.endsWith("Xpath")) {
            try {
                driver.findElement(By.xpath(OR.getProperty(propertyName)));
                is = true;
            } catch (Exception e) {
            }

        } else if (propertyName.endsWith("Id")) {
            try {
                driver.findElement(By.id(OR.getProperty(propertyName)));
                is = true;
            } catch (Exception e) {
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return is;
    }

    private static String getDateAndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static void captureScreenshot() {
        screenshotPath = "/home/piotr/Documents/nauka/test-output/html" + getDateAndTime() + ".jpg";
        String screenshotPath2 = "/home/piotr/Documents/nauka/src/test/resources/screenshots" + getDateAndTime() + ".jpg";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTestRunnable(String testName, ExcelReader excel) {

        String sheetName = "test_suite";
        int rows = excel.getRowCount(sheetName);


        for (int rNum = 2; rNum <= rows; rNum++) {

            String testCase = excel.getCellData(sheetName, "TCID", rNum);

            if (testCase.equalsIgnoreCase(testName)) {

                String runmode = excel.getCellData(sheetName, "Runmode", rNum);

                if (runmode.equalsIgnoreCase("Y"))
                    return true;
                else
                    return false;
            }


        }
        return false;
    }
}
