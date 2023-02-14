package com.sberbank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class SoftwareProductTests extends TestBase {


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

    @Test
    void checkElementTest() {
        step("Check that the menu has elements", () ->{
           $(".kitt-top-menu__item_first").shouldHave(text("О нас"));
        });
    }


    @Test
    void searchGenreTest () {
        step("Кликнуть по полю поиск", ()-> {
            $(".kitt-header-search").click();
            $(".kitt-header-search__search").setValue("Карьера").pressEnter();
        });
        step("Проверить корректность ввода данных", ()-> {
            $(".b-serp-item__title").shouldHave(text("Развитие сотрудников — СберБанк"));
        });
    }

    @Test
    void OpeningInHeaderMenuCheckTest () {
        step ("Кликнуть по полю О нас", ()-> {
            $(".kitt-top-menu__link_first").click();
        });
        step ("Кликнуть по полю Миссия и ценности в открывшемся меню", ()-> {
            $(".kitt-top-menu__link_second").click();
        });
        step ("Проверить корректность открывшейся страницы", ()-> {
            $(".rt-content").shouldHave(text("Наши ценности"));
        });

    }


}
