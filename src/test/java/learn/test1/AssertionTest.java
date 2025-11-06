package learn.test1;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.setDefaultAssertionTimeout;

import java.util.List;

public class AssertionTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/");
		setDefaultAssertionTimeout(10000);
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
		playwright.close();

	}

}
