package com.github.elendrim.seleniumcqa.assertion;

import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface GetFromWebDriver {

	String get(WebDriver driver);

}
