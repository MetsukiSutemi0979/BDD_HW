package ru.netology.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {}

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode(AuthInfo info) {
        return new VerificationCode("12345");
    }

    public static Cards getCards() {
        return new Cards("5559 0000 0000 0002", "5559 0000 0000 0001");
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class Cards {
        String firstCard;
        String secondCard;
    }
}
