package com.github.elendrim.seleniumcqa;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;
import com.github.elendrim.seleniumcqa.assertion.GetFromAlert;

public interface AlertCommand {

	AlertCommand sendKeys(String keysToSend);

	AlertCommand dismiss();

	AlertCommand accept();

	AlertCommand should(GetFromAlert getFromAlert, Assertion assertion);

	AlertCommand should(String expected, GetFromAlert getFromAlert, ExpectedAssertion assertion);
}
