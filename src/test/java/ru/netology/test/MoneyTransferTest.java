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
import static ru.netology.data.DataHelper.getCards;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().setBrowserVersion("115");

        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        //Map<String, Object> prefs = new HashMap<String, Object>();
        //prefs.put("credentials_enable_service", false);
        //prefs.put("password_manager_enables", false);
        //options.setExperimentalOption("prefs", prefs);
        //Configuration.browserCapabilities = options;
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
        cardPage.clickTransfer();
        var transferPage = new TransferPage();
        var transfer = transferPage.transfer(getCards());
        cardPage.clickReload();
        cardPage.checkBalance();
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
        cardPage.clickTransfer2();
        var transferPage = new TransferPage();
        var transfer = transferPage.transfer2(getCards());
        cardPage.clickReload();
        cardPage.checkBalance2();
    }
}
