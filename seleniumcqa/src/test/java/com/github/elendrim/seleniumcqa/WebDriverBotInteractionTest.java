package com.github.elendrim.seleniumcqa;

import static com.github.elendrim.seleniumcqa.AssertFunctions.ASSERT_EQUALS;
import static com.github.elendrim.seleniumcqa.GetFunctions.GET_ATTRIBUTE;
import static com.github.elendrim.seleniumcqa.GetFunctions.GET_TEXT;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebDriverBotInteractionTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void interactionTest(final WebDriver driver) {

		// TODO GOR : tester tous les types
		// https://docs.cypress.io/guides/core-concepts/interacting-with-elements
//		
// V	.click()
//		.dblclick()
//		.rightclick()
// V  	.type()
// V 	.clear()
//		.check()
//		.uncheck()
//		.select()
//		.trigger()
//		.selectFile()

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

			webDriverBot.find(By.id("my-text-id")).sendKeys("Selenium");
			webDriverBot.find(By.id("my-text-id")).should("Selenium", GET_ATTRIBUTE, "value", ASSERT_EQUALS);

			webDriverBot.find(By.id("my-text-id")).clear();
			webDriverBot.find(By.id("my-text-id")).should("", GET_ATTRIBUTE, "value", ASSERT_EQUALS);

			webDriverBot.find(By.name("my-password")).sendKeys("Selenium password");
			webDriverBot.find(By.name("my-password")).should("Selenium password", GET_ATTRIBUTE, "value", ASSERT_EQUALS);

			webDriverBot.find(By.name("my-textarea")).sendKeys("Selenium textarea");
			webDriverBot.find(By.name("my-textarea")).should("Selenium textarea", GET_ATTRIBUTE, "value", ASSERT_EQUALS);

			webDriverBot.find(By.linkText("Return to index")).click();
			webDriverBot.navigate().back();

			webDriverBot.find(By.name("my-select")).selectByValue("1").should("One", GET_TEXT, ASSERT_EQUALS);
			webDriverBot.find(By.name("my-select")).selectByVisibleText("Two").should("2", GET_ATTRIBUTE, "value", ASSERT_EQUALS);

			webDriverBot.find(By.name("my-datalist")).sendKeys("San Francisco");
			webDriverBot.find(By.name("my-datalist")).should("San Francisco", GET_ATTRIBUTE, "value", ASSERT_EQUALS);

			webDriverBot.find(By.name("my-datalist")).sendKeys("San Francisco");

			webDriverBot.find(By.cssSelector("button")).click();
			webDriverBot.find(By.id("message")).should("Received!", GET_TEXT, ASSERT_EQUALS);

		} finally {
			driver.close();
		}
	}

}
