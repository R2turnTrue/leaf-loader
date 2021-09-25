package xyz.namutree0345.lml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.slf4j.LoggerFactory;
import xyz.namutree0345.loaf.Mod;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

public class LoafLoader {

    private static LoafLoader INSTANCE;
    public static Logger logger = LogManager.getLogger("Loaf");

    public static LoafLoader getInstance() {
        if(INSTANCE == null)
            INSTANCE = new LoafLoader();
        return INSTANCE;
    }

    LoafLoader() {}

    public void loadMods(File modDirectory) throws MalformedURLException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if(!modDirectory.exists())
            modDirectory.mkdir();

        for (File file : modDirectory.listFiles()) {
            URLClassLoader urlClassLoader = new URLClassLoader(
                    new URL[] {file.toURI().toURL()},
                    this.getClass().getClassLoader()
            );
            Reflections reflections = new Reflections(urlClassLoader);
            Class<? extends Mod> clazz = reflections.getSubTypesOf(Mod.class).stream().findFirst().get();
            Mod mod = (Mod) clazz.getConstructors()[0].newInstance();
            mod.init();
        }
    }


}
