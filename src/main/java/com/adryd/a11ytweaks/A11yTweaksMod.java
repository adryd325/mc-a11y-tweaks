package com.adryd.a11ytweaks;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A11yTweaksMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("a11ytweaks");

	public static final A11yTweaksConfig CONFIG;

	static {
		AutoConfig.register(A11yTweaksConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(A11yTweaksConfig.class).getConfig();
	}

	@Override
	public void onInitialize() {
	}
}
