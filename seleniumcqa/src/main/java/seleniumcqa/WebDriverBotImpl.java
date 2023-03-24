package seleniumcqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverBotImpl implements WebDriverBot {
	
	private WebDriver driver;

	public WebDriverBotImpl(WebDriver driver) {
		this.driver = driver;
	 
	}

	@Override
	public void visit(String url) {
		driver.get(url);
	}
	
	@Override
	public Action find(By by) {
		Query query = new QueryImpl(driver);
		WebElement webElement = query.find(by);
		return new ActionImpl(driver, webElement);
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

}
