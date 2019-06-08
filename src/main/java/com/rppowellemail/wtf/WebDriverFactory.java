package com.rppowellemail.wtf;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class WebDriverFactory {
    public final String ChromeBrowser = "Chrome";
    public class WebDriverFactoryRequest {

    }

    public static WebDriver buildWebDriver(String browserName) throws Exception {
        WebDriver webDriver = null;
        switch (browserName) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            default:
                throw new Exception("Unable to handle browser type:" + browserName);
        }
        return webDriver;
    }

    public static WebDriver buildLocalWebDriver(String browserName) throws Exception {
        WebDriver webDriver = null;
        switch (browserName) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            default:
                throw new Exception("Unable to handle browser type:" + browserName);
        }
        return webDriver;
    }

    public static WebDriver buildRemoteWebDriver(String browserName) throws Exception {
        WebDriver webDriver = null;
        switch (browserName) {
            case "Remote-Chrome":
            case "Chrome":
                ChromeOptions capabilities = null;
                //DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities = new ChromeOptions();
                webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
                break;
            default:
                throw new Exception("Unable to handle browser type:" + browserName);
        }
        return webDriver;
    }

}
