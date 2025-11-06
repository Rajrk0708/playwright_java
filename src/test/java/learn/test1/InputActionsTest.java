package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.PressSequentiallyOptions;

public class InputActionsTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/practice/input");

		// Type Text
		page.locator("#movieName").fill("Mersal");

		// Press Keys
		Locator locator = page.locator("#appendText");
		locator.press("End");
		locator.pressSequentially("gamer", new PressSequentiallyOptions().setDelay(100));

		// Get Attribute Value
		page.locator("#insideText").getAttribute("value");

		// Clear Text
		page.locator("#clearText").clear();

		// Highlight Element
		page.locator("#disabledInput").highlight();

		// Get Text
		String textContent = page.locator("label[for='readonlyInput']").textContent();
		System.out.println(textContent);

		playwright.close();
	}

}
