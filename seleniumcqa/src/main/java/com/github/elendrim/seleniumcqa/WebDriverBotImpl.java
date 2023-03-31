package com.github.elendrim.seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class WebDriverBotImpl implements WebDriverBot {

	private final WebDriver driver;
	private final Wait<WebDriver> actionWait;
	private final Wait<WebDriver> assertionWait;
	private final Configuration configuration;

	public WebDriverBotImpl(WebDriver driver) {
		this(driver, new Configuration());
	}

	public WebDriverBotImpl(WebDriver driver, Configuration configuration) {
		this.driver = driver;
		this.actionWait = WaitBuilder.createForAction(driver, configuration.getActionPollingEveryDuration(),
				configuration.getActionTimeoutDuration());
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
		this.configuration = configuration;
	}

	@Override
	public void visit(String url) {
		driver.get(url);
	}

	@Override
	public NavigateCommand navigate() {
		return new NavigateCommandImpl(driver);
	}

	@Override
	public Command find(By by) {
		return new CommandImpl(driver, by, configuration);
	}

	@Override
	public AlertCommand alert() {
		return new AlertCommandImpl(driver, configuration);
	}

	@Override
	public WebDriverBot should(String expected, Function<WebDriver, String> textOnWebDriver, BiConsumer<String, String> assertion) {
		assertionWait.until(Conditions.assertion(expected, textOnWebDriver, assertion));
		return this;
	}

	@Override
	public WebDriverBot should(Function<WebDriver, String> textOnWebDriver, Consumer<String> assertion) {
		assertionWait.until(Conditions.assertion(textOnWebDriver, assertion));
		return this;
	}

}
