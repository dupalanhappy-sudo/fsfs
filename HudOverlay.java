package com.example.hudmod;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.RenderTickCounter;

public final class HudOverlay {

    private HudOverlay() {
    }

    public static void register() {
        HudRenderCallback.EVENT.register(HudOverlay::render);
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) {
            return;
        }

        String text = "FPS: " + client.getCurrentFps()
                + "  Ping: " + getPing(client)
                + "  Size: " + HudConfig.get().level;

        float scale = HudConfig.get().scale();

        // Targets the 1.21.6+ GUI matrix stack (Matrix3x2fStack).
        context.getMatrices().pushMatrix();
        context.getMatrices().scale(scale, scale);
        context.drawTextWithShadow(client.textRenderer, text, 4, 4, 0xFFFFFF);
        context.getMatrices().popMatrix();
    }

    private static String getPing(MinecraftClient client) {
        if (client.getNetworkHandler() == null || client.player == null) {
            return "--";
        }
        PlayerListEntry entry = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid());
        return entry == null ? "--" : entry.getLatency() + " ms";
    }
}
