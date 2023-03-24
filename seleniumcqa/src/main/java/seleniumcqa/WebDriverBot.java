package seleniumcqa;

import org.openqa.selenium.By;

public interface WebDriverBot {

	void visit(String url);

	Action find(By by);

	String getTitle();

}
