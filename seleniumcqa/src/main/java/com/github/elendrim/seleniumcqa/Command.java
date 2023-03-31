package com.github.elendrim.seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.WebElement;

public interface Command {

	Command sendKeys(CharSequence... keysToSend);

	Command click();

	Command should(String expected, Function<WebElement, String> textOnElement, BiConsumer<String, String> assertion);

	Command should(Function<WebElement, String> textOnElement, Consumer<String> assertion);

	Command should(String expected, BiFunction<WebElement, String, String> textOnElement, String parameter,
			BiConsumer<String, String> assertion);

	Command should(BiFunction<WebElement, String, String> textOnElement, String parameter, Consumer<String> assertion);

}
