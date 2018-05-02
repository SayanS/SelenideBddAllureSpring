package utils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Component
public class Hook {
    @Autowired
    Attachments attachments;

    @Before
    public void configureSetUp(){
        baseUrl = "https://google.com/";
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            System.out.println("Scenario: " + scenario.getName() + " failed");
            attachments.takeScreenShot();
            attachments.attachSnapshotToReport();
        } else {
            System.out.println("Scenario: " + scenario.getName() + " passed");
        }

    }

    @Before()
    public void beforeScenario(Scenario scenario) throws IOException {
        System.out.println("Scenario: " + scenario.getName() + " started");
    }

}
