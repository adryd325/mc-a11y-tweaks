package com.adryd.a11ytweaks;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "a11ytweaks")
public class A11yTweaksConfig implements ConfigData {
    private boolean disableTorchFlicker = true;
    private boolean disableNightVisionFlashing = true;
    private boolean scaleDebugPie = true;

    public boolean getDisableTorchFlicker() {
        return this.disableTorchFlicker;
    }

    public boolean getDisableNightVisionFlashing() {
        return this.disableNightVisionFlashing;
    }

    public boolean getScaleDebugPie() {
        return this.scaleDebugPie;
    }
}
