package me.duncanruns.fsg115.mod.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

public class SeedFoundScreen extends Screen {
    private int ticked = 0;

    public SeedFoundScreen() {
        super(new LiteralText("Seed Found!"));
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        super.render(mouseX, mouseY, delta);
        renderBackground();
        this.drawCenteredString(minecraft.textRenderer, "Seed Found!", width / 2, height / 3, 0xFFFFFF);
    }

    @Override
    protected void init() {
        minecraft.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.BLOCK_ANVIL_LAND, 1.0f));
    }

    @Override
    public void tick() {
        ticked++;
        if (ticked == 10) {
            minecraft.openScreen(new CreateWorldScreen(null));
        }
    }
}
