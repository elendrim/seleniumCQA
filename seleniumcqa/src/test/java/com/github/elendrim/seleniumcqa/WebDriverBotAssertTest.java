package com.github.elendrim.seleniumcqa;

import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_CONTAINS;
import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_EMPTY;
import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_EQUALS;
import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_EQUALS_IGNORE_CASE;
import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_NOT_EMPTY;
import static com.github.elendrim.seleniumcqa.AssertText.GET_ACCESSIBLE_NAME;
import static com.github.elendrim.seleniumcqa.AssertText.GET_ARIA_ROLE;
import static com.github.elendrim.seleniumcqa.AssertText.GET_ATTRIBUTE;
import static com.github.elendrim.seleniumcqa.AssertText.GET_CSS_VALUE;
import static com.github.elendrim.seleniumcqa.AssertText.GET_CURRENT_URL;
import static com.github.elendrim.seleniumcqa.AssertText.GET_DOM_ATTRIBUTE;
import static com.github.elendrim.seleniumcqa.AssertText.GET_DOM_PROPERTY;
import static com.github.elendrim.seleniumcqa.AssertText.GET_PAGE_SOURCE;
import static com.github.elendrim.seleniumcqa.AssertText.GET_TAGNAME;
import static com.github.elendrim.seleniumcqa.AssertText.GET_TEXT;
import static com.github.elendrim.seleniumcqa.AssertText.GET_TITLE;
import static com.github.elendrim.seleniumcqa.AssertText.GET_WINDOW_HANDLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverBotAssertTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void assertionTest(WebDriver driver) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

			webDriverBot.should("https://www.selenium.dev/selenium/web/web-form.html", GET_CURRENT_URL, ASSERT_EQUALS);
			webDriverBot.should("<label class=\"form-label w-100\">Example range", GET_PAGE_SOURCE, ASSERT_CONTAINS);
			webDriverBot.should("web form", GET_TITLE, ASSERT_EQUALS_IGNORE_CASE);
			webDriverBot.should(GET_WINDOW_HANDLE, ASSERT_NOT_EMPTY);

			webDriverBot.find(By.id("my-text-id")).should("myvalue", GET_ATTRIBUTE, "myprop", ASSERT_EQUALS)
					.should("24px", GET_CSS_VALUE, "line-height", ASSERT_EQUALS).should("text", GET_DOM_PROPERTY, "type", ASSERT_EQUALS)
					.should("input", GET_TAGNAME, ASSERT_EQUALS).should(GET_TEXT, ASSERT_EMPTY);

			webDriverBot.find(By.tagName("h1")).should("Web form", GET_TEXT, AssertText.ASSERT_EQUALS);

			// Method not allowed on firefox
			if (!(driver instanceof FirefoxDriver)) {
				webDriverBot.find(By.id("my-text-id")).should("Text input", GET_ACCESSIBLE_NAME, ASSERT_EQUALS).should("textbox",
						GET_ARIA_ROLE, ASSERT_EQUALS);
			}

			webDriverBot.find(By.id("my-check-1")).should("true", GET_DOM_ATTRIBUTE, "checked", ASSERT_EQUALS);

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
					() -> webDriverBot.find(By.id("element-not-present")).should(GET_TEXT, ASSERT_NOT_EMPTY),
					"Expected find to throw timeoutException, but it didn't");

			assertEquals(
					"Expected condition failed: waiting for queryAndAssert with by 'By.id: element-not-present' actual 'null' (tried for 10 second(s) with 500 milliseconds interval)",
					thrown.getRawMessage());
		} finally {
			driver.quit();
		}
	}

}
