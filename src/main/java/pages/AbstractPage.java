package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.WebDriverContainer;
import helpers.ConfigContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import static com.codeborne.selenide.Selenide.$;

/*
 * Описывает абстрактную страницу, от которой наследуются все реальные страницы
 */
public class AbstractPage {

    /*******************************************************************************************************************
     *                                             Поля класса
     ******************************************************************************************************************/
    protected WebDriver driver;
    protected ConfigContainer config;

    protected AbstractPage() {
        this.driver = WebDriverContainer.getInstance().getWebDriver();
        this.config = ConfigContainer.getInstance();
    }

    /*
     * Установка значения в текстовое поле по его локатору
     */
    protected void setValueToTextFieldById(By by, String valueToSet){
        SelenideElement textField = $((by));
        textField.clear();
        textField.setValue(valueToSet);
        textField.shouldHave(Condition.value(valueToSet));
    }

    /*
     * Наведение курсора на элемент по его локатору
     */
    protected void focusOnElement(By by){
        Actions actions = new Actions(WebDriverContainer.getInstance().getWebDriver());
        actions.moveToElement($(by)).build().perform();
    }

}
