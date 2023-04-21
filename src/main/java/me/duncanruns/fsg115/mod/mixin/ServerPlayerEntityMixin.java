package me.duncanruns.fsg115.mod.mixin;

import me.duncanruns.fsg115.mod.FSG115Mod;
import me.duncanruns.fsg115.mod.SeedManager;
import me.voidxwalker.autoreset.Atum;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void endOfServerStartTest(CallbackInfo info) {
        if (FSG115Mod.getOptions().runInBG && Atum.isRunning && !SeedManager.canTake()) {
            SeedManager.find();
        }
    }
}
