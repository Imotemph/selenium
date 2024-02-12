package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicLoadingTest extends BaseTest{
    private final By DYNAMIC_LOADING = By.xpath(String.format(PRECISE_TEXT_XPATH, "Dynamic Loading"));
    private final By HIDDEN_CONTENT = By.xpath(String.format(PRECISE_TEXT_XPATH, "Example 1: Element on page that is hidden"));
    private final By RENDERED_AFTER_FACT = By.xpath(String.format(PRECISE_TEXT_XPATH, "Example 2: Element rendered after the fact"));
    private final By START_BUTTON = By.xpath("//*[contains(@id, 'start')]//button");
    private final By FINISH_MSG = By.id("finish");
    private String HIDDEN_MSG = "Hello World!";


    @Test
    public void dynamicLoadingTest(){
        driver.findElement(DYNAMIC_LOADING).click();
        driver.findElement(HIDDEN_CONTENT).click();
        driver.findElement(START_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_MSG));
        Assert.assertEquals(driver.findElement(FINISH_MSG).getText(), HIDDEN_MSG,
                "Message does not match or displayed");

        driver.switchTo().window(driver.getWindowHandle()).navigate().back();
        driver.findElement(RENDERED_AFTER_FACT).click();
        driver.findElement(START_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_MSG));
        Assert.assertEquals(driver.findElement(FINISH_MSG).getText(), HIDDEN_MSG,
                "Message does not match or displayed");

    }
}
