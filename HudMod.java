package com.example.hudmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class HudMod implements ClientModInitializer {

    private static final KeyBinding.Category CATEGORY =
            KeyBinding.Category.create(Identifier.of("hudmod", "controls"));

    private static KeyBinding increaseKey;
    private static KeyBinding decreaseKey;

    @Override
    public void onInitializeClient() {
        HudConfig.load();
        HudOverlay.register();

        increaseKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hudmod.increase", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_BRACKET, CATEGORY));
        decreaseKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hudmod.decrease", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_BRACKET, CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (increaseKey.wasPressed()) {
                HudConfig.adjust(1);
            }
            while (decreaseKey.wasPressed()) {
                HudConfig.adjust(-1);
            }
        });
    }
}
