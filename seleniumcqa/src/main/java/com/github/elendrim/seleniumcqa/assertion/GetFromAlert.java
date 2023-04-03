package com.github.elendrim.seleniumcqa.assertion;

import org.openqa.selenium.Alert;

@FunctionalInterface
public interface GetFromAlert {

	String get(Alert t);

}
