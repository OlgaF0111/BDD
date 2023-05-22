package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]"); // страница личного кабинета
    private static final ElementsCollection cards = $$(".list__item div"); //поле с данными карт
    private final String balanceStart = ", баланс: "; // стартовый баланс по картам
    private final String balanceFinish = " р. "; //сумма в рублях

    // конструктор проверки видимости страницы приложения
    public DashboardPage() {

        heading.shouldBe(visible);
    }

    // метод возвращает баланс карты (метод поиска по тексту)
    public int getCardBalance(DataHelper.CardInfo cardInfo) { //числовое значение баланса карты
        var text = cards.findBy(text(cardInfo.getCardNumber().substring(15))).getText();
        return extractBalance(text);
    }

    //метод нажатия на кнопку ПОПОЛНИТЬ напротив карты (метод поиска по атрибуту)
    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id", cardInfo.getTestId())).$("button").click(); //ищет кнопку и кликает на нее
        return new TransferPage();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);  // стартовый баланс
        var finish = text.indexOf(balanceFinish); // баланс после перевода
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);

    }

}
