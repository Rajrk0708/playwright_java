package learn.test3;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.setDefaultAssertionTimeout;

import java.net.URLEncoder;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestngDemoTest {

	Playwright playwright;
	Browser browser;
	Page page;

	@BeforeMethod
	@Parameters("browserName")
	public void setup(@Optional("chromium") String browserName) throws Exception {
		try {
			playwright = Playwright.create();
			BrowserType browserType = switch (browserName.toLowerCase()) {
			case "firefox" -> playwright.firefox();
			case "webkit" -> playwright.webkit();
			default -> playwright.chromium(); // Default: Chrome
			};

			JsonObject capabilities = new JsonObject();
			JsonObject ltOptions = new JsonObject();

			String user = "rajkumar0708rk";
			String accessKey = "LT_egvYzaQuq46HUHDjikqzQEK2oOAglEkZvgqi2bErlrcNbrK";

			capabilities.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`,
																// `pw-chromium`, `pw-firefox` and `pw-webkit`
			capabilities.addProperty("browserVersion", "latest");
			ltOptions.addProperty("platform", "Windows 11");
			ltOptions.addProperty("name", "Playwright Test");
			ltOptions.addProperty("build", "Playwright Testing in TestNG");
			ltOptions.addProperty("user", user);
			ltOptions.addProperty("accessKey", accessKey);
			ltOptions.addProperty("visual", true);
			ltOptions.addProperty("console", true);
			ltOptions.addProperty("network", true);
			capabilities.add("LT:Options", ltOptions);

			String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
			String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + capabilities;
			browser = browserType.connect(cdpUrl);
			page = browser.newPage();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void sampleTest() {
		try {

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

		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		page.close();
		browser.close();
		playwright.close();
	}

}
