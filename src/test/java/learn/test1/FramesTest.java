package learn.test1;

import java.util.List;
import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;

public class FramesTest {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		// Browser maximize script included
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized")));
		BrowserContext context = browser.newContext(new NewContextOptions().setViewportSize(null));
		Page page = context.newPage();
		page.navigate("https://the-internet.herokuapp.com/nested_frames");

		// Frame Locator
		FrameLocator frameLocator = page.frameLocator("frame[name='frame-top']");
		FrameLocator frameLocator1 = frameLocator.frameLocator("//frame[@name='frame-left']");
		String innerText = frameLocator1.locator("//*[contains(text(), 'LEFT')]").innerText();
		System.out.println(innerText);

		// using frameByUrl
		Frame frameByUrl = page.frameByUrl("https://the-internet.herokuapp.com/frame_middle");
		String innerText2 = frameByUrl.locator("//*[contains(text(), 'MIDDLE')]").innerText();
		System.out.println(innerText2);

		// frames
		List<Frame> frames = page.frames();
		System.out.println(frames.size());
		for (Frame frame : frames) {
			System.out.println("Frame URL:" + frame.url());
		}

		//using framebyUrl with index
		Frame frameByUrl2 = page.frameByUrl(frames.get(5).url());
		String innerText3 = frameByUrl2.locator("//*[contains(text(), 'RIGHT')]").innerText();
		System.out.println(innerText3);

		// using Pattern
		Frame frameByUrl3 = page.frameByUrl(Pattern.compile(".*frame_bottom.*"));
		String innerText4 = frameByUrl3.locator("//*[contains(text(), 'BOTTOM')]").innerText();
		System.out.println(innerText4);

		// Closing
		context.close();
		playwright.close();

	}

}
