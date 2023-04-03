package com.github.elendrim.seleniumcqa.assertion;

@FunctionalInterface
public interface Assertion {

	void assertThat(String actual);

}
