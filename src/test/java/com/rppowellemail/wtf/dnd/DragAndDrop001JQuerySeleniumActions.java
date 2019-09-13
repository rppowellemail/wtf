package com.rppowellemail.wtf.dnd;

import com.rppowellemail.wtf.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// http://learn-automation.com/drag-and-drop-in-selenium-webdriver-using-actions-class/

public class DragAndDrop001JQuerySeleniumActions {
    WebDriver webDriver;

    public static final String urlString = "http://jqueryui.com/resources/demos/droppable/default.html";

    @BeforeTest
    public void setup() throws Exception {
        //webDriver = WebDriverFactory.buildRemoteWebDriver("Chrome");
        webDriver = WebDriverFactory.buildLocalWebDriver("Chrome");
        webDriver.manage().window().maximize();
    }

    @Test
    public void doTheTest() {
        webDriver.get(urlString);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss");
        Random r = new Random();
        String u;
        String filename;
        File srcFile;

        u = dateFormat.format(new Date()) + "-" + String.format("%04d", r.nextInt(10000));
        filename = this.getClass().getSimpleName() + "." + (new Object(){}.getClass().getEnclosingMethod().getName()) + "-before-" + u + ".png";
        System.out.println("Saving screenshot '" + filename + "'");
        srcFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebElement draggableElement = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='draggable']")
                ));

        WebElement droppableElement = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='droppable']")
                ));

        //(new Actions(webDriver)).dragAndDrop(draggableElement, droppableElement).perform();

        (new Actions(webDriver)).clickAndHold(draggableElement).pause(2000).moveToElement(droppableElement).pause(2000).release().build().perform();

        u = dateFormat.format(new Date()) + "-" + String.format("%04d", r.nextInt(10000));
        filename = this.getClass().getSimpleName() + "." + (new Object(){}.getClass().getEnclosingMethod().getName()) + "-after-" + u + ".png";
        System.out.println("Saving screenshot '" + filename + "'");
        srcFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filename));
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
