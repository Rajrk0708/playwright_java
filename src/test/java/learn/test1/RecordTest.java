package learn.test1;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class RecordTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script and record test
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null)
				.setRecordVideoDir(Paths.get("videos/")).setRecordVideoSize(1280, 720));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/");
		assertThat(page).hasTitle("QA Playground: Practice Automation Testing with Selenium");
		page.locator("'Get Started'").click();
		assertThat(page.locator("h3")).hasText("Practice Daily Automation!");
		assertThat(page).not().hasURL("https://www.qaplayground.com");
		page.locator("'Edit'").click();
		assertThat(page).hasURL("https://www.qaplayground.com/practice/input");
		assertThat(page.locator("id=movieName")).hasAttribute("placeholder", "Enter hollywood movie name");
		assertThat(page.locator("//*[@class='pb-20']/div/h2")).hasText("Input");
		assertThat(page.locator("label").nth(1)).containsText("Append a text");
		System.out.println("Assertions Worked as Expected");
		context.close();
		playwright.close();

	}

}
