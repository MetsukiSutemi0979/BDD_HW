package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {
    DashboardPage dashboardPage;
    DataHelper.CardInfo firstCardInfo;
    DataHelper.CardInfo secondCardInfo;
    int firstCardBalance;
    int secondCardBalance;

    @BeforeEach
    void setup() {
        // 1. Открываем страницу логина и авторизуемся
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);

        // 2. Вводим код верификации
        var verificationCode = getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);

        // 3. Получаем данные карт и балансы
        firstCardInfo = getFirstCardInfo();
        secondCardInfo = getSecondCardInfo();
        firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
    }

    @Test
    void shouldTransferFromFirstToSecond() {
        // 4. Выполняем перевод
        var amount = generateValidAmount(firstCardBalance);
        var expectedBalanceFirst = firstCardBalance - amount;
        var expectedBalanceSecond = secondCardBalance + amount;

        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);

        // 5. Проверяем балансы
        var actualBalanceFirst = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecond = dashboardPage.getCardBalance(secondCardInfo);

        assertEquals(expectedBalanceFirst, actualBalanceFirst);
        assertEquals(expectedBalanceSecond, actualBalanceSecond);
    }

    @Test
    void shouldGetErrorMessageIfAmountMoreBalance() {
        // 6. Проверяем ошибку при превышении баланса
        var amount = generateInvalidAmount(secondCardBalance);
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);

        transferPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        transferPage.findErrorMessage("Выполнена попытка ввода суммы, превышающей остаток на карте списания");

        // 7. Проверяем, что балансы не изменились
        var actualBalanceFirst = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecond = dashboardPage.getCardBalance(secondCardInfo);

        assertEquals(firstCardBalance, actualBalanceFirst);
        assertEquals(secondCardBalance, actualBalanceSecond);
    }
}