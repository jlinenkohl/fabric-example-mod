package net.fabricmc.example.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.command.*;
import net.minecraft.text.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.context.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.brigadier.tree.*;

@Mixin(TitleScreen.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		System.out.println("This line is printed by an example mod mixin!");
	}

public static LiteralCommandNode CommandManager.dispatcher.register(CommandDispatcher<ServerCommandSource> dispatcher) {
    return dispatcher.register(CommandManager.literal("lobby");
        CommandManager.executes(ctx -> m_TeleportToLobby(ctx));
}

public static int m_TeleportToLobby(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
    final ServerCommandSource source = ctx.getSource();
    final PlayerEntity self = source.getPlayer(); // If not a player than the command ends

		if ((Object)(self) instanceof PlayerEntity) {
			self.setPos(0.0, 75.0, 0.0);
			self.setHeadYaw(-90.0F);
		} else {
			throw new SimpleCommandExceptionType(new TranslatableText("Could not teleport to lobby.")).create();
		}
		return 1;
    }
 

}
