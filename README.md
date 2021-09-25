# Leaf Mod Loader

## Development environment
Windows, Mac, Linux and Java 8

## How to debug
1. Create *leafloader* folder.
2. Download my patched [launchwrapper-1.12-all.jar](https://github.com/NamuTree0345/LegacyLauncher/releases/download/1.12/launchwrapper-1.12-all.jar)
3. Copy the launchwrapper to *leafloader*
4. Run shadowJar
5. Copy shade-jar(loader-1.0-SNAPSHOT-all.jar) to *leafloader*
6. Execute `java -cp leaf-default.jar;launchwrapper-1.12-all.jar;loader-1.0-SNAPSHOT-all.jar net.minecraft.launchwrapper.Launch --tweakClass xyz.namutree0345.lml.LoafLoaderTweaker` in *leafloader* folder