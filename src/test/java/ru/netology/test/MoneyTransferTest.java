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


import static ru.netology.data.DataHelper.getAuthInfo;
import static ru.netology.data.DataHelper.getFirstCard;
import static ru.netology.data.DataHelper.getSecondCard;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().setBrowserVersion("115");
    }

    @Test @Order(1)
    void testMoneyTransfer() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(info);
        Selenide.open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardPage = new DashboardPage();
        cardPage.checkDashboard();
        cardPage.selectCardToTopUp("0002");
        var transferPage = new TransferPage();
        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();
        transferPage.transfer(100, firstCard.getNumber());
        cardPage.clickReload();
        cardPage.checkCardBalance("0001", 9700);
        cardPage.checkCardBalance("0002", 10300);
    }

    @Test
    void testMoneyTransfer2() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(info);
        Selenide.open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardPage = new DashboardPage();
        cardPage.selectCardToTopUp("0001");
        var transferPage = new TransferPage();
        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();
        transferPage.transfer(100, firstCard.getNumber());
        cardPage.clickReload();
        cardPage.checkCardBalance("0001", 9800);
        cardPage.checkCardBalance("0002", 10200);
    }
}
