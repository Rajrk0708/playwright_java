package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;

public class MultipleElementsTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/practice/input");
		Locator texts = page.locator("//label");
		
		// Explicit Wait
		texts.last().waitFor(new WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(20000));
		int count = texts.count();
		System.out.println("Count: " + count);
		for(int i = 8; i< count; i++) {
		String innerText = texts.nth(i).innerText();
		System.out.println(innerText);
		}
		
		playwright.close();

	}

}
