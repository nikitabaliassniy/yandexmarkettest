package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.ArrayList;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import helpers.*;

/*
 * Описывает страницу поиска товаров
 */
public class GadgetSearchPage extends MainMarketPage {

    /*******************************************************************************************************************
     *                                          Локаторы страницы
     ******************************************************************************************************************/
    private static final String priceFromTextFieldId = "glf-pricefrom-var";
    private static final String gadgetFirmCheckBoxXPath = "//label[contains(text(), '%s')]";
    private static final String fitGadget = "div.n-snippet-card2__part_type_center>div>div>a ," +
            "div[class*=list_size_3]>div>div>div>a[title]";
    private static final String counterPanel = "div.n-filter-panel-counter";
    private static final String applyFiltersButton = "div.n-filter-panel-aside__apply>button";
    private static final int quantityOfGadgetsOnPage = 12;

    public GadgetSearchPage(){
        super();
    }

    /*
     * Проверка того, что результаты поиска обновились
     */
    private void checkForSearchUpdate(){
        $(counterPanel).shouldBe(Condition.visible);
    }

    /*
     * Установка значения в поле начальной цены товара
     */
    public void setPriceFromFilter(String gadgetPriceFrom){
        if(gadgetPriceFrom == null) return;
        setValueToTextFieldById(By.id(priceFromTextFieldId), gadgetPriceFrom);
        checkForSearchUpdate();
    }

    /*
     * Установка фильтра по марке продукта
     */
    public void selectProductFirmFilter(ArrayList<String> gadgetFirmNames){
        for(String currentFirmName : gadgetFirmNames){
            $(By.xpath(String.format(gadgetFirmCheckBoxXPath, currentFirmName))).click();
            checkForSearchUpdate();
        }
    }

    /*
     * Нажатие на кнопку применения фильтров
     */
    public void buttonApplyFiltersClick(){
        $(applyFiltersButton).click();
    }

    /*
     * Проверка заданного числа подходящих товаров на странице
     */
    public void quantityCheck(){
        $$(fitGadget).shouldHaveSize(quantityOfGadgetsOnPage);
    }

    /*
     * Сохранение первого подходящего под параметры продукта
     */
    public String firstFitProductCopy(){
        SelenideElement firstFitProduct = $(fitGadget);
        return firstFitProduct.getText();
    }

    /*
     * Поиск и открытие страницы подходящего товара
     */
    public void productSearchAndOpen(String productForSearch) throws InterruptedException{
        productSearch(productForSearch);
        if(!WebDriverContainer.getInstance().getWebDriver().getCurrentUrl().contains("catalog")) ;
        else
            $(fitGadget).click();
    }

}
