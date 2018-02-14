package pages;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends GadgetSearchPage {

    /*******************************************************************************************************************
     *                                          Локаторы страницы
     ******************************************************************************************************************/
    private static final String titleOfProduct = "div.n-title__text";

    public ProductPage(){
        super();
    }

    /*
     * Получение информации о продукте из заголовка
     */
    public String getProductTitle(){
        return $(titleOfProduct).getText();
    }

}
