package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement dashboard = $("[data-test-id=dashboard]");

    public DashboardPage() {
        dashboard.shouldBe(Condition.visible);
    }
}

