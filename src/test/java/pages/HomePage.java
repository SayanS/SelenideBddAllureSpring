package pages;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class HomePage {

    private String GLOBAL_SEARCH_FIELD = "input#lst-ib";
    private String GLOBAL_SEARCH_DROP_DOWN="ul.sbsb_b";
    private String GLOBAL_SEARCH_DROP_DOWN_VALUES = "div.sbqs_c";
    private By GLOBAL_SEARCH_BUTTON = By.xpath("//input[@jsaction='sf.chk']");


    public void clickOnGlobalSearchButton() {
        $(GLOBAL_SEARCH_BUTTON).click();
    }

    public void enterIntoGlobalSearchField(String text) {
        $(GLOBAL_SEARCH_FIELD).val(text);
    }


    public void ensureThatGlobalSearchDropDownValuesStartsWith(String expectedText) {
        $$(GLOBAL_SEARCH_DROP_DOWN_VALUES).get(0).waitUntil(visible,5000);
        $$(GLOBAL_SEARCH_DROP_DOWN_VALUES).stream().forEach(element->{
                element.is(text("sssss"));
                System.out.println(element.getText());
        });

    }
}


