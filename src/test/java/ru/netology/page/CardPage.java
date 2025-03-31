package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private final SelenideElement card = $("[data-test-id=action-deposit]");
    private final SelenideElement reload = $("[data-test-id=action-reload]");

    public CardPage clickTransfer(){
        card.click();
        return new CardPage();
    }

    public CardPage clickReload(){
        reload.click();
        return new CardPage();
    }
}
