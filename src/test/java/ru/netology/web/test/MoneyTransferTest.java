package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

public class MoneyTransferTest {
    LoginPage loginPage;
    DashboardPage dashboardPage; // переменная экземпляра класса

    // предусловие
    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class); //открытие страници логина
        var authInfo = DataHelper.getAuthInfo();  // получение данных аутентификации
        var verificationPage = loginPage.validLogin(authInfo);  // переход на страницу верификации
        var verificationCode = DataHelper.getVerificationCode();  // получение данных для верификации, код верификации
        dashboardPage = verificationPage.validVerify(verificationCode);  // переход на страницу Дашборда

    }

    @Test
    void shouldTransferFromFirstToSecond() {
        var firstCardInfo = getFirstCardInfo(); // получение данных первой карты
        var secondCardInfo = getSecondCardInfo();  // получение данных второй карты
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo); // получение баланса первой карты
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);  // получение баланса второй карты
        var amount = generateValidAmount(firstCardBalance);  //получение валидной суммы перевода (сумма м/б любая, в рамках лимита)
        var expectedBalanceFirstCard = firstCardBalance - amount; // ожидаемый баланс на первой карте после перевода
        var expectedBalanceSecondCard = secondCardBalance + amount; // на второй
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo); // выбираем вторую карту и попадаем на страницу перевода средств
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo); // на стр. перевода выполняем валидный перевод на первую карту и попадаем на страницу Дашборда
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo); // получение фактического баланса первой карты
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo); // второй
        assertEquals(expectedBalanceFirstCard, actualBalanceSecondCard); // сравнение ожидаемого и фактического баланса первой карты
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard); // второй

    }
}

   // @Test
    //void shouldGetErrorMessageIfAmountMoreBalance() {
       // var firstCardInfo = getFirstCardInfo();
       // var secondCardInfo = getSecondCardInfo();
        //var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
       // var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
       // var amount = generateValidAmount(secondCardBalance);  // получение невалидной суммы, превышающей лимит на карте
       // var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo); // выбор карты для перевода
       // transferPage.makeTransfer(String.valueOf(amount), secondCardInfo); // попытка перевода невалидной суммы
       // transferPage.findErrorMessage("Выполнена попытка перевода суммы, превышающей остаток на карте списания");
       // var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
      //  var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
       // assertEquals(firstCardBalance, actualBalanceFirstCard);
       // assertEquals(secondCardBalance, actualBalanceSecondCard);
   // }
//}
