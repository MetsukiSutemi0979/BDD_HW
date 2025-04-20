package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement confirm = $("[data-test-id=action-transfer]");
    private final SelenideElement error = $("[data-test-id=error-notification]");


    public TransferPage() {}


    public TransferPage transfer(DataHelper.Cards card) {
        amount.setValue("100");
        from.setValue(card.getFirstCard());
        confirm.click();
        return new TransferPage();
    }

    public TransferPage transfer2(DataHelper.Cards card) {
        amount.setValue("100");
        from.setValue(card.getSecondCard());
        confirm.click();
        return new TransferPage();
    }

    public TransferPage errorNotification(){
        error.shouldBe(Condition.visible);
        return new TransferPage();
    }
}
