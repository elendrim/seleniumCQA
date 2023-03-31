package com.github.elendrim.seleniumcqa;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class WebDriverArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
          Arguments.of(new ChromeDriver()),
          Arguments.of(new FirefoxDriver())
        );
    }
}