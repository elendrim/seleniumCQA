package seleniumcqa;

import java.time.Duration;

public class Configuration {

	private Duration highlightSleepDuration = Duration.ZERO;
	private Duration commandPollingEveryDuration = Duration.ofMillis(500);
	private Duration commandTimeoutDuration = Duration.ofSeconds(10);
	private Duration assertionPollingEveryDuration = Duration.ofMillis(500);
	private Duration assertionTimeoutDuration = Duration.ofSeconds(10);

	public boolean isHighlightEnabled() {
		return !highlightSleepDuration.isZero();
	}

	public Duration getHighlightSleepDuration() {
		return highlightSleepDuration;
	}

	public void setHighlightSleepDuration(Duration highlightSleepDuration) {
		this.highlightSleepDuration = highlightSleepDuration;
	}

	public Duration getCommandTimeoutDuration() {
		return commandTimeoutDuration;
	}

	public void setCommandTimeoutDuration(Duration commandTimeoutDuration) {
		this.commandTimeoutDuration = commandTimeoutDuration;
	}

	public Duration getAssertionTimeoutDuration() {
		return assertionTimeoutDuration;
	}

	public void setAssertionTimeoutDuration(Duration assertionTimeoutDuration) {
		this.assertionTimeoutDuration = assertionTimeoutDuration;
	}

	public Duration getAssertionPollingEveryDuration() {
		return assertionPollingEveryDuration;
	}

	public void setAssertionPollingEveryDuration(Duration assertionPollingEveryDuration) {
		this.assertionPollingEveryDuration = assertionPollingEveryDuration;
	}

	public Duration getCommandPollingEveryDuration() {
		return commandPollingEveryDuration;
	}

	public void setCommandPollingEveryDuration(Duration commandPollingEveryDuration) {
		this.commandPollingEveryDuration = commandPollingEveryDuration;
	}

}
