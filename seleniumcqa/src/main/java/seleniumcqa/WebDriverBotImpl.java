package seleniumcqa;

import java.time.Duration;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WebDriverBotImpl implements WebDriverBot {
	
	private WebDriver driver;
	private Wait<WebDriver> wait;

	public WebDriverBotImpl(WebDriver driver) {
		this.driver = driver;
		this.wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10));
	 
	}

	@Override
	public void visit(String url) {
		driver.get(url);
	}
	
	@Override
	public Action find(By by) {
		Query query = new QueryImpl(driver);
		WebElement webElement = query.find(by);
		return new ActionImpl(driver, webElement);
	}

	
	@Override
	public WebDriverBot should(String expected, Function<WebDriver, String> textOnWebDriver, BiConsumer<String, String> assertion) {
		String text = textOnWebDriver.apply(driver);
		wait.until(Conditions.assertOnElement(expected, text, assertion));
		return this;
	}
	
	@Override
	public WebDriverBot should(Function<WebDriver, String> textOnWebDriver, Consumer<String> assertion) {
		String text = textOnWebDriver.apply(driver);
		wait.until(Conditions.assertOnElement(text, assertion));
		return this;
	}

}
