package xyz.r2turntrue.lml;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.r2turntrue.loaf.Mod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoafLoader {

    public static Logger logger = LogManager.getLogger("Loaf");
    private static LoafLoader INSTANCE;

    LoafLoader() {
    }

    public static LoafLoader getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LoafLoader();
        return INSTANCE;
    }

    public void loadMods(Path modDirectory) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, URISyntaxException {
        if (!Files.exists(modDirectory)) Files.createDirectories(modDirectory);

        List<Mod> loadedMods = new ArrayList<>();

        Files.list(modDirectory).forEach(path -> {
            try {
                URL loadPath = path.toUri().toURL();
                URL[] classUrl = new URL[]{loadPath};

                ClassLoader cl = new URLClassLoader(classUrl);

                if (cl.getResource("loaf.json") == null) {
                    LoafLoader.logger.warn("Skip Loading Non-Loaf Mod Jar " + path.getFileName());
                } else {
                    Map<String, String> env = new HashMap<>();
                    String[] array = cl.getResource("loaf.json").toURI().toString().split("!");
                    FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), env);
                    Path loafJson = fs.getPath(array[1]);
                    JsonObject loafJsonContent = JsonParser.parseReader(Files.newBufferedReader(loafJson)).getAsJsonObject();

                    Class<?> loadedClass = cl.loadClass(loafJsonContent.get("main").getAsString());

                    Mod mod = (Mod) loadedClass.getConstructors()[0].newInstance();
                    loadedMods.add(mod);
                }
            } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | URISyntaxException e) {
                LoafLoader.logger.error("Could not load mods!");
                e.printStackTrace();
            }
        });

        LoafLoader.logger.info("-- Loaded Loaf Mods --");
        for (Mod loadedMod : loadedMods) {
            LoafLoader.logger.info("  - " + loadedMod.name + "");
            loadedMod.init();
        }
    }
}
