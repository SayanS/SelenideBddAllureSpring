package utils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Component
public class Hook {

    @Before
    public void configureSetUp(){
        baseUrl = "https://google.com/";
    }


    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {
            makeScreenshot(scenario.getStatus());
            getWebDriver().quit();
        }
    }

    @Attachment(value = "Page screenshot - {0}", type = "image/png")
    private byte[] makeScreenshot(String method) {
        return (((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES));
    }


}
