package net.marios271;

import net.fabricmc.api.ClientModInitializer;

public class BetterLogStrip implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        StripHandler.register();
    }
}
