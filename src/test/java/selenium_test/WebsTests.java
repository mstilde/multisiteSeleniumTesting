package selenium_test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import selenium_test.config.DriverFactory;
import selenium_test.web.DuckGoMethods;
import selenium_test.web.OpenAIMethods;
import selenium_test.web.PythonMethods;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class WebsTests {

    private WebDriver driver;
    private DriverFactory setUp;
    private Main chrome;
    private DuckGoMethods chrome_DuckGo;
    private PythonMethods chrome_Python;
    private OpenAIMethods chrome_OpenAI;

    @BeforeEach
    public void setUpChrome() {
        // Create an instance of DriverFactory to manage the WebDriver
        setUp = new DriverFactory(driver);
        driver = setUp.initiateDriver();
        // Create an instance of the Main class for common WebDriver operations
        chrome = new Main(driver);
        // Set implicit wait for elements to be present
        chrome.implicitlyWait(10);
        // Create an instance of DuckGoMethods for DuckDuckGo-specific actions
        chrome_DuckGo = new DuckGoMethods(driver);
        // Create an instance of PythonMethods for Python-specific actions
        chrome_Python = new PythonMethods(driver);
        // Create an instance of OpenAIMethods for OpenAI-specific actions
        chrome_OpenAI = new OpenAIMethods(driver);
    }

    @AfterEach
    public void closeChrome() {
        // Quit the WebDriver after each test
        driver.quit();
    }

    // TC-01 - Verify that you can succesfully change the language to Spanish (Argentina)
    @Test
    public void duckGo_changeLanguageTest_ES_AR() {
        chrome.navigateToDuckGo();
        chrome_DuckGo.openMainMenu();
        chrome_DuckGo.mainMenu_Settings();
        // Wait for the language selector dropdown to be present on the page
        chrome.waitForElementToBePresent(By.cssSelector("select[id=setting_kl]"), 30);
        // Change the language to "Español (Argentina)"
        chrome_DuckGo.changeLanguageInSettings("Español (Argentina)");
        
        // Verify that the selected language is "Español (Argentina)"
        assertTrue(chrome.verifySelectedDropdown("Español (Argentina)"));
    }

    // TC-02 - Verify that you can succesfully access to the Begginer's Guide of the Phyton Documentation
    @Test
    public void phyton_BegginersGuide_Test() {
        chrome.navigateToPython();
        // Select the option "Beginner's Guide" from the left menu.
        chrome_Python.selectLeftMenuOption("Beginner's Guide");

        // Wait for a main page element to load
        chrome.waitForElementToBePresent(By.cssSelector("h1[id='Beginner.27s_Guide_to_Python']"), 30);

        assertTrue(chrome.getTitle().equals("BeginnersGuide - Python Wiki"));
    }

    // TC-03 - Verify that you can succesfully navigate trough the OpenAI webpage sections
    @Test
    public void openAI_Navigation_Test() {
        // Navigate to the OpenAI website
        chrome.navigateToOpenAI();
        
        // Select the "Research" menu and click the "GPT-4" option
        chrome_OpenAI.selectMenuDropdownOption("Research", "GPT-4");
    
        // Wait for the page to load and verify the page is correct
        chrome.waitForElementToBePresent(By.cssSelector("h1[class='f-display-2']"), 30);
        assertTrue(chrome.getTitle().equals("GPT-4"));
    
        // Wait for the "Company" menu to load and select "Blog"
        chrome.waitForElementToBePresent(By.xpath("//*[@id=\"navList4\"]/li[2]/a"), 30);
        try {
            chrome_OpenAI.selectMenuDropdownOption("Company", "Blog");
        } catch(StaleElementReferenceException e) {
            // Retry selecting "Blog" if a Stale exception occurs
            chrome_OpenAI.selectMenuDropdownOption("Company", "Blog");
        }
    
        // Wait for the "Blog" page to load and verify the page is correct
        chrome.waitForElementToBePresent(By.cssSelector("h1[class='f-display-2 md:pr-40 lg:pr-44']"), 30);
        assertTrue(chrome.getTitle().equals("Blog"));
    
        // Search for "GPT-4" in the blog page
        chrome_OpenAI.searchBlogPage("GPT-4");
    
        // Wait for the search results to load
        chrome.waitForElementToBePresent(By.cssSelector("h1[class='f-display-2 md:pr-40 lg:pr-44']"), 30);
    
        // Get the text of the search result and the expected result
        String search = chrome.getElement(By.cssSelector("h1[class='f-display-2 md:pr-40 lg:pr-44']")).getText();
        String resultExpected = "Blog";
    
        // Verify that the search result matches the expected result
        assertEquals(resultExpected, search);
    }

}
