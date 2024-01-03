package selenium_test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    
    private WebDriver driver;

    public Main(WebDriver driver) {
        this.driver = driver;
    }

    // Set implicit wait for the driver
    public void implicitlyWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    // Navigate to DuckDuckGo website
    public void navigateToDuckGo() {
        driver.get("https://duckduckgo.com/");
    }

    // Navigate to Python's Documentation website
    public void navigateToPython() {
        driver.get("https://docs.python.org/3/");
    }

    // Navigate to OpenAI website
    public void navigateToOpenAI() {
        driver.get("https://www.openai.com/");
    }

    // Wait for an element to be present on the page
    public void waitForElementToBePresent(By locator, int timeout_Seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_Seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Get a web element using its locator
    public WebElement getElement(By locator) {
        WebElement element = driver.findElement(locator);
        return element;
    }

    // Get a web element using its locator
    public String getTitle() {
        return driver.getTitle();
    }

    // Verify if the selected option in a dropdown matches the expected selection
    public boolean verifySelectedDropdown(String selectionExpected) {
        Select languageDropdown = new Select(driver.findElement(By.id("setting_kad")));
        String selectedOption = languageDropdown.getFirstSelectedOption().getText();
        return selectedOption.equals(selectionExpected);
    }

    // Navigate back in the web page history
    public void navigateBack() {
        driver.navigate().back();
    }
}
