package com.github.elendrim.seleniumcqa;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.elendrim.seleniumcqa.assertion.GetFromAlert;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebDriver;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElement;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElementWithParameter;

public class GetFunctions {

	public static GetFromWebElement GET_ACCESSIBLE_NAME = (final WebElement element) -> element.getAccessibleName();
	public static GetFromWebElement GET_ARIA_ROLE = (final WebElement element) -> element.getAriaRole();
	public static GetFromWebElementWithParameter GET_ATTRIBUTE = (final WebElement element, final String name) -> element
			.getAttribute(name);
	public static GetFromWebElementWithParameter GET_CSS_VALUE = (final WebElement element, final String propertyName) -> element
			.getCssValue(propertyName);
	public static GetFromWebElementWithParameter GET_DOM_ATTRIBUTE = (final WebElement element, final String name) -> element
			.getDomAttribute(name);
	public static GetFromWebElementWithParameter GET_DOM_PROPERTY = (final WebElement element, final String name) -> element
			.getDomProperty(name);
	public static GetFromWebElement GET_TAGNAME = (final WebElement element) -> element.getTagName();
	public static GetFromWebElement GET_TEXT = (final WebElement element) -> element.getText();

	public static GetFromWebDriver GET_CURRENT_URL = (final WebDriver driver) -> driver.getCurrentUrl();
	public static GetFromWebDriver GET_PAGE_SOURCE = (final WebDriver driver) -> driver.getPageSource();
	public static GetFromWebDriver GET_TITLE = (final WebDriver driver) -> driver.getTitle();
	public static GetFromWebDriver GET_WINDOW_HANDLE = (final WebDriver driver) -> driver.getWindowHandle();

	public static GetFromAlert GET_ALERT_TEXT = (final Alert alert) -> alert.getText();

}
