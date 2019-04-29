package com.rppowellemail.wtf;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BasicTestClass {

    WebDriver webDriver;

    @BeforeTest
    public void setup() throws Exception {
        webDriver = WebDriverFactory.buildWebDriver("Chrome");
        webDriver.manage().window().maximize();
    }

    @Test
    public void doTheTest() {
        webDriver.get("https://www.google.com");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss");
        Random r = new Random();
        String u = dateFormat.format(new Date()) + "-" + String.format("%04d", r.nextInt(10000));
        String filename = this.getClass().getSimpleName() + "." + (new Object(){}.getClass().getEnclosingMethod().getName()) + "-" + u + ".png";

        System.out.println("Saving screenshot '" + filename + "'");
        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void teardown() {
        webDriver.close();
        webDriver.quit();
    }
}
