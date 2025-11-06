package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class RadioCheckboxTest {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/practice/radio");
		// Radio Button
		Locator radioButton = page.locator("//*[text()='Select any one']//parent::div//span[text()='Yes']");
		System.out.println("Radio Button -> Default:"+ radioButton.isChecked());
		radioButton.check();
		System.out.println("Radio Button -> Post Selected: " + radioButton.isChecked());
		// Checkbox
		Locator checkbox = page.locator("//*[contains(text(),'checkbox is selected')]//parent::div//span[text()='Remember me']");
		System.out.println("Checkbox -> Default: " + checkbox.isChecked());
		checkbox.uncheck();
		System.out.println("Checkbox -> Post Unchecked: " + checkbox.isChecked());
		checkbox.check();
		System.out.println("Checkbox -> Post Checked: " + checkbox.isChecked());
		browser.close();
		playwright.close();

	}

}
