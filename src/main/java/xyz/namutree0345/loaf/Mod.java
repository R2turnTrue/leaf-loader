package xyz.namutree0345.loaf;

public abstract class Mod {

    public String name;
    public String id;
    public String version;
    public String[] authors;

    public Mod(String id, String name, String version, String... authors) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.authors = authors;
    }

    public void init() {}

}
