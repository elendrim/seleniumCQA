package com.github.elendrim.seleniumcqa;

import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_EQUALS;
import static com.github.elendrim.seleniumcqa.AssertText.GET_TEXT;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebDriverBotOverflowTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void overflowTest(WebDriver driver) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/overflow/x_scroll_y_scroll.html");
			webDriverBot.find(By.id("right-clicked")).should("", GET_TEXT, ASSERT_EQUALS);
			webDriverBot.find(By.id("bottom-clicked")).should("", GET_TEXT, ASSERT_EQUALS);
			webDriverBot.find(By.id("bottom-right-clicked")).should("", GET_TEXT, ASSERT_EQUALS);

			webDriverBot.find(By.id("right")).click();
			webDriverBot.find(By.id("right-clicked")).should("ok", GET_TEXT, ASSERT_EQUALS);

			webDriverBot.find(By.id("bottom")).click();
			webDriverBot.find(By.id("bottom-clicked")).should("ok", GET_TEXT, ASSERT_EQUALS);

			webDriverBot.find(By.id("bottom-right")).click();
			webDriverBot.find(By.id("bottom-right-clicked")).should("ok", GET_TEXT, ASSERT_EQUALS);

		} finally {
			driver.close();
		}
	}

}
