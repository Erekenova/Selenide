import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.openqa.selenium.By;

public class Demo extends BaseTest {

    @Test
    public void test1() {
        // Ожидаем появления основного меню
        SelenideElement menu = $("#app > div > div > div.home-body > div > div:nth-child(n)");

        menu.should(visible);
        $("div.category-cards div.card").should(Condition.exist);
        // Получаем все пункты меню
        int menuItemsCount = menu.$$("*").size();
        // Проверяем, что пунктов меню равно шести
        if (menuItemsCount == 6) {
            System.out.println("Пунктов меню равно шести");
        } else {
            System.out.println("Пунктов меню не равно шести, их количество: " + menuItemsCount);
        }
        // Кликаем по пункту меню "Alerts, Frame & Windows"
        $x("//h5[text()='Alerts, Frame & Windows']").click();
        // Проверяем, что отображается пункт меню "Browser Windows"
        SelenideElement browserWindowsMenu = $x("//span[text()='Browser Windows']");
        if (browserWindowsMenu.isDisplayed()) {
            System.out.println("Элемент 'Browser Windows' отображается успешно.");
        } else {
            System.out.println("Элемент 'Browser Windows' не найден или не отображается.");
        }
        // Проверяем, что отображается пункт меню "Alerts"
        SelenideElement alertsMenu = $x("//span[text()='Alerts']");
        if (alertsMenu.isDisplayed()) {
            System.out.println("Элемент 'Alerts' отображается успешно.");
        } else {
            System.out.println("Элемент 'Alerts' не найден или не отображается.");
        }
        // Проверяем, что отображается пункт меню "Frames"
        SelenideElement framesMenu = $x("//span[text()='Frames']");
        if (framesMenu.isDisplayed()) {
            System.out.println("Элемент 'Frames' отображается успешно.");
        } else {
            System.out.println("Элемент 'Frames' не найден или не отображается.");
        }
        // Проверяем, что отображается пункт меню "Nested Frames"
        SelenideElement nestedMenu = $x("//span[text()='Nested Frames']");
        if (nestedMenu.isDisplayed()) {
            System.out.println("Элемент 'Nested Frames' отображается успешно.");
        } else {
            System.out.println("Элемент 'Nested Frames' не найден или не отображается.");
        }
        // Проверяем, что отображается пункт меню "Modal Dialogs"
        SelenideElement modalMenu = $x("//span[text()='Modal Dialogs']");
        if (modalMenu.isDisplayed()) {
            System.out.println("Элемент 'Modal Dialogs' отображается успешно.");
        } else {
            System.out.println("Элемент 'Modal Dialogs' не найден или не отображается.");
        }
        // Переходим к пункту 'Nested Frames'
        $x("//span[text()='Nested Frames']").click();
        // Проверка, что внутри есть текст 'Child Iframe'
        SelenideElement childIframe = $("iframe");
        Selenide.switchTo().frame(childIframe);
        SelenideElement childIframeText = $x("//body[contains(text(),'Child Iframe')]");
        if (childIframe.exists()) {
            System.out.println("Текст 'Child Iframe' найден внутри вложенного фрейма");
        } else {
            System.out.println("Текст 'Child Iframe' не найден");
        }
        // Вернуться в родительский фрейм
        switchTo().parentFrame();

        // Переходим в родительский фрейм и проверяем для там есть текст 'Parent frame'
        SelenideElement parentFrame = $("iframe");
        SelenideElement parentIframeText = $x("//body[contains(text(),'Parent frame')]");
        if (parentFrame.exists()) {
            System.out.println("Текст 'Parent frame' найден внутри вложенного фрейма");
        } else {
            System.out.println("Текст 'Parent frame' не найден");
        }
        // Вернуться в основной документ
        switchTo().defaultContent();

        // Проверка, что страница содержит текст 'Sample Nested Iframe page'
        SelenideElement pageText = $x("//*[@id=\"framesWrapper\"]/div[1][contains(text(), 'Sample Nested Iframe page')]");
        if (pageText.exists()) {
            System.out.println("Текст 'Sample Nest ed Iframe page' найден на странице");
        } else {
            System.out.println("Текст 'Sample Nested Iframe page' не найден");
        }
    }

    @Test
    public void test2() {
        //  Кликнуть по меню "Widget"
        $x("//h5[text()='Widgets']").click();

        // На странице Widgets кликнуть по пункту "Progress Bar"
        $x("//span[text()='Progress Bar']").click();
        //  Проверить, что имеется кнопка с текстом "Start"
        SelenideElement startButton = $x("//button[text()='Start']");
        if (startButton.exists()) {
            System.out.println("Кнопка 'Start' найдена");
        } else {
            System.out.println("Кнопка 'Start' не найдена");
            return;
        }
        //  Кликнуть по кнопке "Start"
        startButton.click();

        // Проверить, что на форме имеется кнопка "Stop"
        SelenideElement stopButton = $x("//button[text()='Stop']");
        if (stopButton.exists()) {
            System.out.println("Кнопка 'Stop' найдена");
        } else {
            System.out.println("Кнопка 'Stop' не найдена");
            return;
        }
        //Когда прогресс бар достигнет 30% нажать "Stop"
        SelenideElement progressValue = $x("//div[@role='progressbar']");
        while (true) {
            String progressText = progressValue.getText().trim(); // например, "30%"
            if (progressText.isEmpty()) {
                sleep(500);
                continue;
            }
            try {
                int progressPercent = Integer.parseInt(progressText.replace("%", "").trim());
                if (progressPercent >= 30) {
                    stopButton.click();
                    System.out.println("Прогресс достиг 30%, нажата кнопка 'Stop'");
                    break;
                }
            } catch (NumberFormatException e) {
                // Если текст не число, пропускаем
                sleep(500);
            }
            sleep(500);
        }
    }

    @Test
    public void test3() {

        //  Кликнуть по меню "Widgets"
        $x("//h5[text()='Widgets']").click();

        // На странице Widgets кликнуть по пункту "Select Menu"
        $x("//span[text()='Select Menu']").click();

        //  В выпадающем списке "Old Style Select Menu" выбрать значение "Red"
        SelenideElement oldStyleSelect = $x("//select[@id='oldSelectMenu']");
        oldStyleSelect.selectOption("Red");

        //  Проверить, что в списке выбрано требуемое значение
        String selectedOption = oldStyleSelect.getSelectedOption().getText();
        if ("Red".equals(selectedOption)) {
            System.out.println("Выбрано правильное значение: Red");
        } else {
            System.out.println("Некорректное выбранное значение: " + selectedOption);
        }

        //  В выпадающем списке "Multiselect drop down" выбрать более одного значения
        SelenideElement multiSelect = $x("//select[@id='cars']");
        multiSelect.selectOption("Volvo");
        multiSelect.selectOption("Saab");

        //  Проверить, что в списке выбраны требуемые значения
        var selectedOptions = multiSelect.getSelectedOptions().texts();
        if (selectedOptions.contains("Volvo") && selectedOptions.contains("Saab")) {
            System.out.println("Выбраны правильные значения: " + selectedOptions);
        } else {
            System.out.println("Некорректные выбранные значения: " + selectedOptions);
        }
    }

    }



