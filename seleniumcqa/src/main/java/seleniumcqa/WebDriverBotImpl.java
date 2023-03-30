package seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class WebDriverBotImpl implements WebDriverBot {

	private final WebDriver driver;
	private final Wait<WebDriver> assertionWait;
	private final Configuration configuration;

	public WebDriverBotImpl(WebDriver driver) {
		this(driver, new Configuration());
	}

	public WebDriverBotImpl(WebDriver driver, Configuration configuration) {
		this.driver = driver;
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
		this.configuration = configuration;
	}

	@Override
	public void visit(String url) {
		driver.get(url);
	}

	@Override
	public Action find(By by) {
		return new ActionImpl(driver, by, configuration);
	}

	@Override
	public WebDriverBot should(String expected, Function<WebDriver, String> textOnWebDriver, BiConsumer<String, String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(expected, textOnWebDriver, assertion));
		return this;
	}

	@Override
	public WebDriverBot should(Function<WebDriver, String> textOnWebDriver, Consumer<String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(textOnWebDriver, assertion));
		return this;
	}

}
