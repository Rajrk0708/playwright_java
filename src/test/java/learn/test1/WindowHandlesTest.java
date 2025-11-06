package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class WindowHandlesTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/practice/window");

		// Single tab
		System.out.println("Single Tab");

		Page popup = page.waitForPopup(() -> {
			page.locator("'Open Home Page'").click();
		});
		popup.waitForLoadState();
		System.out.println("New Tab: " + popup.title());
		System.out.println("New Tab: " + popup.url());
		popup.close();

		// Multi tab
		System.out.println("Multi Tab");

		page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(p -> p.context().pages().size() == 3), () -> {
			page.locator("'Open Home Page'").click();
			page.locator("'Multiple windows'").click();
		});
		List<Page> pages = page.context().pages();
		for (Page tabs : pages) {
			System.out.println(tabs.url());
		}
		
		// Accessing pages/tabs
		Page homePage = pages.get(1);
		Page selectPage = pages.get(2);
		String innerText = homePage.locator("h1").innerText();
		System.out.println(innerText);
		String innerText2 = selectPage.locator("//button/span[text()='Select Fruit']").innerText();
		System.out.println(innerText2);
		selectPage.close();
		homePage.close();
		playwright.close();

	}

}
