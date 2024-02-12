package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class ConsentDataTest extends BaseTest{
    private final By POLICY_ACCEPT = By.xpath(String.format(PRECISE_TEXT_XPATH, "I Understand"));
    private final By SEARCH_INPUT = By.xpath("//input[contains(@class,'search-input')]");
    private final By SELECTED_CITY = By.xpath("//h1[contains(@class,'header-loc')]");
    private final String CITY_NAME = "New York";

    @Test
    public void insertingDataInTextField(){
        //TODO : part1 of final assessment
        WebElement Policy = driver.findElement(POLICY_ACCEPT);
        Assert.assertTrue(isClickable(Policy),"Button wasn't clickable");
        driver.findElement(POLICY_ACCEPT).click();
        //TODO : part2 of final assessment -> done
        WebElement textBox = driver.findElement(SEARCH_INPUT);
        Assert.assertTrue(isClickable(textBox),"Element wasn't clickable");
        textBox.sendKeys(CITY_NAME);
        //TODO : part3 of final assessment -> done
        WebElement search_result = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(@class,'search-bar-result__name')]")));
        search_result.click();
        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement cityName = driver.findElement(SELECTED_CITY);
        Assert.assertEquals(cityName.getText().split(",")[0].trim(),CITY_NAME,
                "City name didn't match");
    }
    private boolean isClickable(WebElement element){
        wait = new WebDriverWait(driver, Duration.of(MAX_WAIT, ChronoUnit.SECONDS));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (TimeoutException exception) {
            return false;
        }
        return true;
    }
}

