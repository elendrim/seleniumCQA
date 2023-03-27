package seleniumcqa;

import static seleniumcqa.Conditions.presenceOfElementLocated;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class QueryImpl implements Query {

	private Wait<WebDriver> wait;

	public QueryImpl(WebDriver driver) {
		this.wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.ignoring(NoSuchElementException.class);
		
	}

	@Override
	public WebElement find(By by) {
		return wait.until(presenceOfElementLocated(by));
	}
	
	

}
