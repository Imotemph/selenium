package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected final String URL = "https://the-internet.herokuapp.com/";
    protected final String PRECISE_TEXT_XPATH = "//*[text()='%s']";
    protected final String PARTICULAR_TEXT_XPATH = "//*[contains(text()='%s')]";
    protected final String RELATIVE_RESOURCE_PATH = "src/main/resources/";
    protected final String FULL_DOWNLOAD_PATH = System.getProperty("user.dir") + "/src/main/java/resources";
    protected WebDriverWait wait = null;
    protected final int MAX_WAIT = 20;


    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", FULL_DOWNLOAD_PATH);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.of(MAX_WAIT, ChronoUnit.SECONDS));
        driver.get(URL);
        driver.manage().window().maximize();
    }

    public void handleDownloadPrompt() {
        try {
            // Execute the AutoIT script
            Runtime.getRuntime().exec("C:\\Users\\imote\\IdeaProjects\\A.ASHIF\\src\\main\\resources\\handleDownloadPrompt.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
