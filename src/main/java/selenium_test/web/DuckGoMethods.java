package selenium_test.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DuckGoMethods {

    private WebDriver driver;

    public DuckGoMethods(WebDriver driver) {
        this.driver = driver;
    }

    // Open the main menu
    public void openMainMenu() {
        driver.findElement(By.cssSelector("button[class='legacy-homepage_legacyButton__oUMB9 legacy-homepage_hamburgerButton__VsG7q']")).click();
    }

    // Navigate to settings in the main menu
    public void mainMenu_Settings() {
        driver.findElement(By.cssSelector("a[href='/settings']")).click();
    }

    // Change language in settings
    public void changeLanguageInSettings(String lan) {
        Select lanDropdown = new Select(driver.findElement(By.id("setting_kad")));
        lanDropdown.selectByVisibleText(lan);
    }
}
