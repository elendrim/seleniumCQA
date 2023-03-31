package com.github.elendrim.seleniumcqa;

import static com.github.elendrim.seleniumcqa.AssertText.ASSERT_EQUALS;
import static com.github.elendrim.seleniumcqa.AssertText.GET_TEXT;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebDriverBotAjaxTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void ajaxTest(WebDriver driver) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
			driver.manage().window().maximize();

			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/ajaxy_page.html");
			webDriverBot.find(By.name("typer")).sendKeys("Selenium");
			webDriverBot.find(By.id("green")).click();
			webDriverBot.find(By.name("submit")).click();

			webDriverBot.find(By.id("update_butter")).should("Done!", GET_TEXT, ASSERT_EQUALS);
			webDriverBot.find(By.xpath("//div[@class='label']")).should("Selenium", GET_TEXT, ASSERT_EQUALS);

		} finally {
			driver.close();
		}
	}

}
