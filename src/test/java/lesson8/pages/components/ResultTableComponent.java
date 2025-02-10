package lesson8.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    public ResultTableComponent checkResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));

        return this;
    }
}