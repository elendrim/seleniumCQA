package com.github.elendrim.seleniumcqa;

import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface ExecuteWithWebDriver<T> {

	T execute(WebDriver driver);

}
