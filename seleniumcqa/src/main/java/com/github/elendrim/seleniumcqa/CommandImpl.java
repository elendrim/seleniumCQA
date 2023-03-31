package com.github.elendrim.seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class CommandImpl implements Command {

	private FluentWait<WebDriver> actionWait;
	private FluentWait<WebDriver> assertionWait;
	private By by;
	private Configuration configuration;

	public CommandImpl(WebDriver driver, By by, Configuration configuration) {
		this.actionWait = WaitBuilder.createForAction(driver, configuration.getActionPollingEveryDuration(),
				configuration.getActionTimeoutDuration());
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
		this.by = by;
		this.configuration = configuration;

	}

	@Override
	public Command sendKeys(CharSequence... keysToSend) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		element.sendKeys(keysToSend);
		return this;
	}

	@Override
	public Command click() {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		element.click();
		return this;
	}

	@Override
	public Command should(String expected, Function<WebElement, String> textOnElement, BiConsumer<String, String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(expected, by, textOnElement, assertion, configuration));
		return this;
	}

	@Override
	public Command should(Function<WebElement, String> textOnElement, Consumer<String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(by, textOnElement, assertion, configuration));
		return this;
	}

	@Override
	public Command should(String expected, BiFunction<WebElement, String, String> textOnElement, String parameter,
			BiConsumer<String, String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(expected, by, textOnElement, parameter, assertion, configuration));
		return this;
	}

	@Override
	public Command should(BiFunction<WebElement, String, String> textOnElement, String parameter, Consumer<String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(by, textOnElement, parameter, assertion, configuration));
		return this;
	}

}
