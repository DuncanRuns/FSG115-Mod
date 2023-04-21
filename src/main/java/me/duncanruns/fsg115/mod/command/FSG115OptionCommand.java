package me.duncanruns.fsg115.mod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.duncanruns.fsg115.mod.FSG115Mod;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class FSG115OptionCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("fsg115options")
                .then(CommandManager.literal("threads").then(CommandManager.argument("amount", IntegerArgumentType.integer(1, 1000)).executes(context -> {
                    int amount = IntegerArgumentType.getInteger(context, "amount");
                    FSG115Mod.getOptions().threads = amount;
                    FSG115Mod.saveOptions();
                    context.getSource().sendFeedback(new LiteralText("The filter will run with " + amount + " threads"), false);
                    return 1;
                })))
                .then(CommandManager.literal("background").then(CommandManager.argument("enabled", BoolArgumentType.bool()).executes(context -> {
                    boolean enabled = BoolArgumentType.getBool(context, "enabled");
                    FSG115Mod.getOptions().runInBG = enabled;
                    FSG115Mod.saveOptions();
                    context.getSource().sendFeedback(new LiteralText(enabled ? "The filter will run in the background once the world has loaded" : "The filter will not run in the background"), false);
                    return 1;
                })))
        );
    }
}
