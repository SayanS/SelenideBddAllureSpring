package stepdefs;

import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import pages.HomePage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

@ContextConfiguration(locations = {"/spring.xml"})
public class HomeStepdefs implements En {

    @Autowired
    HomePage homePage;


    public HomeStepdefs() {

        Given("^Home page is opened$", () -> {
//            baseUrl = "https://google.com";
            open(baseUrl, HomePage.class);
        });

        When("^Enter \"([^\"]*)\" into Global search field$", (String text) -> {
            homePage.enterIntoGlobalSearchField(text);
        });

        Then("^The values of Global search drop-down list should start with \"([^\"]*)\"$", (String text) -> {
            homePage.ensureThatGlobalSearchDropDownValuesStartsWith(text);
        });
        When("^Click on Global Search button$", () -> {
            homePage.clickOnGlobalSearchButton();
        });

    }
}
