package com.github.elendrim.seleniumcqa;

import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_EQUALS;
import static com.github.elendrim.seleniumcqa.AssertText.GET_ATTRIBUTE;
import static com.github.elendrim.seleniumcqa.AssertText.GET_TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class WebDriverBotFindTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void assertionTest(WebDriver driver) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");
			webDriverBot.find(By.id("my-text-id")).sendKeys("Selenium");

			webDriverBot.find(By.id("my-text-id")).should("Selenium", GET_ATTRIBUTE, "value", ASSERT_EQUALS);
			webDriverBot.find(By.tagName("h1")).should("Web form", GET_TEXT, ASSERT_EQUALS);
			webDriverBot.find(By.cssSelector("#my-text-id")).should("Selenium", GET_ATTRIBUTE, "value", ASSERT_EQUALS);
			webDriverBot.find(By.name("my-text")).should("Selenium", GET_ATTRIBUTE, "value", ASSERT_EQUALS);
			webDriverBot.find(By.xpath("//input[@id='my-text-id']")).should("Selenium", GET_ATTRIBUTE, "value", ASSERT_EQUALS);
			webDriverBot.find(By.linkText("Return to index")).should("https://www.selenium.dev/selenium/web/index.html", GET_ATTRIBUTE,
					"href", ASSERT_EQUALS);
			webDriverBot.find(new ByChained(By.tagName("form"), By.name("my-select"), By.xpath("//option[@value='3']"))).should("Three",
					GET_TEXT, ASSERT_EQUALS);

		} finally {
			driver.close();
		}
	}

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void timeoutExceptionOnElementNotFound(WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

			TimeoutException thrown = assertThrows(TimeoutException.class,
					() -> webDriverBot.find(By.tagName("h1")).should("text not in h1", GET_TEXT, ASSERT_EQUALS),
					"Expected find to throw timeoutException, but it didn't");

			assertEquals(
					"Expected condition failed: waiting for queryAndAssert with expected 'text not in h1' by 'By.tagName: h1' actual 'Web form' (tried for 10 second(s) with 500 milliseconds interval)",
					thrown.getRawMessage());
		} finally {
			driver.quit();
		}
	}

}
