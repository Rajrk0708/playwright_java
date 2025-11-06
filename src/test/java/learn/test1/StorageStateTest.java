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

public class StorageStateTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(
				new NewContextOptions().setViewportSize(null));
		// .setStorageStatePath(Paths.get("auth/adactinhotel.json")));
		Page page = context.newPage();
		page.navigate("https://adactinhotelapp.com/index.php");
		page.locator("//img[@alt='Hotel Image 3']").waitFor(new WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator("id=username").fill("Rajvimal07");
		page.locator("id=password").fill("Admin123");
		page.locator("id=login").click();
		System.out.println("Page Title: " + page.title());
		String innerText = page.locator("//*[contains(text(), 'Welcome to Adactin')]").innerText();
		System.out.println(innerText);
		// Generate the Auth
//		context.storageState(new StorageStateOptions().setPath(Paths.get("auth/adactinhotel.json")));
		playwright.close();

	}

}
