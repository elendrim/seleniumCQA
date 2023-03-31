package com.github.elendrim.seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class AlertCommandImpl implements AlertCommand {

	private FluentWait<WebDriver> actionWait;
	private FluentWait<WebDriver> assertionWait;
	private Configuration configuration;

	public AlertCommandImpl(WebDriver driver, Configuration configuration) {
		this.actionWait = WaitBuilder.createForAction(driver, configuration.getActionPollingEveryDuration(),
				configuration.getActionTimeoutDuration());
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
		this.configuration = configuration;
	}

	@Override
	public AlertCommand should(String expected, Function<Alert, String> textOnAlert, BiConsumer<String, String> assertion) {
		assertionWait.until(Conditions.alertAndAssert(expected, textOnAlert, assertion));
		return this;
	}

	@Override
	public AlertCommand should(Function<Alert, String> textOnAlert, Consumer<String> assertion) {
		assertionWait.until(Conditions.alertAndAssert(textOnAlert, assertion));
		return this;
	}

	@Override
	public AlertCommand accept() {
		Alert alert = actionWait.until(Conditions.alertForAction());
		alert.accept();
		return this;
	}

	@Override
	public AlertCommand dismiss() {
		Alert alert = actionWait.until(Conditions.alertForAction());
		alert.dismiss();
		return this;
	}

	@Override
	public AlertCommand sendKeys(String keysToSend) {
		Alert alert = actionWait.until(Conditions.alertForAction());
		alert.sendKeys(keysToSend);
		return this;
	}

}
