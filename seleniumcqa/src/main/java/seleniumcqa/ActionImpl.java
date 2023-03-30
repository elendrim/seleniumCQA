package seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class ActionImpl implements Action {

	private FluentWait<WebDriver> assertionWait;
	private FluentWait<WebDriver> commandWait;
	private By by;
	private Configuration configuration;

	public ActionImpl(WebDriver driver, By by, Configuration configuration) {
		this.commandWait = WaitBuilder.createForCommand(driver, configuration.getCommandPollingEveryDuration(),
				configuration.getCommandTimeoutDuration());
		this.assertionWait = WaitBuilder.createForAssertion(driver, configuration.getAssertionPollingEveryDuration(),
				configuration.getAssertionTimeoutDuration());
		this.by = by;
		this.configuration = configuration;

	}

	@Override
	public Action sendKeys(CharSequence... keysToSend) {
		WebElement element = commandWait.until(Conditions.queryForCommand(by, configuration));
		element.sendKeys(keysToSend);
		return this;
	}

	@Override
	public Action click() {
		WebElement element = commandWait.until(Conditions.queryForCommand(by, configuration));
		element.click();
		return this;
	}

	@Override
	public Action should(String expected, Function<WebElement, String> textOnElement, BiConsumer<String, String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(expected, by, textOnElement, assertion, configuration));
		return this;
	}

	@Override
	public Action should(Function<WebElement, String> textOnElement, Consumer<String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(by, textOnElement, assertion, configuration));
		return this;
	}

	@Override
	public Action should(String expected, BiFunction<WebElement, String, String> textOnElement, String parameter,
			BiConsumer<String, String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(expected, by, textOnElement, parameter, assertion, configuration));
		return this;
	}

	@Override
	public Action should(BiFunction<WebElement, String, String> textOnElement, String parameter, Consumer<String> assertion) {
		assertionWait.until(Conditions.queryAndAssert(by, textOnElement, parameter, assertion, configuration));
		return this;
	}

}
