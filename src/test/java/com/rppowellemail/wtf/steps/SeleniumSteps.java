package com.rppowellemail.wtf.steps;

import com.rppowellemail.wtf.WebDriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class SeleniumSteps {

    private WebDriver webDriver;

    @Given("^I am on Google Page$")
    public void on_Google_Page() throws Exception {
        //webDriver = WebDriverFactory.buildWebDriver("Chrome");
        webDriver = WebDriverFactory.buildRemoteWebDriver("Remote-Chrome");
        webDriver.manage().window().maximize();

        webDriver.get("https://www.google.com");
    }

    @When("^I submit \"TestNG\"$")
    public void submit() {
    }
    @Then("^I will get a results page for \"TestNG\"$")
    public void check_page_results() {
        webDriver.close();
        webDriver.quit();
    }

}
