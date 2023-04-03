package com.github.elendrim.seleniumcqa;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;
import com.github.elendrim.seleniumcqa.assertion.GetFromAlert;

public class AlertCommandImpl implements AlertCommand {

	private FluentWait<WebDriver> actionWait;
	private FluentWait<WebDriver> assertionWait;

	public AlertCommandImpl(WebDriver driver, Configuration configuration) {
		this.actionWait = WaitBuilder.createForAction(driver, configuration.getActionPollingEveryDuration(),
				configuration.getActionTimeoutDuration());
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
	}

	@Override
	public AlertCommand should(String expected, GetFromAlert getFromAlert, ExpectedAssertion assertion) {
		assertionWait.until(Conditions.alertAndAssert(expected, getFromAlert, assertion));
		return this;
	}

	@Override
	public AlertCommand should(GetFromAlert getFromAlert, Assertion assertion) {
		assertionWait.until(Conditions.alertAndAssert(getFromAlert, assertion));
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
