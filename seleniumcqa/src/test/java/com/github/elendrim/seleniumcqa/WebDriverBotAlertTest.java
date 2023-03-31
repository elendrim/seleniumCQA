package com.github.elendrim.seleniumcqa;

import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_EQUALS;
import static com.github.elendrim.seleniumcqa.AssertText.GET_ALERT_TEXT;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebDriverBotAlertTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void alertTest(WebDriver driver) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/alerts.html");

			webDriverBot.find(By.id("alert")).click();
			webDriverBot.alert().should("cheese", GET_ALERT_TEXT, ASSERT_EQUALS);
			webDriverBot.alert().accept();

			webDriverBot.find(By.id("empty-alert")).click();
			webDriverBot.alert().accept();

			webDriverBot.find(By.id("prompt")).click();
			webDriverBot.alert().sendKeys("Selenium");
			webDriverBot.alert().accept();

			webDriverBot.find(By.id("prompt-with-default")).click();
			webDriverBot.alert().sendKeys("Selenium");
			webDriverBot.alert().dismiss();

			webDriverBot.find(By.id("double-prompt")).click();
			webDriverBot.alert().should("First", GET_ALERT_TEXT, ASSERT_EQUALS);
			webDriverBot.alert().accept();
			webDriverBot.alert().should("Second", GET_ALERT_TEXT, ASSERT_EQUALS);
			webDriverBot.alert().accept();

			webDriverBot.find(By.id("slow-alert")).click();
			webDriverBot.alert().should("Slow", GET_ALERT_TEXT, ASSERT_EQUALS);
			webDriverBot.alert().accept();

		} finally {
			driver.close();
		}
	}

}
