package xyz.r2turntrue.loaf.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public final class Author {
    private final String name;

    public Author(String name) {
        this.name = name;
    }

    public static Author[] asJsonArray(JsonArray array) {
        List<Author> authors = new ArrayList<>();
        for (JsonElement jsonElement : array) {
            authors.add(new Author(jsonElement.getAsString()));
        }
        return authors.toArray(new Author[0]);
    }

    public String name() {
        return name;
    }
}
