package com.github.elendrim.seleniumcqa.assertion;

import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface GetFromWebElementWithParameter {

	String get(WebElement element, String parameter);

}
