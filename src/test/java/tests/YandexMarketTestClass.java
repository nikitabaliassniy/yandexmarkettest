package tests;

import helpers.*;
import models.GadgetData;
import org.testng.annotations.Test;
import pages.*;
import java.io.UnsupportedEncodingException;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class YandexMarketTestClass extends AbstractTestClass {

    private MainYandexPage mainYandexPage = new MainYandexPage();
    private MainMarketPage mainMarketPage = new MainMarketPage();
    private GadgetSearchPage gadgetSearchPage = new GadgetSearchPage();
    private ProductPage productPage = new ProductPage();

    public YandexMarketTestClass(){
        super();
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProviderClass.class)
    public void gadgetSearchTest(GadgetData gadget) throws UnsupportedEncodingException, InterruptedException {
        open(ConfigContainer.getInstance().getConfigParameter("site_url"));
        mainYandexPage.openYandexMarket();
        assertTrue(mainMarketPage.checkIfOpened());
        mainMarketPage.checkCurrentRegion();
        mainMarketPage.openElectronicSubsection(gadget.getType());
        gadgetSearchPage.selectProductFirmFilter(gadget.getFirmNames());
        gadgetSearchPage.setPriceFromFilter(gadget.getPriceFrom());
        gadgetSearchPage.buttonApplyFiltersClick();
        gadgetSearchPage.quantityCheck();
        String productName = gadgetSearchPage.firstFitProductCopy();
        gadgetSearchPage.productSearchAndOpen(productName);
        assertEquals(productName, productPage.getProductTitle());
    }

}
