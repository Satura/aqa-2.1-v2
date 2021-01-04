package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestUI {

    @BeforeAll
    void setUp(){
        Configuration.headless = true;
    }

    @Test
    void shouldTest(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Вася");
        $("[data-test-id=phone] input").setValue("+79516324578");
        $("[data-test-id=agreement]").click();
        $("button").click();

        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    // Задача #2

    @Test
    void wrongName(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Vasya");
        $("button").click();

        $("[data-test-id=name] .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void emptyName(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("");
        $("button").click();

        $("[data-test-id=name] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void wrongPhone(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Вася");
        $("[data-test-id=phone] input").setValue("789");
        $("button").click();

        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void emptyPhone(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Вася");
        $("[data-test-id=phone] input").setValue("");
        $("button").click();

        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void notAgreement(){
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Вася");
        $("[data-test-id=phone] input").setValue("+79516324578");
        $("button").click();

        $("label").shouldHave(Condition.cssClass("input_invalid"));
    }
}
