package com.terraformersmc.terrestria.config;

public class TerrestriaGeneralConfig {
	private boolean quarterLogs = true;
	private boolean oceanVolcanoes = true;

	public boolean areQuarterLogsEnabled() {
		return false;
	}

	public boolean areOceanVolcanoesEnabled() {
		return oceanVolcanoes;
	}
}
