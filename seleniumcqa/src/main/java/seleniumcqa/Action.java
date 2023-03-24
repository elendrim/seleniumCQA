package seleniumcqa;

public interface Action {

	Action sendKeys(CharSequence... keysToSend);

	Action click();

	String getText();

}
