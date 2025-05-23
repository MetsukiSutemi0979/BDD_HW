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

    public static CardInfo getFirstCard() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559 0000 0000 0002");
    }


    public static class AuthInfo {
        String login;
        String password;

        public AuthInfo(String vasya, String qwerty123) {
            
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    public static class VerificationCode {
        String code;

        public VerificationCode(String number) {
            
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }


    public static class CardInfo {
        String number;

        public CardInfo(String s) {
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
