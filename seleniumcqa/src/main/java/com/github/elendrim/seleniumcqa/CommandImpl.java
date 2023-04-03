package com.github.elendrim.seleniumcqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElement;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElementWithParameter;

public class CommandImpl implements Command {

	private final WebDriver driver;
	private final FluentWait<WebDriver> actionWait;
	private final FluentWait<WebDriver> assertionWait;
	private final By by;
	private final Configuration configuration;

	public CommandImpl(final WebDriver driver, final By by, final Configuration configuration) {
		this.driver = driver;
		this.actionWait = WaitBuilder.createForAction(driver, configuration.getActionPollingEveryDuration(),
				configuration.getActionTimeoutDuration());
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
		this.by = by;
		this.configuration = configuration;

	}

	@Override
	public Command sendKeys(final CharSequence... keysToSend) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		element.sendKeys(keysToSend);
		return this;
	}

	@Override
	public Command clear() {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		element.clear();
		return this;
	}

	@Override
	public Command click() {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		element.click();
		return this;
	}

	@Override
	public Command selectByVisibleText(final String text) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		Select select = new Select(element);
		select.selectByVisibleText(text);
		By optionSelected = new ByChained(by, By.cssSelector("option:checked"));
		return new CommandImpl(driver, optionSelected, configuration);
	}

	@Override
	public Command deselectByVisibleText(final String text) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		Select select = new Select(element);
		select.selectByVisibleText(text);
		By optionSelected = new ByChained(by, By.cssSelector("option:checked"));
		return new CommandImpl(driver, optionSelected, configuration);
	}

	@Override
	public Command selectByValue(final String value) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		Select select = new Select(element);
		select.selectByValue(value);
		By optionSelected = new ByChained(by, By.cssSelector("option:checked"));
		return new CommandImpl(driver, optionSelected, configuration);
	}

	@Override
	public Command deselectByValue(final String value) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		Select select = new Select(element);
		select.deselectByValue(value);
		By optionSelected = new ByChained(by, By.cssSelector("option:checked"));
		return new CommandImpl(driver, optionSelected, configuration);
	}

	@Override
	public Command selectByIndex(final int index) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		Select select = new Select(element);
		select.selectByIndex(index);
		By optionSelected = new ByChained(by, By.cssSelector("option:checked"));
		return new CommandImpl(driver, optionSelected, configuration);
	}

	@Override
	public Command deselectByIndex(final int index) {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		Select select = new Select(element);
		select.deselectByIndex(index);
		By optionSelected = new ByChained(by, By.cssSelector("option:checked"));
		return new CommandImpl(driver, optionSelected, configuration);
	}

	@Override
	public void deselectAll() {
		WebElement element = actionWait.until(Conditions.queryForAction(by, configuration));
		Select select = new Select(element);
		select.deselectAll();
	}

	@Override
	public Command should(final String expected, final GetFromWebElement getFromWebElement, final ExpectedAssertion assertion) {
		assertionWait.until(Conditions.queryAndAssert(expected, by, getFromWebElement, assertion, configuration));
		return this;
	}

	@Override
	public Command should(final GetFromWebElement getFromWebElement, final Assertion assertion) {
		assertionWait.until(Conditions.queryAndAssert(by, getFromWebElement, assertion, configuration));
		return this;
	}

	@Override
	public Command should(final String expected, final GetFromWebElementWithParameter getFromWebElementWithParameter,
			final String parameter, final ExpectedAssertion assertion) {
		assertionWait.until(Conditions.queryAndAssert(expected, by, getFromWebElementWithParameter, parameter, assertion, configuration));
		return this;
	}

	@Override
	public Command should(final GetFromWebElementWithParameter getFromWebElementWithParameter, final String parameter,
			final Assertion assertion) {
		assertionWait.until(Conditions.queryAndAssert(by, getFromWebElementWithParameter, parameter, assertion, configuration));
		return this;
	}

}
