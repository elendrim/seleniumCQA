package com.github.elendrim.seleniumcqa;

import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;

public class WebDriverBotExecuteOnWebDriverTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void executeOnWebDriverTest(final WebDriver driver) {

		try {

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.doWithWebDriver((final WebDriver webDriver) -> {
				webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
				webDriver.manage().window().maximize();
				return null;
			});
			webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

			Set<String> windowsHandles = webDriverBot.doWithWebDriver((final WebDriver webDriver) -> {
				return webDriver.getWindowHandles();
			});

			System.out.println(windowsHandles);

		} finally {
			driver.close();
		}
	}

}
