package learn.test1;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class DropDownTest {

	public static void main(String[] args) throws InterruptedException {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false).setChannel("chrome"));
		Page page = browser.newPage();
		page.navigate("https://www.qaplayground.com/practice/select");

		// Set locator for dropdown
		Locator heroDD = page.locator("//*[contains(text(), 'Select your super her')]//parent::div/select");

		// Select by Value
		heroDD.selectOption(new SelectOption().setValue("ant-man"));
		// Another way to select value
//		page.selectOption("//*[contains(text(),'Select your super her')]//parent::div/select", "ant-man"); 
		// To print the selected Value
		String selectedText1 = page
				.locator("//*[contains(text(), 'Select your super her')]//parent::div/select >> option:checked")
				.textContent();
		System.out.println("Selected text: " + selectedText1);

		// Select by text
		heroDD.selectOption(new SelectOption().setLabel("Aquaman"));
		// To print the selected Value
		String selectedText2 = page
				.locator("//*[contains(text(), 'Select your super her')]//parent::div/select >> option:checked")
				.textContent();
		System.out.println("Selected text: " + selectedText2);

		// Select by index
		heroDD.selectOption(new SelectOption().setIndex(2));
		// To print the selected Value
		String selectedText3 = page
				.locator("//*[contains(text(), 'Select your super her')]//parent::div/select >> option:checked")
				.textContent();
		System.out.println("Selected text: " + selectedText3);

		// Print all options in select
		Locator options = heroDD.locator("option");
		List<String> allTextContents = options.allTextContents();
		System.out.println(allTextContents);

		// Select by index another way
		int count = options.count();
		heroDD.selectOption(new SelectOption().setIndex(count - 1));
		String selectedText4 = page
				.locator("//*[contains(text(), 'Select your super her')]//parent::div/select >> option:checked")
				.textContent();
		System.out.println("Selected text: " + selectedText4);

		page.reload();
		Thread.sleep(2000);

		// Without Select dropdown
		Locator progLangDD = page.locator(
				"(//*[contains(text(), 'Select your super her')]//parent::div//following-sibling::div)[1]/button");
		progLangDD.click();
		progLangDD.press("Enter");
		String selectedProg = progLangDD.textContent();
		System.out.println("Selected Programming Language: " + selectedProg);

		// Multiple Options
		heroDD.selectOption(new String[] { "ant-man", "aquaman", "avengers", "batman" });
		Locator selectedOptions = page
				.locator("//*[contains(text(), 'Select your super her')]//parent::div/select >> option:checked");
		int count2 = selectedOptions.count();
		for (int i = 0; i < count2; i++) {
			System.out.println("Selected text: " + selectedOptions.nth(i).textContent());
		}

		playwright.close();

	}

}
