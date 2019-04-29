package com.rppowellemail.wtf.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        monochrome = true,
        features = {"src/test/resources/features"},
        glue = { "com.rppowellemail.wtf.steps" },
        format = {"pretty","json:target/cucumber.json"},
        tags = { "@only" }
)
public class RunFeaturesTaggedWithOnly extends AbstractTestNGCucumberTests {
}
