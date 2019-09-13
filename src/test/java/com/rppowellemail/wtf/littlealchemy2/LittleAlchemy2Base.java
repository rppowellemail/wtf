package com.rppowellemail.wtf.littlealchemy2;

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
import java.util.List;
import java.util.Random;

public class LittleAlchemy2Base {
    WebDriver webDriver;

    public static final String urlString = "https://littlealchemy2.com/";

    @BeforeTest
    public void setup() throws Exception {
        //webDriver = WebDriverFactory.buildRemoteWebDriver("Chrome");
        webDriver = WebDriverFactory.buildLocalWebDriver("Chrome");
        webDriver.manage().window().maximize();
    }

    @Test
    public void doTheTest() {
        webDriver.get(urlString);

        WebElement element = webDriver.findElement(
                // $x("//div[@class='loading-screen-button-animation'][1]/..")[0]
                By.xpath("//div[@class='loading-screen-button-animation'][1]/..")
        );

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

        WebElement playButton = webDriver.findElement(
                // The play button
                // $x("//div[@class='loading-screen-button-animation'][1]/..")[0]
                By.xpath("//div[@class='loading-screen-button-animation'][1]/..")
        );
        playButton.click();



//        WebElement clearButton = webDriver.findElement(
//                // $x("//div[@class='icons']/div[1]")
//                By.xpath("//div[@class='icons']/div[1]")
//        );
//        clearButton.click();

//        WebDriverWait wait = new WebDriverWait(webDriver, 10);
//        WebElement settingsButton = webDriver.findElement(
//                // $x("//div[@class='icons']/div[1]")
//                By.xpath("//div[@class='icons']/div[2]")
//        );

        WebElement settingsButton = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='icons']/div[2]")
                ));
        settingsButton.click();

//        WebElement popupClose = webDriver.findElement(
//                // $x("//div[@class='settings modal any modal js-show']//div[@class='close border']/img");
//                By.xpath("//div[@class='settings modal any modal js-show']//div[@class='close border']/img")
//        );
//        popupClose.click();

        WebElement popupClose = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='settings modal any modal js-show']//div[@class='close border']/img")
                ));
        popupClose.click();

        getLibraryElements(webDriver);

        WebElement workspaceElement;

        workspaceElement = getWorkspace();
        WebElement airElement = getElement("air");

        System.out.println(workspaceElement);
        System.out.println(airElement);

        //(new Actions(webDriver)).dragAndDrop(airElement, workspaceElement).perform();
        //(new Actions(webDriver)).dragAndDrop(airElement, workspaceElement).perform();
        (new Actions(webDriver)).dragAndDropBy(airElement, 250, 150).perform();

        //(new Actions(webDriver)).clickAndHold(airElement).pause(500).moveToElement(workspaceElement).pause(500).release().build().perform();

        getLibraryElements(webDriver);
        workspaceElement = getWorkspace();
    }

    private void getLibraryElements(WebDriver webDriver) {
        // outer-library
        List<WebElement> elements = webDriver.findElements(
                By.xpath(("//div[@class='element']"))
        );
        for (int i = 0; i < elements.size(); i++) {
            WebElement elementName = elements.get(i).findElement(
                    By.xpath(("./div[@class='elementName']"))
            );
            System.out.println("Element["+i+"]=" + elementName.getText());
        }

    }

    private WebElement getWorkspace() {
        WebElement e = webDriver.findElement(
                By.xpath("//div[@id='workspace']")
        );
        System.out.println("workspace:" + e.getText());

        List<WebElement> elements = webDriver.findElements(
                By.xpath("//div[@id='workspace']/div[@class='element droppable']/img")
        );
        for (int i = 0; i < elements.size(); i++) {
            System.out.println("workspace element droppable[" + i + "]:" + elements.get(i).getAttribute("alt"));
        }

        return elements.get(0);
    }

    private WebElement getElement(String name) {
        return webDriver.findElement(
                By.xpath("//div[@class='elementName' and contains(text(),'" + name + "')]")
        );
    }

//    @AfterTest
//    public void teardown() {
//        webDriver.close();
//        webDriver.quit();
//    }

}
