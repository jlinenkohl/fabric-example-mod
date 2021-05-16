package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

import net.fabricmc.fabric.api.command.v1.*;
import net.minecraft.server.command.*;
import net.minecraft.text.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.context.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.brigadier.tree.*;


public class ExampleMod implements ModInitializer {

	//	public static final FabricItem FABRIC_ITEM = new FabricItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16));
	public static final Item FABRIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");


		// Register the new item
		Registry.register(Registry.ITEM, new Identifier("tutorial", "fabric_item"), FABRIC_ITEM);


		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("hub").executes(context -> {
				return m_teleportToLobby(context);
			}));
		});

	}


/*
	public static LiteralCommandNode register(CommandDispatcher<ServerCommandSource> dispatcher) {
		return dispatcher.register(CommandManager.literal("lobby")
			.executes(ctx -> m_TeleportToLobby(ctx)));
}
*/
	public static int m_teleportToLobby(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		ServerCommandSource source = context.getSource();
		PlayerEntity self = source.getPlayer();

		if ((Object)(self) instanceof PlayerEntity) {
			self.sendSystemMessage(new LiteralText("Teleporting you to the lobby..."), Util.NIL_UUID);
			self.teleport(0.0, 15.0, 0.0);
//			self.setPos(0.0, 15.0, 0.0);

			self.updatePositionAndAngles(0.0, 15.0, 0.0, 270.0F, 0.0F);
			self.refreshPositionAfterTeleport(0.0, 15.0, 0.0);
			return 1;
		} else {
			throw new SimpleCommandExceptionType(new TranslatableText("Could not teleport to lobby.")).create();
		}
	}

}