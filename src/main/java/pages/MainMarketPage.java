package pages;

import com.codeborne.selenide.Condition;
import helpers.WebDriverContainer;
import org.openqa.selenium.By;
import java.io.UnsupportedEncodingException;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.CharEncoding.ISO_8859_1;

/*
 * Описывает главную страницу яндекс-маркета
 */
public class MainMarketPage extends MainYandexPage {

    /*******************************************************************************************************************
     *                                          Локаторы страницы
     ******************************************************************************************************************/
    private static final String mainMenuItemElectronicXPath =
            "//a[contains(@class, 'topmenu__link') and contains(text(), 'Электроника')]";
    private static final String subsectionXPath = "//a[contains(text(), '%s')]";
    private static final String currentRegionSpanXPath =
            "//span[contains(@class, 'header2-menu__text') and contains(text(), '%s')]";
    private static final String declineRegionButton = "span.n-region-notification__change";
    private static final String regionInputTextField = "div.region-suggest input.input__control";
    private static final String regionInSuggestListXPath =
            "//div[contains(@class,'suggestick-list')]//*[contains(text(),'%s')]";
    private static final String suggestList = "div.suggestick-list";
    private static final String acceptRegionButton = "button.region-select-form__continue-with-new";
    private static final String acceptRegionInPopUpButton = "span.n-region-notification__ok";
    private static final String marketLogo = "a.logo_part_market";
    private static final String searchFieldId = "header-search";

    public MainMarketPage(){
        super();
    }

    /*
     * Открытие заданного подраздела из раздела "Электроника"
     */
    public void openElectronicSubsection(String subsectionName){
        focusOnElement(By.xpath(mainMenuItemElectronicXPath));
        $(By.xpath(String.format(subsectionXPath, subsectionName))).shouldBe(Condition.visible).click();
    }

    /*
     * Проверяет отображение логотипа яндекс-маркета на странице
     */
    public boolean checkIfOpened(){
        $(marketLogo).shouldBe(Condition.visible);
        return true;
    }

    /*
     * Проверка текущего региона на соответствие с заданным в конфиге
     */
    public void checkCurrentRegion() throws UnsupportedEncodingException {
        byte[] stringBytes = config.getConfigParameter("RegionName").getBytes(ISO_8859_1);
        String regionName = new String(stringBytes, UTF_8);
        if($(acceptRegionInPopUpButton).isEnabled() && $$(By.xpath(String.format(currentRegionSpanXPath, regionName))).size()==1)
            $(acceptRegionInPopUpButton).click();
        else if($(declineRegionButton).isDisplayed()) {
            $(declineRegionButton).click();
            $(regionInputTextField).shouldBe(Condition.visible).setValue(regionName);
            $(By.xpath(String.format(regionInSuggestListXPath, regionName))).shouldBe(Condition.visible).click();
            $(suggestList).shouldNotBe(Condition.visible);
            $(acceptRegionButton).shouldBe(Condition.visible).click();
            $(acceptRegionInPopUpButton).shouldBe(Condition.visible).click();
            $(By.xpath(String.format(currentRegionSpanXPath, ""))).click();
            $$(By.xpath(String.format(currentRegionSpanXPath, regionName))).shouldHaveSize(1);
        }
    }

    /*
     * Выполнение поиска товара с помощью поисковой строки
     */
    protected void productSearch(String productForSearch) throws InterruptedException{
        $(By.id(searchFieldId)).setValue(productForSearch).pressEnter();
        while(!WebDriverContainer.getInstance().getWebDriver().getCurrentUrl().
                contains(productForSearch.substring(productForSearch.lastIndexOf(" ") + 1))){
            Thread.sleep(100);
        }
    }

}
