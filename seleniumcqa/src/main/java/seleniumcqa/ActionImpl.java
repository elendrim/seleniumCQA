package seleniumcqa;

import static seleniumcqa.Conditions.isInteractable;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		beforeAction();
		webElement.sendKeys(keysToSend);
		return this;
	}

	@Override
	public Action click() {
		beforeAction();
		webElement.click();
		return this;
	}

	@Override
	public String getText() {
		before();
		return webElement.getText();
	}
	
	@Override
	public Action should(String expected, Function<WebElement, String> textOnElement, BiConsumer<String, String> assertion) {
		beforeAssert();
		String text = textOnElement.apply(webElement);
		wait.until(Conditions.assertOnElement(expected, text, assertion));
		return this;
	}
	
	@Override
	public Action should(Function<WebElement, String> textOnElement, Consumer<String> assertion) {
		beforeAssert();
		String text = textOnElement.apply(webElement);
		wait.until(Conditions.assertOnElement(text, assertion));
		return this;
	}
	
	@Override
	public Action should(String expected,  BiFunction<WebElement, String, String> textOnElement, String parameter, BiConsumer<String, String> assertion) {
		beforeAssert();
		String text = textOnElement.apply(webElement, parameter);
		wait.until(Conditions.assertOnElement(expected, text, assertion));
		return this;
	}
	
	@Override
	public Action should(BiFunction<WebElement, String, String> textOnElement, String parameter, Consumer<String> assertion) {
		beforeAssert();
		String text = textOnElement.apply(webElement, parameter);
		wait.until(Conditions.assertOnElement(text, assertion));
		return this;
	}
	
	private void before() {
		Highlight highlight = new Highlight(driver, webElement);
		highlight.startHighlight();
		sleep(500);
		highlight.endHighlight();
	}
	
	private void beforeAction() {
		Highlight highlight = new Highlight(driver, webElement);
		highlight.startHighlight();
		scrollIntoView();
		wait.until(isInteractable(webElement));
		sleep(500);
		highlight.endHighlight();
	}
	
	private void beforeAssert() {
		Highlight highlight = new Highlight(driver, webElement);
		highlight.startHighlight();
		sleep(500);
		highlight.endHighlight();
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
