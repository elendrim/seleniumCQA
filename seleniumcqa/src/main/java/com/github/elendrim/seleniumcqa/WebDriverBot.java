package com.github.elendrim.seleniumcqa;

import org.openqa.selenium.By;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebDriver;

public interface WebDriverBot {

	void visit(String url);

	NavigateCommand navigate();

	Command find(By by);

	AlertCommand alert();

	WebDriverBot should(String expected, GetFromWebDriver getFromWebDriver, ExpectedAssertion assertion);

	WebDriverBot should(GetFromWebDriver getFromWebDriver, Assertion assertion);

	<T> T doWithWebDriver(ExecuteWithWebDriver<T> executeWithWebDriver);

}
