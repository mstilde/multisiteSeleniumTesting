package selenium_test.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PythonMethods {

    private WebDriver driver;

    public PythonMethods(WebDriver driver) {
        this.driver = driver;
    }
    
    // Select the left menu
    private WebElement selectLeftMenu() {
        WebElement menu = driver.findElement(By.className("sphinxsidebarwrapper"));
        return menu;
    }

    // Select an option from the left menu
    public void selectLeftMenuOption(String optionToSelect) {
        try {
            // Get the left menu element
            WebElement leftMenu = selectLeftMenu();
            List<WebElement> leftMenuOptions = leftMenu.findElements(By.tagName("a"));

            // Iterate through the left menu options
            for (WebElement menuOption : leftMenuOptions) {
                if (menuOption.getText().equals(optionToSelect)) {
                    // Click on the desired menu option
                    menuOption.click();
                }
            }
        } catch (StaleElementReferenceException e) {
            // Handle the exception if it occurs
            System.err.println("Elemento web volvió 'stale': " + e.getMessage());
        }
    }

}
