package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

//утилитный класс с приватным конструктором со статичными методами
public class DataHelper {
    private DataHelper() {
    }

    // возвращает пользователя
    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }


    //возвращает код верификации
    public static VerificationCode getVerificationCode() {

        return new VerificationCode("12345");
    }

    //методы возвращающие данных карт
    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");

    }

    //метод возвращающий валидную сумму перевода в рамках баланса
    public static int generateValidAmount(int balance) {

        return new Random().nextInt(balance) + 1;
    }

    //метод генерации невалидной суммы выходящей за пределы баланса карты
    public static int generateInvalidAmount(int balance) {

        return Math.abs(balance) + new Random().nextInt(10000);
    }

    //дата-классы

    // данные о пользователе
    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    // код верификации
    @Value
    public static class VerificationCode {
        String code;
    }

    // данные о карте
    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }
}
