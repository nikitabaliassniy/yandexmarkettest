package tests;

import com.codeborne.selenide.Configuration;
import helpers.ConfigContainer;
import helpers.WebDriverContainer;
import org.testng.annotations.*;

public abstract class AbstractTestClass {

    private ConfigContainer config;

    @BeforeClass
    public void setUp() {
        config = ConfigContainer.getInstance();
        config.loadConfig();
        Configuration.timeout = 30000;
    }

    @BeforeMethod
    public void testSetUp(){
        WebDriverContainer.getInstance().setWebDriver(config.getConfigParameter("DriverType"));
    }

    @AfterMethod
    public void tearDown(){
        WebDriverContainer.getInstance().getWebDriver().close();
    }

}
