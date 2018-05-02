package utils;

import cucumber.api.java.Before;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Configuration.baseUrl;

@Component
public class SetUp {

    @Before
    public void configureSetUp(){
        baseUrl = "https://google.com/";
    }

}
