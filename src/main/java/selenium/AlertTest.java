package selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTest extends BaseTest {
    private final By JS_ALERT = By.xpath(String.format(PRECISE_TEXT_XPATH, "JavaScript Alerts"));
    private final By CLICK_FOR_JS_ALERT_BUTTON = By.xpath("//button[@onclick='jsAlert()']");
    private final By CLICK_FOR_JS_PROMPT_BUTTON = By.xpath("//*[contains(@onclick, 'jsPrompt()')]");
    private String MESSAGE = "I accept your challenge";
    private String ENTERED_MESSAGE = "You entered: " + MESSAGE;
    private final By ENTERED_MESSAGE_RESULT = By.xpath("//*[contains(@id, 'result')]");
    private final By SUCCESS_MSG_LOCATOR = By.xpath(String.format(PRECISE_TEXT_XPATH, "You successfully clicked an alert"));



    @Test
    public void alertTest() {
        driver.findElement(JS_ALERT).click();
        driver.findElement(CLICK_FOR_JS_ALERT_BUTTON).click();
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(SUCCESS_MSG_LOCATOR).isDisplayed(),
                "Success message is not displayed!");
        driver.findElement(CLICK_FOR_JS_PROMPT_BUTTON).click();
        driver.switchTo().alert().sendKeys(MESSAGE);
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(ENTERED_MESSAGE_RESULT).getText(), ENTERED_MESSAGE,
        "Message did not match or displayed");
    }

}
