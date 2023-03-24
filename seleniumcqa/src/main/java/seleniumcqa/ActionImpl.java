package seleniumcqa;

import static seleniumcqa.Conditions.isInteractable;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ActionImpl implements Action {

	private WebDriver driver;
	private WebElement webElement;
	private FluentWait<WebDriver> wait;
	

	public ActionImpl(WebDriver driver, WebElement webElement) {
		this.driver = driver;
		this.wait = new FluentWait<WebDriver>(driver);
		this.webElement = webElement;
	}

	
	// https://docs.cypress.io/guides/core-concepts/interacting-with-elements
	
	@Override
	public Action sendKeys(CharSequence... keysToSend) {
		before(isInteractable(webElement));
		webElement.sendKeys(keysToSend);
		return this;
	}

	@Override
	public Action click() {
		before(isInteractable(webElement));
		webElement.click();
		return this;
	}

	@Override
	public String getText() {
		before(Conditions.isVisible(webElement));
		return webElement.getText();
	}
	
	
	private void before(ExpectedCondition<Boolean> condition) {
		scrollIntoView();
		
		String currentStyle = webElement.getAttribute("style");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, currentStyle + " color: yellow; border: 5px solid yellow;");
		
		wait.until(condition);
		
		sleep(500);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, currentStyle);
	}
	
	private void scrollIntoView() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
	}
	
	
	public void sleep(long millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (Exception e) {
		}
	}

}
