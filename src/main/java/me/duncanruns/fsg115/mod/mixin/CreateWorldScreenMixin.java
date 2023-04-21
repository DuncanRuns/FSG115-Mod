package me.duncanruns.fsg115.mod.mixin;

import me.duncanruns.fsg115.mod.FSG115Mod;
import me.duncanruns.fsg115.mod.SeedManager;
import me.duncanruns.fsg115.mod.screen.FilteringScreen;
import me.duncanruns.fsg115.runner.FilterResult;
import me.voidxwalker.autoreset.Atum;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin extends Screen {
    protected CreateWorldScreenMixin() {
        super(null);
    }

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void interruptCreationMixin(CallbackInfo info) {
        if (!Atum.isRunning) return;

        if (SeedManager.canTake()) {
            FilterResult filterResult = SeedManager.take();
            FSG115Mod.setLastToken(filterResult.toToken());
            Atum.seed = Long.toString(filterResult.getWorldSeed());
        } else {
            SeedManager.find();
            minecraft.openScreen(new FilteringScreen());
            info.cancel();
        }
    }
}
