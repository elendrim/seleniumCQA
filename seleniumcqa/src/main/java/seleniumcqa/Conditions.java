package seleniumcqa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

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
				if ( !isDisplayed ) {
					return false;
				}
				
				boolean isEnabled = element.isEnabled();
				if ( !isEnabled ) {
					return false;
				}
				
				boolean isObscured = isObscured(element).apply(driver);
				if ( isObscured ) {
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
				if ( !isDisplayed ) {
					return false;
				}
				
				boolean isObscured = isObscured(element).apply(driver);
				if ( isObscured ) {
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
	

}
