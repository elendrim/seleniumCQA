package com.github.elendrim.seleniumcqa.assertion;

import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface GetFromWebElement {

	String get(WebElement element);

}
