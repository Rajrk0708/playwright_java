package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.ClickOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.MouseButton;

public class ClickActionsTest {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/practice/button");
		
		// Double Click
		page.locator("'Double Click Me'").dblclick();
		System.out.println(page.locator("(//p)[9]").innerText());
		
		// Right Click
		page.locator("'Right Click Me'").click(new ClickOptions().setButton(MouseButton.RIGHT));
		System.out.println(page.locator("(//p)[9]").innerText());
		
		// New Tab
		Page newPage = context.newPage();
		newPage.navigate ("https://the-internet.herokuapp.com/drag_and_drop");
		
		// Drag and Drop
		Locator source = newPage.locator("id=column-a");
		Locator target = newPage.locator("id=column-b");
		source.dragTo(target);
		System.out.println("Dragged element successfully!");
		
		// Closing
		newPage.close();
		browser.close();
		playwright.close();


	}

}
