package me.duncanruns.fsg115.mod.mixin;

import com.mojang.brigadier.CommandDispatcher;
import me.duncanruns.fsg115.mod.command.FSG115OptionCommand;
import me.duncanruns.fsg115.mod.command.TokenCommand;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public abstract class CommandManagerMixin {
    @Shadow
    @Final
    private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addCommandMixin(boolean isDedicatedServer, CallbackInfo info) {
        TokenCommand.register(dispatcher);
        FSG115OptionCommand.register(dispatcher);
    }
}
