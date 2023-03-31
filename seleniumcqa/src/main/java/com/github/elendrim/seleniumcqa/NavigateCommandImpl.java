package com.github.elendrim.seleniumcqa;

import java.net.URL;

import org.openqa.selenium.WebDriver;

public class NavigateCommandImpl implements NavigateCommand {

	private WebDriver driver;

	public NavigateCommandImpl(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public NavigateCommand to(String url) {
		driver.navigate().to(url);
		return this;
	}

	@Override
	public NavigateCommand to(URL url) {
		driver.navigate().to(url);
		return this;
	}

	@Override
	public NavigateCommand back() {
		driver.navigate().back();
		return this;
	}

	@Override
	public NavigateCommand forward() {
		driver.navigate().forward();
		return this;
	}

	@Override
	public NavigateCommand refresh() {
		driver.navigate().refresh();
		return this;
	}

}
