package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HttpAuthenticationTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false).setChannel("chrome"));
		// Http Authentication
		BrowserContext newContext = browser.newContext(new NewContextOptions().setHttpCredentials("admin", "admin"));
		Page page = newContext.newPage();
		page.navigate("https://the-internet.herokuapp.com/basic_auth");
		Locator locator = page.locator("//div[@class='example']");
		List<String> allInnerTexts = locator.allInnerTexts();
		for (String innerText : allInnerTexts) {
			System.out.println(innerText);
		}

		playwright.close();

	}
}
