package me.duncanruns.fsg115.mod.screen;

import me.duncanruns.fsg115.mod.SeedManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

public class FilteringScreen extends Screen {
    public FilteringScreen() {
        super(new LiteralText("Filtering Seeds..."));
    }


    @Override
    public void render(int mouseX, int mouseY, float delta) {
        super.render(mouseX, mouseY, delta);
        renderBackground();
        this.drawCenteredString(minecraft.textRenderer, "Filtering Seeds...", width / 2, height / 3, 0xFFFFFF);
    }

    @Override
    public void tick() {
        if (SeedManager.canTake()) {
            minecraft.openScreen(new SeedFoundScreen());
        }
    }
}
