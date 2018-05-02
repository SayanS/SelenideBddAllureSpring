package pages;

import com.codeborne.selenide.CollectionCondition;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class HomePage {

    private String GLOBAL_SEARCH_FIELD = "input#lst-ib";
    private String GLOBAL_SEARCH_DROP_DOWN_VALUES = "li.sbsb_c.gsfs";
    private By GLOBAL_SEARCH_BUTTON = By.xpath("//input[@name='btnK']");


    public void clickOnGlobalSearchButton() {
        $(GLOBAL_SEARCH_BUTTON).click();
    }


    public void enterIntoGlobalSearchField(String text) {
        $(GLOBAL_SEARCH_FIELD).val(text);
    }


    public void ensureThatGlobalSearchDropDownValuesStartsWith(String value) {
        $$(GLOBAL_SEARCH_DROP_DOWN_VALUES).shouldHave(CollectionCondition.texts(value));
    }
}


