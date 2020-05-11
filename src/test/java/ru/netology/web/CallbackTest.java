package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    void correctlyTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ваня Васин");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void correctlyTestV2() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ваня Васин");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void checkSurnameTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("David Vasin");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void checkPhoneNumberTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ваня Васин");
        form.$("[data-test-id=phone] input").setValue("879270000015");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}

