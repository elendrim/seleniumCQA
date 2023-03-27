package seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface WebDriverBot {

	void visit(String url);

	Action find(By by);

	WebDriverBot should(String expected, Function<WebDriver, String> textOnWebDriver, BiConsumer<String, String> assertion);
	
	WebDriverBot should(Function<WebDriver, String> textOnWebDriver, Consumer<String> assertion);

}
