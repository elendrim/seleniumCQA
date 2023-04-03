package com.github.elendrim.seleniumcqa;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;

public class WebDriverBotSelectTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void interactionTest(final WebDriver driver) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https: // www.selenium.dev/selenium/web/selectPage.html");

			// TODO GOR A TESTER les select multiple:

			// TODO GOR TESTER les erreurs lors du select
			webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

			// TODO GOR ajouter un test doit retourner une erreur car le champs est disabled
			// webDriverBot.find(By.name("my-disabled")).sendKeys("Selenium dissabled
			// input");
			// webDriverBot.find(By.name("my-disabled")).should("Selenium dissabled input",
			// GET_ATTRIBUTE, "value", ASSERT_EQUALS);

			// TODO GOR ajouter un test doit retourner une erreur car le champs est disabled
			// webDriverBot.find(By.name("my-readonly")).sendKeys("Selenium readonly
			// input");
			// webDriverBot.find(By.name("my-readonly")).should("Selenium readonly input",
			// GET_ATTRIBUTE, "value", ASSERT_EQUALS);

		} finally {
			driver.close();
		}
	}

}
