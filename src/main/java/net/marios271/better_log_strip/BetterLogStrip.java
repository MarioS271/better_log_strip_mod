package net.marios271.better_log_strip;

import net.fabricmc.api.ClientModInitializer;

public class BetterLogStrip implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        StripHandler.register();
    }
}
