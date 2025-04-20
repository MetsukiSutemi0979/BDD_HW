package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement dashboard = $("[data-test-id=dashboard]");
    private final SelenideElement card1 = $$("button[data-test-id='action-deposit']").get(0);
    private final SelenideElement card2 = $$("button[data-test-id='action-deposit']").get(1);
    private final SelenideElement reload = $("[data-test-id=action-reload]");
    private final SelenideElement card1Balance = $(Selectors.withText("**** **** **** 0001"));
    private final SelenideElement card2Balance = $(Selectors.withText("**** **** **** 0002"));


    public DashboardPage clickTransfer(){
        card1.click();
        return new DashboardPage();
    }

    public DashboardPage clickTransfer2(){
        card2.click();
        return new DashboardPage();
    }

    public DashboardPage clickReload(){
        reload.click();
        return new DashboardPage();
    }

    public DashboardPage checkBalance() {
        card1Balance.shouldHave(Condition.exactText("**** **** **** 0001, баланс: 10000 р.\nПополнить"));
        card2Balance.shouldHave(Condition.exactText("**** **** **** 0002, баланс: 10000 р.\nПополнить"));
        return new DashboardPage();
    }

    public DashboardPage checkBalance2(){
        card2Balance.shouldHave(Condition.exactText("**** **** **** 0002, баланс: 10100 р.\nПополнить"));
        card1Balance.shouldHave(Condition.exactText("**** **** **** 0001, баланс: 9900 р.\nПополнить"));
        return new DashboardPage();
    }



    public DashboardPage() {
        dashboard.shouldBe(Condition.visible);
    }
}

