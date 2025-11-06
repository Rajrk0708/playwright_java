package learn.test3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CrossBrowserTest {
	
	Playwright playwright;
    Browser browser;
    Page page;

    @BeforeMethod
    @Parameters("browserName")
    public void setup(@Optional("chromium") String browserName) {
        playwright = Playwright.create();
        BrowserType browserType = switch (browserName.toLowerCase()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium(); // Default: Chrome
        };
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://playwright.dev/java");
    }

    @Test
    public void sampleTest() {
        System.out.println("Page Title: " + page.title());
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }


}
