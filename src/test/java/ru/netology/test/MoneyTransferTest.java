package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.data.DataHelper;
import ru.netology.page.CardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import java.util.HashMap;
import java.util.Map;


import static ru.netology.data.DataHelper.getAuthInfo;
import static ru.netology.data.DataHelper.getCards;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enables", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;
    }

    @Test
    void testMoneyTransfer() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(info);
        Selenide.open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardPage = new CardPage();
        cardPage.clickTransfer();
        var transferPage = new TransferPage();
        var transfer = transferPage.transfer(getCards());
        cardPage.clickReload();





    }
}
