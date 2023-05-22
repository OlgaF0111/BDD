package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferHead = $(byText("Пополнение карты")); // открытие страницы ПОПОНЕНИЕ КАРТЫ
    private final SelenideElement amountInput = $("[data-test-id='amount'] input"); // поле ввода суммы перевода
    private final SelenideElement fromInput = $("[data-test-id='from'] input"); // поле ввода номера карты откуда переводим
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']"); //кнопка ПОПОЛНИТЬ
    private final SelenideElement errorMessage = $("[data-test-id='error-message']"); //сообщение об ошибке "Оштбка! Произошла ошибка"

    public TransferPage() {

        transferHead.shouldBe(visible);
    }

    //метод открытия новой страницы после заполнения существующей формы валидными данными
    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage(); // открывается странца Дашборда и переходим туда выполнять ее методы
    }

    //метод ничего не возвращающий, заполняющий поля
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer); // заполнение поля ввода суммы
        fromInput.setValue(cardInfo.getCardNumber());  // заполнение поля ввода карты списания
        transferButton.click(); //нажатие на кнопку
    }

    // метод ничего не возвращает, ищет сообщение об ошибке в течении 15 сек и проверяет видимость сообщ.
    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

}
