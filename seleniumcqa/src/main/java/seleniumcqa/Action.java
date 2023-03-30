package seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.WebElement;

public interface Action {

	Action sendKeys(CharSequence... keysToSend);

	Action click();

	Action should(String expected, Function<WebElement, String> textOnElement, BiConsumer<String, String> assertion);

	Action should(Function<WebElement, String> textOnElement, Consumer<String> assertion);

	Action should(String expected, BiFunction<WebElement, String, String> textOnElement, String parameter,
			BiConsumer<String, String> assertion);

	Action should(BiFunction<WebElement, String, String> textOnElement, String parameter, Consumer<String> assertion);

}
