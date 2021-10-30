package com.mrshiehx.mcmods.xphone;

import com.mrshiehx.mcmods.xphone.modules.RegisterBlocks;
import com.mrshiehx.mcmods.xphone.modules.RegisterItems;
import com.mrshiehx.mcmods.xphone.modules.TitleManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

public class XPhone implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("XPhone");
	public static final String MODID="xphone";
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		RegisterItems.registerItems();
		RegisterBlocks.registerBlocks();
	}
}
