package com.w2a.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Tools;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static WebDriver driver;
    private static Properties config = new Properties();
    protected static Properties OR = new Properties();
    private static FileInputStream fis;
    protected static Logger log = Logger.getLogger("devpinoyLogger");
    protected static ExcelReader excel = new ExcelReader("src/test/resources/excel/testdata.xlsx");
    public static WebDriverWait wait;
    public ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test ;
//    private Tools tool = new Tools();

    @BeforeSuite
    public void setUp() {
        PropertyConfigurator.configureAndWatch("log4j.properties");
        if (driver == null) {
            try {
                fis = new FileInputStream("src/test/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config file loaded!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis = new FileInputStream("src/test/resources/properties/OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                OR.load(fis);
                log.debug("OR file loaded!!!");

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (config.getProperty("browser").equals("firefox")) {

                driver = new FirefoxDriver();
            }

        }
    }
    public void type(String locator, String value) {

        if (locator.endsWith("Css")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("Xpath")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("Id")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
        }

        test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

    }
    @BeforeTest()
    public void beforeTest() {

        driver.get(config.getProperty("testSiteUrl"));
        log.debug("Navigated to: " + config.getProperty("http://www.way2automation.com/angularjs-protractor/banking/#/login"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicitWait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);


    }

    public void click(String propertyName) {
        if (propertyName.endsWith("Css")) {
            driver.findElement(By.cssSelector(OR.getProperty(propertyName))).click();
        } else if (propertyName.endsWith("Xpath")) {
            driver.findElement(By.xpath(OR.getProperty(propertyName))).click();
        } else if (propertyName.endsWith("Id")) {
            driver.findElement(By.id(OR.getProperty(propertyName))).click();
        }
        test.log(LogStatus.INFO, "Clicked on: " + propertyName);
    }
    static WebElement dropdown;

    public void select(String propertyName, String text) {
        if (propertyName.endsWith("Css")) {
            dropdown = driver.findElement(By.cssSelector(OR.getProperty(propertyName)));
        } else if (propertyName.endsWith("Xpath")) {
            dropdown =driver.findElement(By.xpath(OR.getProperty(propertyName)));
        } else if (propertyName.endsWith("Id")) {
            dropdown =driver.findElement(By.id(OR.getProperty(propertyName)));
        }
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
        test.log(LogStatus.INFO, "Selecting from dropdown :" + propertyName + " entered value as " + text);


    }

    public void sendKeys(String text, String propertyName) {
        if (propertyName.endsWith("Css")) {
            driver.findElement(By.cssSelector(OR.getProperty(propertyName))).sendKeys(text);
        } else if (propertyName.endsWith("Xpath")) {
            driver.findElement(By.xpath(OR.getProperty(propertyName))).sendKeys(text);
        } else if (propertyName.endsWith("Id")) {
            driver.findElement(By.id(OR.getProperty(propertyName))).sendKeys(text);
        }


        test.log(LogStatus.INFO, "Sending text: " + text + " to locator: " + propertyName);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            Tools.addDelaySeconds(1);
            driver.quit();
        }
    }
}
