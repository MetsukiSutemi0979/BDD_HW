
package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getAuthInfo;
import static ru.netology.data.DataHelper.getFirstCard;
import static ru.netology.data.DataHelper.getSecondCard;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenCards() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(info);
        Selenide.open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var cardPage = new DashboardPage();

        var firstCard = getFirstCard();
        var secondCard = getSecondCard();

        int initialFromBalance = cardPage.getCardBalance("0001");
        int initialToBalance = cardPage.getCardBalance("0002");
        int amount = 100;

        cardPage.selectCardToTopUp("0002")
                .transfer(amount, firstCard.getNumber());

        assertEquals(initialFromBalance - amount, cardPage.getCardBalance("0001"));
        assertEquals(initialToBalance + amount, cardPage.getCardBalance("0002"));
    }
}
