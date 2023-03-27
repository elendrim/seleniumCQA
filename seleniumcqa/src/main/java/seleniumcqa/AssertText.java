package seleniumcqa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;	

public class AssertText {
	
	public static Function<WebElement, String> GET_ACCESSIBLE_NAME = (WebElement element) -> element.getAccessibleName();
	public static Function<WebElement, String> GET_ARIA_ROLE = (WebElement element) -> element.getAriaRole();
	public static BiFunction<WebElement, String, String> GET_ATTRIBUTE = (WebElement element, String name) -> element.getAttribute(name);
	public static BiFunction<WebElement, String, String> GET_CSS_VALUE  = (WebElement element, String propertyName) -> element.getCssValue(propertyName);
	public static BiFunction<WebElement, String, String> GET_DOM_ATTRIBUTE = (WebElement element, String name) -> element.getDomAttribute(name);
	public static BiFunction<WebElement, String, String> GET_DOM_PROPERTY = (WebElement element, String name) -> element.getDomProperty(name);
	public static Function<WebElement, String> GET_TAGNAME = (WebElement element) -> element.getTagName();
	public static Function<WebElement, String> GET_TEXT = (WebElement element) -> element.getText();
	
	
	
	public static Function<WebDriver, String> GET_CURRENT_URL = (WebDriver driver) -> driver.getCurrentUrl();
	public static Function<WebDriver, String> GET_PAGE_SOURCE = (WebDriver driver) -> driver.getPageSource();
	public static Function<WebDriver, String> GET_TITLE = (WebDriver driver) -> driver.getTitle();
	public static Function<WebDriver, String> GET_WINDOW_HANDLE = (WebDriver driver) -> driver.getWindowHandle();
	
	
	
	public static BiConsumer<String, String> ASSERT_EQUALS = (String expected, String actual) -> assertThat(actual, is(equalTo(expected)));
	public static BiConsumer<String, String> ASSERT_EQUALS_IGNORE_CASE = (String expected, String actual) ->  assertThat(actual, is(equalToIgnoringCase(expected)));
	
	public static BiConsumer<String, String> ASSERT_CONTAINS = (String expected, String actual) ->  assertThat(actual, containsString(expected));
	public static BiConsumer<String, String> ASSERT_CONTAINS_IGNORE_CASE = (String expected, String actual) ->  assertThat(actual, containsStringIgnoringCase(expected));
	
	public static BiConsumer<String, String> ASSERT_NOT_EQUALS = (String expected, String actual) -> assertThat(actual, not(is(equalTo(expected))));
	public static BiConsumer<String, String> ASSERT_NOT_EQUALS_IGNORE_CASE = (String expected, String actual) -> assertThat(actual, not(is(equalToIgnoringCase(expected))));

	public static Consumer<String> ASSERT_EMPTY = (String actual) ->  assertThat(actual, is(Matchers.emptyOrNullString()));
	public static Consumer<String> ASSERT_NOT_EMPTY = (String actual) ->  assertThat(actual, is(not(Matchers.emptyOrNullString())));
	
}
