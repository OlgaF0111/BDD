package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");  // поле ввода логина
    private SelenideElement passwordField = $("[data-test-id=password] input"); // поле ввода пароля
    private SelenideElement loginButton = $("[data-test-id=action-login]");  // кнопка ПРОДОЛЖИТЬ

    //поиск элементов на странице браузера
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }
}
