package org.example.testmod;

import xyz.namutree0345.loaf.Mod;

public class TestMod extends Mod {
    public TestMod() {
        super("test", "Test", "1.0", "NamuTree0345");
    }

    @Override
    public void init() {
        System.out.println("Test Mod Initializing...");
    }
}