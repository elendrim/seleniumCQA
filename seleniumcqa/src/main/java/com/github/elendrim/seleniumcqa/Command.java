package com.github.elendrim.seleniumcqa;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElement;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElementWithParameter;

public interface Command {

	Command sendKeys(CharSequence... keysToSend);

	Command clear();

	Command click();

	Command selectByVisibleText(String text);

	Command deselectByVisibleText(String text);

	Command selectByValue(String value);

	Command deselectByValue(String value);

	Command selectByIndex(int index);

	Command deselectByIndex(int index);

	void deselectAll();

	Command should(String expected, GetFromWebElement getFromWebElement, ExpectedAssertion assertion);

	Command should(GetFromWebElement getFromWebElement, Assertion assertion);

	Command should(String expected, GetFromWebElementWithParameter getFromWebElementWithParameter, String parameter,
			ExpectedAssertion assertion);

	Command should(GetFromWebElementWithParameter getFromWebElementWithParameter, String parameter, Assertion assertion);

}
