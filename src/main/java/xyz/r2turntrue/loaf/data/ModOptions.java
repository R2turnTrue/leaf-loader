package xyz.r2turntrue.loaf.data;

import java.util.List;

public final class ModOptions {
    private final ModID id;
    private final String name;
    private final String version;
    private final Author[] authors;
    private final List<String> mixins;

    public ModOptions(ModID id, String name, String version, Author[] authors, List<String> mixins) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.authors = authors;
        this.mixins = mixins;
    }

    public ModID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String version() {
        return version;
    }

    public Author[] authors() {
        return authors;
    }

    public List<String> mixins() {
        return mixins;
    }
}
