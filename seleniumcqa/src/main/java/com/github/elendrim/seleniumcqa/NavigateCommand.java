package com.github.elendrim.seleniumcqa;

import java.net.URL;

public interface NavigateCommand {

	NavigateCommand to(String url);

	NavigateCommand to(URL url);

	NavigateCommand back();

	NavigateCommand forward();

	NavigateCommand refresh();

}
