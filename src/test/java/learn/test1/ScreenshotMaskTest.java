package learn.test1;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.PdfOptions;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Playwright;

public class ScreenshotMaskTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://playwright.dev/java/");

		// Generate PDF
		page.pdf(new PdfOptions().setPath(Paths.get("files/page.pdf")));

		// Screenshot
		page.screenshot(new ScreenshotOptions().setPath(Paths.get("./snaps/img.png")));
		page.screenshot(new ScreenshotOptions().setPath(Paths.get("./snaps/full.jpeg")).setFullPage(true));

		// Screenshot Locator
		Locator locator = page.locator("//*[text()='Search']");
		locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/locator.png")));

		// Set Mask
		page.screenshot(
				new ScreenshotOptions().setPath(Paths.get("./snaps/locatorMask.jpeg")).setMask(Arrays.asList(locator)));
		
		System.out.println("PDF Generated");
		context.close();
		playwright.close();

	}

}
