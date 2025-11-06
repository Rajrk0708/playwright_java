package learn.test1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class UploadTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://the-internet.herokuapp.com/upload");
		// Upload Single file
		page.locator("(//input[@type='file'])[1]").setInputFiles(Paths.get("files/test.pdf"));
		// Upload Multiple files
		page.locator("(//input[@type='file'])[2]")
				.setInputFiles(new Path[] { Paths.get("files/test.pdf"), Paths.get("files/test.txt") });
		page.locator("input[id='file-submit']").click();
		String innerText = page.locator("//*[@id='content']/div/h3").innerText();
		System.out.println(innerText);
		context.clock();
		playwright.close();

	}

}
