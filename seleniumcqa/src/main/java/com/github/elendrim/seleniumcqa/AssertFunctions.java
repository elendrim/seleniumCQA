package com.github.elendrim.seleniumcqa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;

import com.github.elendrim.seleniumcqa.assertion.Assertion;
import com.github.elendrim.seleniumcqa.assertion.ExpectedAssertion;

public class AssertFunctions {

	public static ExpectedAssertion ASSERT_EQUALS = (String expected, String actual) -> assertThat(actual, is(equalTo(expected)));
	public static ExpectedAssertion ASSERT_EQUALS_IGNORE_CASE = (String expected, String actual) -> assertThat(actual,
			is(equalToIgnoringCase(expected)));

	public static ExpectedAssertion ASSERT_CONTAINS = (String expected, String actual) -> assertThat(actual, containsString(expected));
	public static ExpectedAssertion ASSERT_CONTAINS_IGNORE_CASE = (String expected, String actual) -> assertThat(actual,
			containsStringIgnoringCase(expected));

	public static ExpectedAssertion ASSERT_NOT_EQUALS = (String expected, String actual) -> assertThat(actual, not(is(equalTo(expected))));
	public static ExpectedAssertion ASSERT_NOT_EQUALS_IGNORE_CASE = (String expected, String actual) -> assertThat(actual,
			not(is(equalToIgnoringCase(expected))));

	public static Assertion ASSERT_EMPTY = (String actual) -> assertThat(actual, is(Matchers.emptyOrNullString()));
	public static Assertion ASSERT_NOT_EMPTY = (String actual) -> assertThat(actual, is(not(Matchers.emptyOrNullString())));

}
