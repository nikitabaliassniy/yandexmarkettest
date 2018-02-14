package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;

/*
 * Описывает главную страницу яндекса
 */
public class MainYandexPage extends AbstractPage {

    /*******************************************************************************************************************
     *                                          Локаторы страницы
     ******************************************************************************************************************/
    private static final String openMarketButton = "a[data-id='market']";

    public MainYandexPage(){
        super();
    }

    /*
     * Открытие страницы маркета
     */
    public void openYandexMarket(){
        $(openMarketButton).shouldBe(Condition.visible).click();
    }
}
