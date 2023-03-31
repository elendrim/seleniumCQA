package com.github.elendrim.seleniumcqa;

import java.time.Duration;

public class Configuration {

	private Duration highlightSleepDuration = Duration.ZERO;
	private Duration actionPollingEveryDuration = Duration.ofMillis(500);
	private Duration actionTimeoutDuration = Duration.ofSeconds(10);
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

	public Duration getActionTimeoutDuration() {
		return actionTimeoutDuration;
	}

	public void setActionTimeoutDuration(Duration actionTimeoutDuration) {
		this.actionTimeoutDuration = actionTimeoutDuration;
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

	public Duration getActionPollingEveryDuration() {
		return actionPollingEveryDuration;
	}

	public void setActionPollingEveryDuration(Duration actionPollingEveryDuration) {
		this.actionPollingEveryDuration = actionPollingEveryDuration;
	}

}
