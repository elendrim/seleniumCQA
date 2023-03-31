package com.github.elendrim.seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Conditions {

	public static ExpectedCondition<WebElement> presenceOfElementLocated(final By by) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}

			@Override
			public String toString() {
				return "presence of element located by: '" + by + "'";
			}
		};
	}

	public static ExpectedCondition<Boolean> isObscured(final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String isObscured = "var rec = arguments[0].getBoundingClientRect(); "
						+ "var elementFromPoint = document.elementFromPoint(rec.left+rec.width/2, rec.top+rec.height/2); "
						+ "if ( elementFromPoint == null ) {return false;} "
						+ "return elementFromPoint.parentNode !== arguments[0].parentNode;";

				JavascriptExecutor js = (JavascriptExecutor) driver;
				Object res = js.executeScript(isObscured, element);
				if (res == null) {
					return true;
				}

				return (Boolean) res;
			}

			@Override
			public String toString() {
				return "isObscured of " + element;
			}
		};
	}

	public static ExpectedCondition<WebElement> queryForAction(final By by, Configuration configuration) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return null;
				}

				boolean isDisplayed = element.isDisplayed();
				if (!isDisplayed) {
					return null;
				}

				boolean isEnabled = element.isEnabled();
				if (!isEnabled) {
					return null;
				}

				boolean isObscured = isObscured(element).apply(driver);
				if (isObscured) {
					return null;
				}

				// new Scroller(driver).scrollIntoView(element);
				new Highlighter(driver, configuration).highlight(element);

				return element;
			}

			@Override
			public String toString() {
				return "queryForAction by '" + by + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> queryAndAssert(final String expected, final By by, Function<WebElement, String> textOnElement,
			final BiConsumer<String, String> assertion, final Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = textOnElement.apply(element);
				assertion.accept(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with expected '" + expected + "' by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> queryAndAssert(final By by, Function<WebElement, String> textOnElement,
			final Consumer<String> assertion, Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = textOnElement.apply(element);
				assertion.accept(actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> queryAndAssert(String expected, By by, BiFunction<WebElement, String, String> textOnElement,
			String parameter, BiConsumer<String, String> assertion, Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = textOnElement.apply(element, parameter);
				assertion.accept(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> queryAndAssert(By by, BiFunction<WebElement, String, String> textOnElement, String parameter,
			Consumer<String> assertion, Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = textOnElement.apply(element, parameter);
				assertion.accept(actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> assertion(final String expected, Function<WebDriver, String> textOnWebDriver,
			final BiConsumer<String, String> assertion) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {
				this.actual = textOnWebDriver.apply(driver);
				assertion.accept(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with expected '" + expected + "' actual '" + actual + "'";
			}
		};
	}

	public static ExpectedCondition<Boolean> assertion(final Function<WebDriver, String> textOnWebDriver,
			final Consumer<String> assertion) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {
				this.actual = textOnWebDriver.apply(driver);
				assertion.accept(actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with actual '" + actual + "'";
			}
		};
	}

	public static ExpectedCondition<Boolean> alertAndAssert(final String expected, Function<Alert, String> textOnAlert,
			final BiConsumer<String, String> assertion) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {
				Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
				if (alert == null) {
					return false;
				}
				this.actual = textOnAlert.apply(alert);
				assertion.accept(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "alertAndAssert with expected '" + expected + "'" + " actual '" + actual + "'";
			}
		};
	}

	public static ExpectedCondition<Boolean> alertAndAssert(Function<Alert, String> textOnAlert, final Consumer<String> assertion) {
		return new ExpectedCondition<Boolean>() {
			private String actual;

			@Override
			public Boolean apply(WebDriver driver) {
				Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
				if (alert == null) {
					return false;
				}
				this.actual = textOnAlert.apply(alert);
				assertion.accept(actual);
				return true;
			}

			@Override
			public String toString() {
				return "alertAndAssert with actual '" + actual + "'";
			}
		};
	}

	public static ExpectedCondition<Alert> alertForAction() {
		return new ExpectedCondition<Alert>() {

			@Override
			public Alert apply(WebDriver driver) {
				return ExpectedConditions.alertIsPresent().apply(driver);
			}

			@Override
			public String toString() {
				return "alertForAction";
			}
		};
	}

}
