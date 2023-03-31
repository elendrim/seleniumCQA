package com.github.elendrim.seleniumcqa;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.Alert;

public interface AlertCommand {

	AlertCommand sendKeys(String keysToSend);

	AlertCommand dismiss();

	AlertCommand accept();

	AlertCommand should(Function<Alert, String> textOnAlert, Consumer<String> assertion);

	AlertCommand should(String expected, Function<Alert, String> textOnAlert, BiConsumer<String, String> assertion);
}
