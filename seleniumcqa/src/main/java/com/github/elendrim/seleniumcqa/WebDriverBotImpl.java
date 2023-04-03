package com.github.elendrim.seleniumcqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebDriver;

public class WebDriverBotImpl implements WebDriverBot {

	private final WebDriver driver;
	private final Wait<WebDriver> assertionWait;
	private final Configuration configuration;

	public WebDriverBotImpl(final WebDriver driver) {
		this(driver, new Configuration());
	}

	public WebDriverBotImpl(final WebDriver driver, final Configuration configuration) {
		this.driver = driver;
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
		this.configuration = configuration;
	}

	@Override
	public void visit(final String url) {
		driver.get(url);
	}

	@Override
	public NavigateCommand navigate() {
		return new NavigateCommandImpl(driver);
	}

	@Override
	public Command find(final By by) {
		return new CommandImpl(driver, by, configuration);
	}

	@Override
	public AlertCommand alert() {
		return new AlertCommandImpl(driver, configuration);
	}

	@Override
	public WebDriverBot should(final String expected, final GetFromWebDriver getFromWebDriver, final ExpectedAssertion assertion) {
		assertionWait.until(Conditions.assertion(expected, getFromWebDriver, assertion));
		return this;
	}

	@Override
	public WebDriverBot should(final GetFromWebDriver getFromWebDriver, final Assertion assertion) {
		assertionWait.until(Conditions.assertion(getFromWebDriver, assertion));
		return this;
	}

	@Override
	public <T> T doWithWebDriver(final ExecuteWithWebDriver<T> executeWithWebDriver) {
		return executeWithWebDriver.execute(driver);
	}
}
