package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MultipleWindowTest extends BaseTest {

    private final By MULTIPLE_WINDOWS = By.xpath(String.format(PRECISE_TEXT_XPATH, "Multiple Windows"));
    private final By CLICK_FOR_CLICK_HERE = By.xpath("//a[contains(text(), 'Click Here')]");
    private final By NEW_WINDOW_TEXT = By.xpath("//*[contains(@class, 'example')]//h3");
    private String MULTIPLE_WINDOW_MSG = "Opening a new window";
    private String NEW_WINDOW_MSG = "New Window";
    @Test
    public void multipleWindowsTest() {

        driver.findElement(MULTIPLE_WINDOWS).click();
        Assert.assertEquals(driver.findElement(NEW_WINDOW_TEXT).getText(), MULTIPLE_WINDOW_MSG,
                "Multiple window page was not displayed");

        String originalWindowHandle = driver.getWindowHandle();
        driver.findElement(CLICK_FOR_CLICK_HERE).click();

        wait.until(numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindowHandle.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wait.until(titleIs("New Window"));

        Assert.assertEquals(driver.findElement(NEW_WINDOW_TEXT).getText(), NEW_WINDOW_MSG,
                "New Window is Displayed");


        //testing about closing current window and sending control to current window
        //Asserting if we're back to multiple window page
        /*driver.close();

        driver.switchTo().window(originalWindowHandle);
        Assert.assertEquals(driver.findElement(NEW_WINDOW_TEXT).getText(), MULTIPLE_WINDOW_MSG,
                "Multiple window page was not displayed");*/
    }
}