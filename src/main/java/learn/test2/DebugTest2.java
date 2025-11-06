package learn.test2;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.options.AriaRole;

public class DebugTest2 {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com");

		// Debug
		page.pause();

		// getBy - Locators
		page.getByText("Get Started").click();
		page.getByRole(AriaRole.LINK, new GetByRoleOptions().setName("Edit")).click();
		page.getByPlaceholder("Enter hollywood movie name").fill("God of War");
		page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Practice")).click();
		String textContent2 = page.getByText("Practice Daily Automation!").textContent();
		System.out.println(textContent2);
		context.close();
		playwright.close();
	}

}
