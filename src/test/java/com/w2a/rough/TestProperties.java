package com.w2a.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        Properties OR =new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/properties/Config.properties");
        FileInputStream orFis = new FileInputStream("src/test/resources/properties/OR.properties");
        config.load(fis);
        OR.load(orFis);
        System.out.println(config.getProperty("browser"));
        System.out.println(OR.getProperty("bmlBtnCss"));

    }
}
