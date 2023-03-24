package seleniumcqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class QueryImpl implements Query {

	private WebDriver driver;
	private Wait<WebDriver> wait;

	public QueryImpl(WebDriver driver) {
		this.driver = driver;
		this.wait = new FluentWait<WebDriver>(driver);
	}

	@Override
	public WebElement find(By by) {
		return wait.until(driver -> driver.findElement(by));
	}
	
	

}
