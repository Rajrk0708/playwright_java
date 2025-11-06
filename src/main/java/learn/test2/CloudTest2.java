package learn.test2;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.setDefaultAssertionTimeout;

import java.net.URLEncoder;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CloudTest2 {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			JsonObject capabilities = new JsonObject();
			JsonObject ltOptions = new JsonObject();

			String user = "rajkumar0708rk";
			String accessKey = "LT_egvYzaQuq46HUHDjikqzQEK2oOAglEkZvgqi2bErlrcNbrK";

			capabilities.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`,
																// `pw-chromium`, `pw-firefox` and `pw-webkit`
			capabilities.addProperty("browserVersion", "latest");
			ltOptions.addProperty("platform", "Windows 11");
			ltOptions.addProperty("name", "Playwright Test");
			ltOptions.addProperty("build", "Playwright Testing in Java");
			ltOptions.addProperty("user", user);
			ltOptions.addProperty("accessKey", accessKey);
			ltOptions.addProperty("visual", true);
			ltOptions.addProperty("console", true);
			ltOptions.addProperty("network", true);
			capabilities.add("LT:Options", ltOptions);
			

			BrowserType chromium = playwright.chromium();
			String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
			String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + capabilities;
			Browser browser = chromium.connect(cdpUrl);
			Page page = browser.newPage();
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
			browser.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

}
