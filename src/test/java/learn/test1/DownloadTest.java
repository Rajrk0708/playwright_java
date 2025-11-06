package learn.test1;

import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Download;

public class DownloadTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/practice/upload-download");
		// Download file
		Download download = page.waitForDownload(() -> {
			page.locator("'Download PDF'").click();
		});
		System.out.println(download.path());
		System.out.println(download.url());
		System.out.println(download.failure());
		System.out.println(download.suggestedFilename());
		download.saveAs(Paths.get("files/" + download.suggestedFilename()));
		// Context need to be closed to save the downloaded file
		context.close();
		playwright.close();

	}

}
