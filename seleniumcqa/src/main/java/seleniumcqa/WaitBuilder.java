package seleniumcqa;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class WaitBuilder {

	public static FluentWait<WebDriver> createForAssertion(WebDriver driver, Duration pollingEveryDuration, Duration timeoutDuration) {
		return new FluentWait<WebDriver>(driver).pollingEvery(pollingEveryDuration).withTimeout(timeoutDuration)
				.ignoreAll(Arrays.asList(NoSuchElementException.class, AssertionError.class));
	}

	public static FluentWait<WebDriver> createForCommand(WebDriver driver, Duration pollingEveryDuration, Duration timeoutDuration) {
		return new FluentWait<WebDriver>(driver).pollingEvery(pollingEveryDuration).withTimeout(timeoutDuration)
				.ignoreAll(Arrays.asList(NoSuchElementException.class));
	}

}
