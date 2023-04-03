package com.github.elendrim.seleniumcqa.assertion;

@FunctionalInterface
public interface ExpectedAssertion {

	void assertThat(String expected, String actual);

}
