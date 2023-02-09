package com.sberbank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
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


    @Test
    void searchGenreTest () {
        step("Кликнуть по полю поиск", ()-> {
            $(".kitt-header-search").click();
            $(".kitt-header-search__search").setValue("Карьера").pressEnter();
        });
        step("Проверить корректность ввода данных", ()-> {
            $(".b-serp-item__title").shouldHave(text("Лента ежедневных новостей СберБанка и его дочерних компаний"));
        });
    }

    @Test
    void JobSearchCheckTest () {
        step ("Кликнуть по полю Еще", ()-> {
            $(".kitt-header-sections__more-text_common").click();
        });
        step("Перейти в раздел Вакансии", ()-> {
            $x("//div[@class='kitt-header-sections__dropdown']/a[contains(text(),'Вакансии')]").click();
        });
        step("Кликнуть и ввести запрос в строку поиска",()-> {
            $(".styled__SearchWrapper-sc-h3io0q-6 dlnYlK").shouldHave(text("Найти вакансию"));
        });
    }


}
