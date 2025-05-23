package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {
    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement confirm = $("[data-test-id=action-transfer]");
    private final SelenideElement error = $("[data-test-id=error-notification]");


    public TransferPage() {}

    public DashboardPage transfer(int amountToTransfer, String fromCardNumber) {
        amount.setValue(String.valueOf(amountToTransfer));
        from.setValue(fromCardNumber);
        confirm.click();
        return new DashboardPage();
    }

    public void checkErrorNotification(String expectedText) {
        error.shouldBe(visible)
                .shouldHave(text(expectedText));
    }
}
