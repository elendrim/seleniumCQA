package com.github.elendrim.seleniumcqa;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;
import com.github.elendrim.seleniumcqa.assertion.GetFromAlert;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebDriver;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElement;
import com.github.elendrim.seleniumcqa.assertion.GetFromWebElementWithParameter;

public class Conditions {

	public static ExpectedCondition<WebElement> presenceOfElementLocated(final By by) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(final WebDriver driver) {
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
			public Boolean apply(final WebDriver driver) {
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

	public static ExpectedCondition<WebElement> queryForAction(final By by, final Configuration configuration) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(final WebDriver driver) {

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

	public static ExpectedCondition<Boolean> queryAndAssert(final String expected, final By by, final GetFromWebElement getFromElement,
			final ExpectedAssertion assertion, final Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = getFromElement.get(element);
				assertion.assertThat(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with expected '" + expected + "' by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> queryAndAssert(final By by, final GetFromWebElement getFromElement, final Assertion assertion,
			final Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = getFromElement.get(element);
				assertion.assertThat(actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> queryAndAssert(final String expected, final By by,
			final GetFromWebElementWithParameter getFromWebElementWithParameter, final String parameter, final ExpectedAssertion assertion,
			final Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = getFromWebElementWithParameter.get(element, parameter);
				assertion.assertThat(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> queryAndAssert(final By by,
			final GetFromWebElementWithParameter getFromWebElementWithParameter, final String parameter, final Assertion assertion,
			final Configuration configuration) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {

				ExpectedCondition<WebElement> presenceOfElement = presenceOfElementLocated(by);
				WebElement element = presenceOfElement.apply(driver);
				if (element == null) {
					return false;
				}

				new Highlighter(driver, configuration).highlight(element);

				this.actual = getFromWebElementWithParameter.get(element, parameter);
				assertion.assertThat(actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with by '" + by + "' actual '" + actual + "'";
			}

		};
	}

	public static ExpectedCondition<Boolean> assertion(final String expected, final GetFromWebDriver getFromWebDriver,
			final ExpectedAssertion assertion) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {
				this.actual = getFromWebDriver.get(driver);
				assertion.assertThat(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with expected '" + expected + "' actual '" + actual + "'";
			}
		};
	}

	public static ExpectedCondition<Boolean> assertion(final GetFromWebDriver getFromWebDriver, final Assertion assertion) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {
				this.actual = getFromWebDriver.get(driver);
				assertion.assertThat(actual);
				return true;
			}

			@Override
			public String toString() {
				return "queryAndAssert with actual '" + actual + "'";
			}
		};
	}

	public static ExpectedCondition<Boolean> alertAndAssert(final String expected, final GetFromAlert getFromAlert,
			final ExpectedAssertion assertion) {
		return new ExpectedCondition<Boolean>() {

			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {
				Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
				if (alert == null) {
					return false;
				}
				this.actual = getFromAlert.get(alert);
				assertion.assertThat(expected, actual);
				return true;
			}

			@Override
			public String toString() {
				return "alertAndAssert with expected '" + expected + "'" + " actual '" + actual + "'";
			}
		};
	}

	public static ExpectedCondition<Boolean> alertAndAssert(final GetFromAlert getFromAlert, final Assertion assertion) {
		return new ExpectedCondition<Boolean>() {
			private String actual;

			@Override
			public Boolean apply(final WebDriver driver) {
				Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
				if (alert == null) {
					return false;
				}
				this.actual = getFromAlert.get(alert);
				assertion.assertThat(actual);
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
			public Alert apply(final WebDriver driver) {
				return ExpectedConditions.alertIsPresent().apply(driver);
			}

			@Override
			public String toString() {
				return "alertForAction";
			}
		};
	}

}
