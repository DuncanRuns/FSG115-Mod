package me.duncanruns.fsg115.mod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.duncanruns.fsg115.mod.FSG115Mod;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

public class TokenCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("oceantoken").executes(TokenCommand::execute));
    }

    private static int execute(CommandContext<ServerCommandSource> context) {
        String lastToken = FSG115Mod.getLastToken();
        if (lastToken == null) {
            context.getSource().sendError(new LiteralText("No tokens have been generated yet."));
            return 0;
        }
        context.getSource().sendFeedback(
                new LiteralText("Last token: ").append(
                        Texts.bracketed(new LiteralText(lastToken).styled(style -> style.setColor(Formatting.GREEN)
                                .setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, FSG115Mod.getLastToken()))
                                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("chat.copy.click")))))
                ), false
        );
        return 1;
    }
}
