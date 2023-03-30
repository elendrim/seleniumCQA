package seleniumcqa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;

public class Highlighter {

	private final WebDriver driver;
	private Configuration configuration;
	private final Sleeper sleeper;

	public Highlighter(WebDriver driver, Configuration configuration) {
		this(driver, configuration, Sleeper.SYSTEM_SLEEPER);
	}

	public Highlighter(WebDriver driver, Configuration configuration, Sleeper sleeper) {
		this.driver = driver;
		this.configuration = configuration;
		this.sleeper = sleeper;
	}

	public void highlight(WebElement element) {
		if (configuration.isHighlightEnabled()) {
			String currentStyle = element.getAttribute("style");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					currentStyle + " color: yellow; border: 5px solid yellow;");

			try {
				sleeper.sleep(configuration.getHighlightSleepDuration());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new WebDriverException(e);
			}

			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, currentStyle);
		}
	}

}
