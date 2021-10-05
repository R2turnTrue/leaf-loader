package xyz.r2turntrue.loaf;

import xyz.r2turntrue.loaf.data.ModOptions;

public abstract class Mod {
    private ModOptions options;

    public void init() {}

    public ModOptions options() {
        return options;
    }

    public void setOptions(ModOptions options) {
        if (this.options != null) throw new RuntimeException("Mod options is always loaded.");
        else this.options = options;
    }
}
