package seleniumcqa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlight {
	
	private final WebDriver driver;
	private final WebElement element;
	private String currentStyle;

	public Highlight(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
	}

	public void startHighlight() {
		this.currentStyle = element.getAttribute("style");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, this.currentStyle + " color: yellow; border: 5px solid yellow;");
	}
	
	public void endHighlight() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, this.currentStyle);
	}
	
}
