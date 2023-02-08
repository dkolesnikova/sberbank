package com.sberbank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SoftwareProductTests {

    @BeforeEach
    void setUp() {
        open("https://www.sberbank.com/ru");
    }

    @Test
    @DisplayName("Проверка переключения сайта на другой язык (EN)")
    void checkLocaleTest (){
        step("Click on the button EN", () -> {
            $(".kitt-header__lang_en").click();
        });
        step("Check that the menu has become english elements", ()->{
            $(".kitt-content").shouldHave(text("Investor Relations"));
        });
    }

    @ValueSource(strings = {
            "О нас", "Устойчивое развитие", "Корпоративное управление", "Акционерам и инвесторам", "СберПресс", "Экосистема", "Частным клиентам", "Контакты"})
    @ParameterizedTest(name = "Check for the presence of the element {0} on the main page in the header" )
    void checkElementTest(String element) {
        step("Check that the menu has elements", () ->{
           $(".kitt-top-menu__list_left").shouldHave(text(element));
        });
    }

    @DisplayName("Check 'Sberpress' button in the footer page ")
    @Test
    void checkSberPressButtonInFooterPage() {
        step("Click on the 'СберПресс' button and check page has 'Все новости' text", () ->{
           $(".kitt-footer-help__link").$(byText("СберПресс")).click();
           $(".na-header").shouldHave(text("Все новости"));
        });
    }
}
