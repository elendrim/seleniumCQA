package seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.opentest4j.AssertionFailedError;

public class Conditions {

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

	public static ExpectedCondition<Boolean> isInteractable(final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				boolean isDisplayed = element.isDisplayed();
				if (!isDisplayed) {
					return false;
				}

				boolean isEnabled = element.isEnabled();
				if (!isEnabled) {
					return false;
				}

				boolean isObscured = isObscured(element).apply(driver);
				if (isObscured) {
					return false;
				}

				return true;
			}

			@Override
			public String toString() {
				return "isInteractable of " + element;
			}
		};
	}

	public static ExpectedCondition<Boolean> isVisible(final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				boolean isDisplayed = element.isDisplayed();
				if (!isDisplayed) {
					return false;
				}

				boolean isObscured = isObscured(element).apply(driver);
				if (isObscured) {
					return false;
				}

				return true;
			}

			@Override
			public String toString() {
				return "isInteractable of " + element;
			}
		};
	}

	public static ExpectedCondition<WebElement> presenceOfElementLocated(final By locator) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}

			@Override
			public String toString() {
				return "presence of element located by: " + locator;
			}
		};
	}
	
	
	public static ExpectedCondition<Boolean> assertOnElement(final String expected, final String actual, final BiConsumer<String, String> function) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				
				try {
					function.accept(expected, actual);
					return true;
				} catch (AssertionFailedError e) {
					return false;
				}
			}

			@Override
			public String toString() {
				return "assertOnElement with function "+ function +" expected "+ expected +" actual "+actual;
			}
		};
	}

	public static ExpectedCondition<Boolean> assertOnElement(final String actual, final Consumer<String> function) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				
				try {
					function.accept(actual);
					return true;
				} catch (AssertionFailedError e) {
					return false;
				}
			}

			@Override
			public String toString() {
				return "assertOnElement with "+ function +" actual "+actual;
			}
		};
	}
}
