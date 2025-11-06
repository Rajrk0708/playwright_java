package learn.test1;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CodegenTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.navigate("https://www.qaplayground.com/");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
		assertThat(page.locator("h3")).containsText("Practice Daily Automation!");
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit")).click();
		assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Input"))).isVisible();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter hollywood movie name")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter hollywood movie name"))
				.fill("God of War");
		assertThat(page.getByRole(AriaRole.MAIN)).containsText("Append a text and press keyboard tab");
		assertThat(page.getByText("Append a text and press")).isVisible();
		assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter hollywood movie name")))
				.hasValue("God of War");
		
		System.out.println("Codegen Script Executed Successfully");
		playwright.close();
	}

}
