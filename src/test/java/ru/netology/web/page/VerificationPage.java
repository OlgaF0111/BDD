package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");  // поле для введения кода верификации
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]"); // кнопка ПРОДОЛЖИТЬ на странице вериф.


    public VerificationPage() {

        codeField.shouldBe(visible);
    }

    // поиск элементов на странице вериф.
    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}




