package selenium_test.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenAIMethods {
    
    private WebDriver driver;

    public OpenAIMethods(WebDriver driver) {
        this.driver = driver;
    }

    // Select a menu by name and click on it
    public WebElement selectMenu(String menuWanted) {
        WebElement menu = null;
        List<WebElement> menuOptions = driver.findElements(By.cssSelector("li[class='ml-24 first:ml-0 mt-1']"));

        for (WebElement menuOption : menuOptions) {
            // Extract the menu name
            String menuName = menuOption.findElement(By.cssSelector("span[class='flex items-center group']"))
                .findElement(By.tagName("span")).getText();
            if (menuName.equals(menuWanted)) {
                menu = menuOption;
                menuOption.click();
            }
        }

        return menu;
    }

    // Select an option from a dropdown menu
    public void selectMenuDropdownOption(String menuExpected, String optionExpected) {

        WebElement menuSelected = selectMenu(menuExpected);

        List<WebElement> researchOptions = menuSelected.findElements(By.cssSelector("span[class='flex items-center']"));

        for (WebElement researchOption : researchOptions) {
            // Extract the option name
            String optionName = researchOption.findElement(By.tagName("span")).getText();
            
            if (optionName.equals(optionExpected)) {
                // Click on the desired option
                researchOption.click();
            }
        }
    }

    // Search for a term in the blog page
    public void searchBlogPage(String search) {
        // Click on the search button
        driver.findElements(By.cssSelector("button[aria-label='Search']")).get(1).click();
        WebElement searchFunction = driver.findElement(By.cssSelector("input[type='Search']"));
        // Enter the search term and press Enter
        searchFunction.sendKeys(search);
        searchFunction.sendKeys(Keys.RETURN);
    }
}
