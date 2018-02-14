package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.io.File;

public class WebDriverContainer {

    // Статический экземпляр этого класса
    private static WebDriverContainer instance;
    // Статический экземпляр браузера
    private static WebDriver driver;
    // Относительный путь к папке с драйверами
    private static final String driverPath = "src/test/resources/drivers/";

    /**
     * Возвращает статический экземпляр класса
     */
    public static synchronized WebDriverContainer getInstance(){
        if(instance == null)
            instance = new WebDriverContainer();
        return instance;
    }

    /**
     * Возвращает статический экземпляр драйвера
     */
    public WebDriver getWebDriver(){
        return driver;
    }

    /**
     * Инициализация экземпляр браузера
     */
    public void setWebDriver(String driverType) {
        switch (driverType){
            case "Firefox":
                this.setFirefoxWebDriver();
                break;
            case "Chrome":
                this.setChromeDriver();
                break;
        }
    }

    /**
     * Настройка ChromeDriver
     */
    private void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File(driverPath + "Chromium/chrome.exe"));
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
    }

    /**
     * Настройка FirefoxDriver
     */
    private void setFirefoxWebDriver(){
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(null, profile);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        WebDriverRunner.setWebDriver(driver);
    }

}
