package com.w2a.utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    public static  ExtentReports getInstance(){
        if(extent==null){
            File file = new File("src/test/resources/extentconfig/ReportsConfig.xml");
            extent = new ExtentReports("/home/piotr/Documents/nauka/test-output/html/extent.html",true, DisplayOrder.OLDEST_FIRST);
            extent.loadConfig(file);
        }
        return extent;
    }
}
