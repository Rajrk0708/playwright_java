package learn.test1;

import java.util.List;
import java.util.function.Consumer;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Dialog;

public class AlertsTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/practice/alert");

		// Consumer to handle alert 
		Consumer<Dialog> alert = new Consumer<Dialog>() {
			public void accept(Dialog a) {
				System.out.println("Dialog message: " + a.message());
				a.accept("Raj");
				page.offDialog(this);
			}
		};
		
		// Using Consumer to handle alert
		page.onDialog(alert);
		page.locator("id=prompt-alert").click();
		String innerText = page.locator("//*[contains(text(), 'Your name is')]").nth(0).innerText();
		System.out.println(innerText);
		
		// Alert Handling
		page.onDialog(dialog -> {
			// Simple Alert
//			System.out.println(dialog.message());
//			dialog.accept();

			// Confirm Alert
//			System.out.println(dialog.message());
//			dialog.dismiss();

			// Prompt Alert
			System.out.println(dialog.message());
			System.out.println(dialog.defaultValue());
//			dialog.accept("Raj Kumar");
			dialog.dismiss();

		});

		// Simple Alert
//		page.locator("id=simple-alert").click();

		// Confirm Alert
//		page.locator("id=confirm-alert").click();

		// Prompt Alert
		page.locator("id=prompt-alert").click();
		
		// Closing
		playwright.close();

	}

}
