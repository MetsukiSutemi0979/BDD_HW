package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement dashboard = $("[data-test-id=dashboard]");
    private final SelenideElement reload = $("[data-test-id=action-reload]");

    public TransferPage selectCardToTopUp(String cardLastDigits) {
        $$(".list__item").findBy(text(cardLastDigits))
                .$("button[data-test-id='action-deposit']")
                .click();
        return new TransferPage();
    }

    public DashboardPage clickReload(){
        reload.click();
        return new DashboardPage();
    }

    public int getCardBalance(String lastDigits) {
        String text = $(Selectors.withText("**** **** **** " + lastDigits)).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        String balance = text.substring(text.indexOf("баланс:") + 7, text.indexOf("р.")).trim();
        return Integer.parseInt(balance.trim());
    }

    public DashboardPage() {
        dashboard.shouldBe(Condition.visible);
    }

}

