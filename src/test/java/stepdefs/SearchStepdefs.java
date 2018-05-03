package stepdefs;

import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import pages.SearchResultsPage;

@ContextConfiguration(locations = {"/spring.xml"})
public class SearchStepdefs implements En {
    @Autowired
    SearchResultsPage searchResultsPage;

    public SearchStepdefs() {
        Then("^Search results should contain \"([^\"]*)\"$", (String text) -> {
           searchResultsPage.ensureThatResultsContains(text);
        });
    }
}
