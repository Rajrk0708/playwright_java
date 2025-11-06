package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BrowserContextTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://adactinhotelapp.com/index.php");
		page.locator("//img[@alt='Hotel Image 3']").waitFor(new WaitForOptions().setTimeout(50000));
		page.locator("id=username").fill("Rajvimal07");
		page.locator("id=password").fill("Admin123");
		page.locator("id=login").click();

		System.out.println("Page Title: " + page.title());

		// new tab in same window
		Page newPage = context.newPage();
		newPage.navigate("https://adactinhotelapp.com/SearchHotel.php");
		System.out.println("Page Title: " + page.title());
		
		// new window without cookies and cache
		BrowserContext newContext = browser.newContext();
		Page page2 = newContext.newPage();
		page2.navigate("https://adactinhotelapp.com/index.php");
		page2.locator("//img[@alt='Hotel Image 3']").waitFor(new WaitForOptions().setState(WaitForSelectorState.ATTACHED));
		System.out.println("Page Title: " + page2.title());

		// Access element in previous tab
		page.bringToFront();
		String innerText = page.locator("//*[contains(text(), 'Welcome to Adactin')]").innerText();
		System.out.println(innerText);
		
		// Closing
		newPage.close();
		newContext.close();
		playwright.close();

	}

}
